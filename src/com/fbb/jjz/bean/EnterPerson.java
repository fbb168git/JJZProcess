package com.fbb.jjz.bean;

import java.util.Date;

public class EnterPerson {
    
    private String userid;
    private String carid;
    private String driverlicenseno;
    private String drivername;
    private String drivingphoto;
    private String carphoto;
    private String driverphoto;
    private String personphoto;
    private Date create_time;
    private Date update_time;
    
    
    public EnterPerson() {
        super();
    }
    
    
    public EnterPerson(String userid, String carid, String driverlicenseno, String drivername, String drivingphoto, String carphoto, String driverphoto, String personphoto) {
        super();
        this.userid = userid;
        this.carid = carid;
        this.driverlicenseno = driverlicenseno;
        this.drivername = drivername;
        this.drivingphoto = drivingphoto;
        this.carphoto = carphoto;
        this.driverphoto = driverphoto;
        this.personphoto = personphoto;
    }


    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getCarid() {
        return carid;
    }
    public void setCarid(String carid) {
        this.carid = carid;
    }
    public String getDriverlicenseno() {
        return driverlicenseno;
    }
    public void setDriverlicenseno(String driverlicenseno) {
        this.driverlicenseno = driverlicenseno;
    }
    public String getDrivername() {
        return drivername;
    }
    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }
    public String getDrivingphoto() {
        return drivingphoto;
    }
    public void setDrivingphoto(String drivingphoto) {
        this.drivingphoto = drivingphoto;
    }
    public String getCarphoto() {
        return carphoto;
    }
    public void setCarphoto(String carphoto) {
        this.carphoto = carphoto;
    }
    public String getDriverphoto() {
        return driverphoto;
    }
    public void setDriverphoto(String driverphoto) {
        this.driverphoto = driverphoto;
    }
    public String getPersonphoto() {
        return personphoto;
    }
    public void setPersonphoto(String personphoto) {
        this.personphoto = personphoto;
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
