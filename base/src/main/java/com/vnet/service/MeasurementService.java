package com.vnet.service;

import com.vnet.Utils;
import com.vnet.db.SqlService;
import com.vnet.model.Loinc;
import com.vnet.model.Measurement;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class MeasurementService {

    private LoincService loincService;
    private SqlService sqlService;

    public void setLoincService(LoincService loincService) {this.loincService = loincService;}
    public void setSqlService(SqlService sqlService) {this.sqlService = sqlService;}

    /**
     * Get Measurement by id
     * @param id identifier
     * @return Measurement
     */
    public Measurement get(String id) throws ServiceException {
        final String sql = "select * from measurement where id='" + id + "'";
        try {
            final List<Properties> items = sqlService.query(sql);
            if (items.size()==0)
                throw new ServiceException(ServiceException.Code.NOT_FOUND, id);

            return transform(items.get(0));
        } catch (SQLException e) {
            throw new ServiceException(ServiceException.Code.SQL, sql, e.getMessage());
        }
    }

    /**
     * Delete measurement
     * @param id identifier
     * @throws ServiceException
     */
    public void delete(String id) throws ServiceException {
        final String sql = "delete from measurement where id='" + id + "'";
        try {
            if (sqlService.update(sql) == 0)
                throw new ServiceException(ServiceException.Code.NOT_FOUND, id);
        } catch (SQLException e) {
            throw new ServiceException(ServiceException.Code.SQL, sql, e.getMessage());
        }
    }

    static private Measurement transform(Properties item) {
        return new Measurement(item.getProperty("id"),
                item.getProperty("patientId"),
                item.getProperty("deviceId"),
                item.getProperty("code"),
                item.getProperty("unit"),
                Float.parseFloat(item.getProperty("value")),
                !("0".equals(item.getProperty("manual"))),
                Utils.parse(item.getProperty("captureTimestamp")),
                Utils.parse(item.getProperty("creationTimestamp")),
                item.getProperty("note"));
    }

    private void check(Measurement measurement) throws ServiceException {
        if (Utils.notSet(measurement.getId()))
            throw new ServiceException(ServiceException.Code.MISSING, "measurement.id");
        if (Utils.notSet(measurement.getCode()))
            throw new ServiceException(ServiceException.Code.MISSING, "measurement.code");
        if (Utils.notSet(measurement.getPatientId()))
            throw new ServiceException(ServiceException.Code.MISSING, "measurement.patientId");
        if (Utils.notSet(measurement.getDeviceId()))
            throw new ServiceException(ServiceException.Code.MISSING, "measurement.deviceId");
        if (Utils.notSet(measurement.getUnit()))
            throw new ServiceException(ServiceException.Code.MISSING, "measurement.unit");

        if (measurement.getCaptureTimestamp()==null)
            measurement.setCaptureTimestamp(new Date());
        if (measurement.getCreationTimestamp()==null)
            measurement.setCreationTimestamp(new Date());

        final Loinc loinc = loincService.getCode(measurement.getCode());

        if (Utils.notSet(measurement.getNote()) && !Utils.notSet(loinc.getLongCommonName()))
            measurement.setNote(loinc.getLongCommonName());
    }

    private int getLimit(String last) throws ServiceException {
        if (Utils.notSet(last))
            return 10;

        try {
            return Integer.parseInt(last);
        } catch (NumberFormatException e) {
            throw new ServiceException(ServiceException.Code.ILV, last);
        }
    }

    /**
     * List all measurements
     * @return the list of all measurements
     */
    public Collection<Measurement> list(String last) throws ServiceException {
        final String sql = "select * from measurement order by captureTimestamp desc limit " + getLimit(last);
        try {
            final List<Properties> items = sqlService.query(sql);
            final Collection<Measurement> measurements = new LinkedList<>();
            measurements.addAll(items.stream().map(MeasurementService::transform).collect(Collectors.toList()));
            return measurements;
        } catch (SQLException e) {
            throw new ServiceException(ServiceException.Code.SQL, sql, e.getMessage());
        }
    }

    /**
     * Upload Measurement
     * @param measurement measurement
     */
    public Measurement upload(Measurement measurement) throws ServiceException {
        check(measurement);
        StringBuilder sb = new StringBuilder("insert into measurement (id,patientId,deviceId,code,unit,value,manual,captureTimestamp,creationTimestamp,note)");
        sb.append(" values ")
                .append("('").append(measurement.getId()).append("'")
                .append(",'").append(measurement.getPatientId()).append("'")
                .append(",'").append(measurement.getDeviceId()).append("'")
                .append(",'").append(measurement.getCode()).append("'")
                .append(",'").append(measurement.getUnit()).append("'")
                .append(",").append(measurement.getValue())
                .append(",").append(measurement.isManual() ? 1 : 0)
                .append(",'").append(Utils.formatDate(measurement.getCaptureTimestamp())).append("'")
                .append(",'").append(Utils.formatDate(new Date())).append("'");
        if (measurement.getNote()!=null)
            sb.append(",'").append(measurement.getNote()).append("')");
        else
            sb.append("null)");
        final String sql = sb.toString();
        try {
            sqlService.update(sql);
            return measurement;
        } catch (SQLException e) {
            throw new ServiceException(ServiceException.Code.SQL, sql, e.getMessage());
        }
    }
}