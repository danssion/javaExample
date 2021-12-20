package com.danssion.java.baseType.transfer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public class jsonObj {
    public static void main(String[] args) {
        //map <= => jsonObject
        JSONObject itemJSONObj = new JSONObject();
        Map<String, Object> itemMap = JSONObject.toJavaObject(itemJSONObj, Map.class);
        itemJSONObj = JSONObject.parseObject(JSON.toJSONString(itemMap));


    }
}
