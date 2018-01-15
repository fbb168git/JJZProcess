package com.fbb.jjz.util;

public class TextUtil {
    public static boolean isEmpty(String text){
        if(text == null || text.equalsIgnoreCase("")){
            return true;
        }
        return false;
    }
}
