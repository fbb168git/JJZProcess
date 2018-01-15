package com.fbb.jjz.bean;

public class ApplyCarInfo {
    public String carmodel;//车辆品牌型号
    public String carregtime;//车辆注册日期 yyyy-MM-dd
    public String engineno;//发动机号
    public String vehicletype;//03 小型客车 04 小型货车 02 大型货车 52 小型新能源汽车 51 大型新能源汽车
    public String drivername;//驾驶员姓名
    public String driverlicenseno;//驾驶证号
    
    //未添加过汽车的账号需要添加以下参数
    public String licenseno;//车牌号
    public String cartypecode;//大型汽车 02小型汽车 使馆汽车 领馆汽车 境外汽车 外国汽车 香港入出汽车 澳门入出汽车
    
    public ApplyCarInfo() {
        super();
    }
    
    public ApplyCarInfo(String carmodel, String carregtime, String engineno, String vehicletype, String drivername, String driverlicenseno) {
        super();
        this.carmodel = carmodel;
        this.carregtime = carregtime;
        this.engineno = engineno;
        this.vehicletype = vehicletype;
        this.drivername = drivername;
        this.driverlicenseno = driverlicenseno;
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
