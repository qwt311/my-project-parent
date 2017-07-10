package com.project.common.util;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by qiaowentao on 2017/2/21.
 */
public class JsonUtils {

    /**
     *对象转换成json串
     * @param obj
     * @return
     */
    public static String Object2Json(Object obj) {
        // TODO Auto-generated method stub
        return JSON.toJSONString(obj);
    }

    /**
     *json串转换成对象
     * @param <T>
     * @param json
     * @return
     */
    public static <T> Object Json2Object(String json, Class<T> clazz) {
        // TODO Auto-generated method stub
        return JSON.parseObject(json, clazz);
    }

    /**
     * json串转换成List集合
     * @param json
     * @param clazz
     * @return
     */
    public static <T> List<T> Json2List(String json, Class<T> clazz){
        return JSON.parseArray(json, clazz);
    }

}
