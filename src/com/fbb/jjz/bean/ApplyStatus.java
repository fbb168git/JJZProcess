package com.fbb.jjz.bean;

import java.util.Date;


public class ApplyStatus {
//    "0"审核失败 "1"审核成功  "2"审核中 "3"请求失败 "4"未申请
    public static final String APPLY_STAUS_VERIFY_FAIL = "0";
    public static final String APPLY_STAUS_VERI️FY_SUCCESS = "1";
    public static final String APPLY_STAUS_VERIFY_ING = "2";
    public static final String APPLY_STAUS_REQ_FAIL = "3";
    public static final String APPLY_STAUS_UNDO = "4";
    
    public String userid;
    public String carid;
    public String apply_status;
    public Date enterbjstart;
    public Date enterbjend;
    public Date apply_time;
    public Date create_time;
    public Date update_time;
    
    public ApplyStatus(String userid, String carid, String apply_staus, Date enterbjstart, Date enterbjend, Date apply_time) {
        super();
        this.userid = userid;
        this.carid = carid;
        this.apply_status = apply_staus;
        this.enterbjstart = enterbjstart;
        this.enterbjend = enterbjend;
        this.apply_time = apply_time;
    }

    public ApplyStatus() {
        // TODO Auto-generated constructor stub
    }
    
    
}
