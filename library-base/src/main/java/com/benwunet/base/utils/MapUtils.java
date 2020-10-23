package com.benwunet.base.utils;

import android.text.TextUtils;


import java.util.HashMap;
import java.util.Map;

/**
 * author feng
 * create  2017/3/31 10
 * des     网络请求参数的封装
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */



public class MapUtils {

    //登录需要的map数据
    public static Map<String, String> getLoginMap(String phone, String password) {
        Map<String, String> map = getDefMap(false);
        put("mobile", phone, map);
        put("password", password, map);
        return map;
    }
        //获取验证码需要的map数据
    public static Map<String, String> getCodeMap(String code, String mobile) {
        Map<String, String> map = getDefMap(false);
        put("code", code, map);
        put("mobile", mobile, map);
        return map;
    }

    //将数据添加到map
    public static Map<String, String> put(String key, String value, Map<String, String> map) {
        if (!TextUtils.isEmpty(value)) {
            if ("password".equals(key)) {
                map.put(key, RSAUtils.encrypt(value));
            } else {
                map.put(key, value);
            }
        }
        return map;
    }

    public static Map<String, String> getDefMap(boolean needToken) {
        Map<String, String> map = new HashMap<>();
//        if (needToken && EmptyUtils.isNotEmpty( MyAPP.getAccessToken())) {
//            map.put("accessToken", MyAPP.getAccessToken());
//        }
        return map;
    }


}
