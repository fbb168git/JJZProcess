package com.fbb.jjz.bean;

import java.util.Date;


public class MemberInfo {
    public static final String MEMBER_TYPE_TRY = "0";
    public static final String MEMBER_TYPE_NORMAL = "1";
    
    private String memberid;
    private String member_type;
    private Date start_date;
    private Date end_date;
    private Date create_time;
    private Date update_time;
    
    public String getMemberid() {
        return memberid;
    }
    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }
    public String getMember_type() {
        return member_type;
    }
    public void setMember_type(String member_type) {
        this.member_type = member_type;
    }
    public Date getStart_date() {
        return start_date;
    }
    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }
    public Date getEnd_date() {
        return end_date;
    }
    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
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
