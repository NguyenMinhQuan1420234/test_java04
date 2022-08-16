package com.nashtech.assetmanagement.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.lang.reflect.Type;
import com.nashtech.assetmanagement.exceptions.ParsingJsonException;

public class JsonUtil {

    public static JsonObject readJsonFile(String filePath) {
//        Convert JSON File to Java JsonObject
        Type jsonObjectType = new TypeToken<JsonObject>() {}.getType();
        JsonObject jsonObject;
        Gson gson = new Gson();
        JsonReader jsonReader;
        try {
            jsonReader = new JsonReader(new FileReader(filePath));
            jsonObject = gson.fromJson(jsonReader, jsonObjectType);
        } catch (Exception e) {
            throw new ParsingJsonException("Can't parsing file to jsonObject", e);
        }
        return jsonObject;
    }

    public static JsonArray readJsonFileToArray(String filePath) {
//        Convert JSON File to Java JsonObject
        Type jsonObjectType = new TypeToken<JsonObject>() {}.getType();
        JsonArray jsonArray;
        Gson gson = new Gson();
        JsonReader jsonReader;
        try {
            jsonReader = new JsonReader(new FileReader(filePath));
            jsonArray = gson.fromJson(jsonReader, jsonObjectType);
        } catch (Exception e) {
            throw new ParsingJsonException("Can't parsing file to jsonObject", e);
        }
        return jsonArray;
    }
}