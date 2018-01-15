package com.fbb.jjz.util;

import java.util.Arrays;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class CipherUtil {
    private String timestamp;
    
    public String sortSignParameters(Map<String, String> paramMap) {
        timestamp = DateUtil.getDateTime();
        paramMap.put("timestamp", timestamp);
        StringBuilder localStringBuilder = new StringBuilder();
        String[] arrayOfString = (String[]) paramMap.keySet().toArray(new String[0]);
        Arrays.sort(arrayOfString);
        for(int i =0;i<arrayOfString.length;i++){
            String str1 = arrayOfString[i];
            String str2 = (String) paramMap.get(str1);
            if ((str1.trim().equals("")) || (str2 == null) || (str2.trim().equals(""))) {
            } else {
                localStringBuilder.append(str1).append(str2);
            }
        }
        return new SignUtil().sign(localStringBuilder.toString());
    }

    public JSONObject addCommonParameters(JSONObject paramJSONObject) {
        try {
            paramJSONObject.put("timestamp", timestamp);
            paramJSONObject.put("platform", "02");
            return paramJSONObject;
        } catch (JSONException localJSONException) {
            localJSONException.printStackTrace();
        }
        return paramJSONObject;
    }
}
