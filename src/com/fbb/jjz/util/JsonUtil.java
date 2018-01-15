package com.fbb.jjz.util;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {


    public static <T> String toJson(T obj) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(obj, new TypeToken<T>() {}.getType());
    }

    public static <T> String toJson(List<T> obj) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(obj, new TypeToken<List<T>>() {}.getType());
    }

    public static <T> T fromJson(String dataContentOfProtocal, Class<T> clazz) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.fromJson(dataContentOfProtocal, clazz);
    }

    public static <T> ArrayList<T> listFromJson(String dataContentOfProtocal, Class<T> clazz) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        ArrayList<T> lst = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(dataContentOfProtocal).getAsJsonArray();
        for (final JsonElement elem : array) {
            lst.add(gson.fromJson(elem, clazz));
        }
        return lst;
    }

    public static void main(String[] args) {}

}
