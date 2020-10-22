package com.benwunet.sign.ui.bean;

import com.google.gson.annotations.SerializedName;
import com.zhouyou.http.model.ApiResult;

/**
 * @Package: com.benwunet.sign.ui.bean
 * @ClassName: codeResult
 * @Description: java类作用描述
 * @Author: feng
 * @CreateDate: 2020/10/20 0020 18:05
 * @Version: 1.0
 */


public class VerifyCodeBean {

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private int code;
    private String data;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                '}';
    }
}
