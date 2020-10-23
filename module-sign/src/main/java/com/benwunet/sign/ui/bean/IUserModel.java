package com.benwunet.sign.ui.bean;

public interface IUserModel{
    void login(String phone, String verifyCode);
    void getVerifyCode(String phone);
}
