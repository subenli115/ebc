package com.benwunet.base.bean;


public class StringDataV2Bean {


    private boolean data;

    public boolean getData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private int code;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                '}';
    }
}
