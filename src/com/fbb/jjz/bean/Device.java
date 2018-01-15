package com.fbb.jjz.bean;

import java.util.Date;

public class Device {
    private String deviceid;
    private String userid;
    private String imei;
    private String imsi;
    private String gpslon;
    private String gpslat;
    private Date create_time;
    private Date update_time;
    
    
    public String getDeviceid() {
        return deviceid;
    }
    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getImei() {
        return imei;
    }
    public void setImei(String imei) {
        this.imei = imei;
    }
    public String getImsi() {
        return imsi;
    }
    public void setImsi(String imsi) {
        this.imsi = imsi;
    }
    public String getGpslon() {
        return gpslon;
    }
    public void setGpslon(String gpslon) {
        this.gpslon = gpslon;
    }
    public String getGpslat() {
        return gpslat;
    }
    public void setGpslat(String gpslat) {
        this.gpslat = gpslat;
    }
    public Date getCreate_time() {
        return create_time;
    }
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
    public Date getUpdate_time() {
        return update_time;
    }
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
    
     
}
