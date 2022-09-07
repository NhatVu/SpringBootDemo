package com.example.demo.helper;

import java.util.HashMap;
import java.util.Map;

public class APIResponseUtils {

    private static <T> Map<String, Object> buildApiResponse(int status, T data, T error){
        Map<String, Object> res = new HashMap<>();
        if (status < 0){
            // error
            res.put("status", status);
            res.put("error", error);
        }else{
            res.put("status", status);
            res.put("data", data);
        }
        return res;
    }

    public static <T> Map<String, Object> buildAPISuccess(int status, T data){
        return buildApiResponse(status, data, null);
    }

    public static <T> Map<String, Object> buildAPIError(int status, T error){
        return buildApiResponse(status, null, error);
    }
}
