package com.fbb.jjz.util;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class JinJingAreaUtil {
    public static HashMap<String,String> areaName = new HashMap<String, String>();
    public static HashMap<String,String> rukouName = new HashMap<String, String>();
    
    public static String[] areaCodes = {"05","09","12","16","11","13","14","15","17","18"};
    public static HashMap<String,String[]> rukouCodes = new HashMap<String, String[]>();
    static {
        areaName.put("05", "朝阳");rukouName.put("19", "京沪高速");rukouName.put("12", "京哈高速");rukouName.put("74", "其他道路");
        areaName.put("09", "房山");
        areaName.put("12", "通州");
        areaName.put("16", "昌平");
        areaName.put("11", "大兴");
        areaName.put("13", "顺义");
        areaName.put("14", "平谷");
        areaName.put("15", "怀柔");
        areaName.put("17", "密云");
        areaName.put("18", "延庆");
        rukouCodes.put("05", new String[]{"19","12","74"}); 
        rukouCodes.put("09", new String[]{"03","14","16","42","43","44","45","46","47","78"}); 
        rukouCodes.put("12", new String[]{"24","04","02","48","49","53","50","51","52","81"}); 
        rukouCodes.put("16", new String[]{"13","54","85"}); 
        rukouCodes.put("11", new String[]{"56","57","06","58","05","59","26","60","80"}); 
        rukouCodes.put("13", new String[]{"61","62","63","20","21","82"}); 
        rukouCodes.put("14", new String[]{"22","25","11","23","27","83"}); 
        rukouCodes.put("15", new String[]{"10","64","84"}); 
        rukouCodes.put("17", new String[]{"65","66","67","29","28"}); 
        rukouCodes.put("18", new String[]{"07","17","18","68","69","70","71","87"});
    }
    
    public static String[] randomArea() {
        String[] result = new String[2];
       int index = new Random().nextInt(areaCodes.length);
       result[0] = areaCodes[index];
       String[] rukou = rukouCodes.get(result[0]);
       result[1] = rukou[new Random().nextInt(rukou.length)];
       return result;
    }
    
    public static void main(String[] args) {
        for(int i=0;i<1000;i++){
            String[] randomArea = randomArea();
            System.out.println(randomArea[0]+"|"+randomArea[1]);
        }
    }
}
