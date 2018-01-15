package com.fbb.jjz.bean;

import java.util.Date;

import com.fbb.jjz.util.TextUtil;

public class Car {
    
    public String carid;
    public String userid;
    public String licenseno;//'车牌号',
    public String engineno;//发动机号',
    public String carmodel;//'车辆品牌型号',
    public String carregtime;//车辆注册日期 yyyy-MM-dd',
    public String vehicletype;//'03 小型客车 04 小型货车 02 大型货车 52 小型新能源汽车 51 大型新能源汽车',
    public String cartype;//01 大型汽车 02//小型汽车 03使馆汽车 04领馆汽车 05境外汽车 06外国汽车 26香港入出境车 27澳门入出境车
    public String env_grade;//
    public Date create_time;
    public Date update_time;
    
    
    public Car() {
        super();
    }
    
    public Car(String carid, String userid, String licenseno, String engineno, String carmodel, String carregtime, String vehicletype, String cartype,String env_grade) {
        super();
        this.carid = carid;
        this.userid = userid;
        this.licenseno = licenseno;
        this.engineno = engineno;
        this.carmodel = carmodel;
        this.carregtime = carregtime;
        this.vehicletype = vehicletype;
        this.cartype = cartype;
        this.env_grade = env_grade;
    }

    public boolean hasEmptyValue(){
        if(TextUtil.isEmpty(carid)) return true;
        if(TextUtil.isEmpty(userid)) return true;
        if(TextUtil.isEmpty(licenseno)) return true;
        if(TextUtil.isEmpty(engineno)) return true;
        if(TextUtil.isEmpty(carmodel)) return true;
        if(TextUtil.isEmpty(carregtime)) return true;
        if(TextUtil.isEmpty(vehicletype)) return true;
        if(TextUtil.isEmpty(cartype)) return true;
        if(TextUtil.isEmpty(env_grade)) return true;
        return false;
    }
    public String getCarid() {
        return carid;
    }
    public void setCarid(String carid) {
        this.carid = carid;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getLicenseno() {
        return licenseno;
    }
    public void setLicenseno(String licenseno) {
        this.licenseno = licenseno;
    }
    public String getEngineno() {
        return engineno;
    }
    public void setEngineno(String engineno) {
        this.engineno = engineno;
    }
    public String getCarmodel() {
        return carmodel;
    }
    public void setCarmodel(String carmodel) {
        this.carmodel = carmodel;
    }
    public String getVehicletype() {
        return vehicletype;
    }
    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }
    public String getCartype() {
        return cartype;
    }
    public void setCartype(String cartype) {
        this.cartype = cartype;
    }
    public Date getUpdate_time() {
        return update_time;
    }
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
    public Date getCreate_time() {
        return create_time;
    }
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
    public String getCarregtime() {
        return carregtime;
    }
    public void setCarregtime(String carregtime) {
        this.carregtime = carregtime;
    }
    
    
     
}
