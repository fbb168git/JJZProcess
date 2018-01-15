package com.fbb.jjz.bean;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ApplyParams {
    public String userid;
    public String carid;

    public String licenseno;
    public String cartypecode;// 01 大型汽车 02小型汽车 03使馆汽车 04领馆汽车 05境外汽车 06外国汽车 26香港入出境车 27澳门入出境车
    public String envGrade;
    public String carmodel;// 车辆品牌型号
    public String carregtime;// 车辆注册日期 yyyy-MM-dd
    public String engineno;// 发动机号
    public String vehicletype;// 03 小型客车 04 小型货车 02 大型货车 52 小型新能源汽车 51 大型新能源汽车

    public String drivername;// 驾驶员姓名
    public String driverlicenseno;// 驾驶证号
    public String drivingphoto = "";
    public String carphoto = "";
    public String driverphoto = "";
    public String personphoto = "";

    public String applyid = "";
    public String applyflag = "";

    public ApplyParams() {
        super();
    }


    public ApplyParams(ApplyCarInfo carInfo) {
        super();
        this.carmodel = carInfo.carmodel;
        this.carregtime = carInfo.carregtime;
        this.engineno = carInfo.engineno;
        this.vehicletype = carInfo.vehicletype;
        this.drivername = carInfo.drivername;
        this.driverlicenseno = carInfo.driverlicenseno;
    }

    public void format() {
        try {
            licenseno = URLEncoder.encode(licenseno, "UTF-8");
            drivername = URLEncoder.encode(drivername, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void fillCarInfo(Car car) {
        userid = car.userid;
        carid = car.carid;
        licenseno = car.licenseno;
        cartypecode = car.cartype;
        envGrade = car.env_grade;
        carmodel = car.carmodel;
        carregtime = car.carregtime;
        engineno = car.engineno;
        vehicletype = car.vehicletype;
    }

    public void fillPersonInfo(EnterPerson person) {
        drivername = person.getDrivername();
        driverlicenseno = person.getDriverlicenseno();
    }
    

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        String dot = "\n";
        buffer.append("userid=").append(userid).append(dot);
        buffer.append("carid=").append(carid).append(dot);
        buffer.append("licenseno=").append(licenseno).append(dot);
        buffer.append("cartypecode=").append(cartypecode).append(dot);
        buffer.append("envGrade=").append(envGrade).append(dot);
        buffer.append("carmodel=").append(carmodel).append(dot);
        buffer.append("carregtime=").append(carregtime).append(dot);
        buffer.append("engineno=").append(engineno).append(dot);
        buffer.append("vehicletype=").append(vehicletype).append(dot);
        buffer.append("drivername=").append(drivername).append(dot);
        buffer.append("driverlicenseno=").append(driverlicenseno).append(dot);
        buffer.append("applyid=").append(applyid).append(dot);
        buffer.append("applyflag=").append(applyflag);
        return buffer.toString();
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
