package com.vnet.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Measurement {

    private String id;
    private String patientId;
    private String deviceId;
    private String code;
    private String unit;
    private float value;
    private boolean manual;
    private Date captureTimestamp;
    private Date creationTimestamp;
    private String note;

    public Measurement() {
    }

    public Measurement(String id, String patientId, String deviceId, String code, String unit, float value, boolean manual, Date captureTimestamp, Date creationTimestamp, String note) {
        this.id = id;
        this.patientId = patientId;
        this.deviceId = deviceId;
        this.code = code;
        this.unit = unit;
        this.value = value;
        this.manual = manual;
        this.captureTimestamp = captureTimestamp;
        this.creationTimestamp = creationTimestamp;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isManual() {
        return manual;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }

    public Date getCaptureTimestamp() {
        return captureTimestamp;
    }

    public void setCaptureTimestamp(Date captureTimestamp) {
        this.captureTimestamp = captureTimestamp;
    }

    public Date getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Date creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
