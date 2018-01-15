package com.fbb.jjz.bean;

import java.util.ArrayList;

import com.fbb.jjz.util.JsonUtil;

public class EnterCarList {
    
//  {"datalist":[{"carid":"643359","userid":"0719afcd79e847c8abfb4c6d0cf8b02b","licenseno":"豫HFB768","cartype":"02",
//    "applyflag":"1","applyid":"","carapplyarr":[]}],"rescode":"200","resdes":"获取申请信息列表成功"}
//  {"datalist":[{"carid":"643359","userid":"0719afcd79e847c8abfb4c6d0cf8b02b","licenseno":"豫HFB768","cartype":"02",
//    "applyflag":"0",
//    "carapplyarr":[{"applyid":"026201712262357256315849","carid":"643359","cartype":"02","engineno":"Q042607","enterbjend":"2018-01-02","enterbjstart":"2017-12-27","existpaper":"","licenseno":"豫HFB768","loadpapermethod":"","remark":"e85f0d98bc8627c57f43afbb5d686737","status":"1","syscode":"","syscodedesc":"","userid":"0719afcd79e847c8abfb4c6d0cf8b02b"}]
//    }],"rescode":"200","resdes":"获取申请信息列表成功"}
    private ArrayList<ApplyEnterCar> datalist;
    private String rescode;
    private String resdes;
    
    public static EnterCarList fromJson(String json) {
        EnterCarList result = JsonUtil.fromJson(json, EnterCarList.class);
        return result;
    }
    
    public ArrayList<ApplyEnterCar> getDatalist() {
        return datalist;
    }
    public void setDatalist(ArrayList<ApplyEnterCar> datalist) {
        this.datalist = datalist;
    }
    public String getRescode() {
        return rescode;
    }
    public void setRescode(String rescode) {
        this.rescode = rescode;
    }
    public String getResdes() {
        return resdes;
    }
    public void setResdes(String resdes) {
        this.resdes = resdes;
    }
    
    static String testText = "{\"datalist\":[{\"carid\":\"643359\",\"userid\":\"0719afcd79e847c8abfb4c6d0cf8b02b\",\"licenseno\":\"豫HFB768\",\"cartype\":\"02\",\"applyflag\":\"1\",\"applyid\":\"\",\"carapplyarr\":[]}],\"rescode\":\"200\",\"resdes\":\"获取申请信息列表成功\"}";
    static String testText2 = "{\"datalist\":[],\"rescode\":\"200\",\"resdes\":\"获取申请信息列表成功\"}";
    public static void main(String[] args) {
        EnterCarList carList = fromJson(testText2);
        System.out.print(carList.getResdes());
    }
    
    
}
