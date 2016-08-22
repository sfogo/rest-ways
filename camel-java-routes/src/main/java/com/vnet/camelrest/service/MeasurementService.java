package com.vnet.camelrest.service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import com.vnet.camelrest.model.Loinc;
import com.vnet.camelrest.model.Measurement;
import org.apache.camel.BeanInject;

/**
 * A {@link Measurement} service enabled from {@link com.vnet.camelrest.Routes}.
 */

public class MeasurementService {

    @BeanInject
    private LoincService loincService;

    @BeanInject
    private DBService dbService;

    /**
     * Get Measurement by id
     * @param id identifier
     * @return Measurement
     */
    public Measurement get(String id) throws ItemException {
        final String sql = "select * from measurement where id='" + id + "'";
        try {
            final List<Properties> items = dbService.query(sql);
            if (items.size()==0)
                throw new ItemNotFoundException(id);

            return transform(items.get(0));
        } catch (SQLException e) {
            throw new DBException(sql,e.getMessage());
        }
    }

    /**
     * Delete measurement
     * @param id identifier
     * @throws ItemException
     */
    public void delete(String id) throws ItemException {
        final String sql = "delete from measurement where id='" + id + "'";
        try {
            if (dbService.update(sql) == 0)
                throw new ItemNotFoundException(id);
        } catch (SQLException e) {
            throw new DBException(sql,e.getMessage());
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
            DBService.parse(item.getProperty("captureTimestamp")),
            DBService.parse(item.getProperty("creationTimestamp")),
            item.getProperty("note"));
    }

     private void check(Measurement measurement) throws ItemException {
         if (Utils.notSet(measurement.getId())) throw new MissingValueException("measurement.id");
         if (Utils.notSet(measurement.getCode())) throw new MissingValueException("measurement.code");
         if (Utils.notSet(measurement.getPatientId())) throw new MissingValueException("measurement.patientId");
         if (Utils.notSet(measurement.getDeviceId())) throw new MissingValueException("measurement.deviceId");
         if (Utils.notSet(measurement.getUnit())) throw new MissingValueException("measurement.unit");

         if (measurement.getCaptureTimestamp()==null)
             measurement.setCaptureTimestamp(new Date());
         if (measurement.getCreationTimestamp()==null)
             measurement.setCreationTimestamp(new Date());

         final Loinc loinc = loincService.getCode(measurement.getCode());

         if (Utils.notSet(measurement.getNote()) && !Utils.notSet(loinc.getLongCommonName()))
             measurement.setNote(loinc.getLongCommonName());
    }

    private int getLimit(String last) throws ItemException {
        if (Utils.notSet(last))
            return 10;

        try {
            return Integer.parseInt(last);
        } catch (NumberFormatException e) {
            throw new SearchItemException(last, "Invalid limit value");
        }
    }

    /**
     * List all measurements
     * @return the list of all measurements
     */
    public Collection<Measurement> list(String last) throws ItemException {
        final String sql = "select * from measurement order by captureTimestamp desc limit " + getLimit(last);
        try {
            final List<Properties> items = dbService.query(sql);
            final Collection<Measurement> measurements = new LinkedList<>();
            measurements.addAll(items.stream().map(MeasurementService::transform).collect(Collectors.toList()));
            return measurements;
        } catch (SQLException e) {
            throw new DBException(sql,e.getMessage());
        }
    }

    /**
     * Upload Measurement
     * @param measurement measurement
     */
    public Measurement upload(Measurement measurement) throws ItemException {
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
            .append(",'").append(DBService.formatMySqlTimestamp(measurement.getCaptureTimestamp())).append("'")
            .append(",'").append(DBService.formatMySqlTimestamp(new Date())).append("'");
        if (measurement.getNote()!=null)
            sb.append(",'").append(measurement.getNote()).append("')");
        else
            sb.append("null)");
        final String sql = sb.toString();
        try {
            dbService.update(sql);
            return measurement;
        } catch (SQLException e) {
            throw new DBException(sql,e.getMessage());
        }
    }
}