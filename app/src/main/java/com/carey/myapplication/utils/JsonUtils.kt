package com.carey.myapplication.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.lang.reflect.Type

/**
 * @author Carey
 * @version 2018/11/15
 */
object JsonUtils {
    private val sGson = Gson()

    /**
     * Json字符串转json对象
     *
     * @param json  Json字符串
     * @param clazz Json对象的class
     * @param <T>   Json对象的泛型
     * @return Json对象
    </T> */
    fun <T> getObjct(json: String, clazz: Class<T>): T {
        return sGson.fromJson(json, clazz)
    }

    /**
     * Json字符串转基本对象
     *
     * @param json Json字符串
     * @param type 数据的type
     * @param <T>  json对象的泛型
     * @return json对象
    </T> */
    fun <T> getObjct(json: String, type: Type): T {
        return sGson.fromJson(json, type)
    }

    /**
     * Json字符串转List对象
     * @param json Json字符串
     * @param type 数据的type
     * @param <T> json对象的泛型
     * @return json对象
     */
    fun <T> getObjct(json: String, type: TypeToken<List<T>>): List<T> {
        return sGson.fromJson(json, type.type)
    }

    /**
     * Json对象转Json字符串
     *
     * @param obj Json对象
     * @return Json字符串
     */
    fun getJsonString(obj: Any): String {
        return sGson.toJson(obj)
    }
}
