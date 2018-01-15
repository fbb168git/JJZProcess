package com.fbb.jjz.bean;

import java.util.ArrayList;


public class ApplyEnterCar {
//    {"datalist":[{"carid":"643359","userid":"0719afcd79e847c8abfb4c6d0cf8b02b","licenseno":"豫HFB768","cartype":"02","applyflag":"1","applyid":"","carapplyarr":[]}],"rescode":"200","resdes":"获取申请信息列表成功"}
//   {"datalist":[{"carid":"643359","userid":"0719afcd79e847c8abfb4c6d0cf8b02b","licenseno":"豫HFB768","cartype":"02","applyflag":"0","carapplyarr":[{"applyid":"026201712262357256315849","carid":"643359","cartype":"02","engineno":"Q042607","enterbjend":"2018-01-02","enterbjstart":"2017-12-27","existpaper":"","licenseno":"豫HFB768","loadpapermethod":"","remark":"e85f0d98bc8627c57f43afbb5d686737","status":"1","syscode":"","syscodedesc":"","userid":"0719afcd79e847c8abfb4c6d0cf8b02b"}]}],"rescode":"200","resdes":"获取申请信息列表成功"}
//    {"datalist":[{"carid":"19422944","userid":"0E0CDBD816C846A9A9BF71AA57C94851","licenseno":"豫UK9852","cartype":"02","applyflag":"0","carapplyarr":[{"applyid":"026201712281413149422547","carid":"19422944","cartype":"02","engineno":"DL0234","enterbjend":"2017-12-30","enterbjstart":"2017-12-29","existpaper":"","licenseno":"豫UK9852","loadpapermethod":"","remark":"92555ebfab1ad5eae3c11d86674f7295","status":"2","syscode":"","syscodedesc":"","userid":"0E0CDBD816C846A9A9BF71AA57C94851"}]}],"rescode":"200","resdes":"获取申请信息列表成功"}

    public class ApplyInfo {//车辆申请信息
// 2017-12-27 15:54:20请求       {"applyid":"026201712262357256315849","carid":"643359","cartype":"02","engineno":"Q042607","enterbjend":"2018-01-02","enterbjstart":"2017-12-27","existpaper":"","licenseno":"豫HFB768","loadpapermethod":"","remark":"e85f0d98bc8627c57f43afbb5d686737","status":"1","syscode":"","syscodedesc":"","userid":"0719afcd79e847c8abfb4c6d0cf8b02b"}
        public String applyid;//"026201712262357256315849"
        public String carid;//"643359"
        public String cartype;//"02"
        public String engineno;//发动机号 "Q042607" 
        public String enterbjend;//出京日期 "2018-01-02"
        public String enterbjstart;//进京日期 "2017-12-27" 
        public String existpaper;//"" 是否是纸质的？
        public String licenseno;//车牌号 "豫HFB768" 
        public String loadpapermethod;//""
        public String remark;//"e85f0d98bc8627c57f43afbb5d686737"
        public String status;//"0"审核失败 "1"审核成功  "2"审核中
        public String syscode;//""
        public String syscodedesc;//""
        public String userid;//"0719afcd79e847c8abfb4c6d0cf8b02b"
    }
    
    private String carid;//"643359"
    private String userid;//"0719afcd79e847c8abfb4c6d0cf8b02b"
    private String licenseno;//"豫HFB768"
    private String cartype;//"02" TODO 其他值及含义
    private String applyflag;//"1"可办理 "0"不可办理
    private String applyid;//""
    private ArrayList<ApplyInfo> carapplyarr;//[]
    
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
    public String getCartype() {
        return cartype;
    }
    public void setCartype(String cartype) {
        this.cartype = cartype;
    }
    public String getApplyflag() {
        return applyflag;
    }
    public void setApplyflag(String applyflag) {
        this.applyflag = applyflag;
    }
    public String getApplyid() {
        return applyid;
    }
    public void setApplyid(String applyid) {
        this.applyid = applyid;
    }
    public ArrayList<ApplyInfo> getCarapplyarr() {
        return carapplyarr;
    }
    public void setCarapplyarr(ArrayList<ApplyInfo> carapplyarr) {
        this.carapplyarr = carapplyarr;
    }
}
