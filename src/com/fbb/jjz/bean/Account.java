package com.fbb.jjz.bean;

import java.util.Date;

public class Account {
    private String userid;
    private String phone;
    private String name;
    private String sex;
    private String headurl;
    private Date create_time;
    private Date update_time;
    
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getHeadurl() {
        return headurl;
    }
    public void setHeadurl(String headurl) {
        this.headurl = headurl;
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
