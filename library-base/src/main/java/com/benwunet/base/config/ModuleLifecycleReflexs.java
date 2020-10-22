package com.benwunet.base.config;

/**
 * Created by feng on 2020/10/15.
 * 组件生命周期反射类名管理，在这里注册需要初始化的组件，通过反射动态调用各个组件的初始化方法
 * 注意：以下模块中初始化的Module类不能被混淆
 */

public class ModuleLifecycleReflexs {
    private static final String BaseInit = "com.benwunet.base.base.BaseModuleInit";
    //主业务模块
    private static final String MainInit = "com.benwunet.main.MainModuleInit";
    //登录验证模块
    private static final String SignInit = "com.benwunet.sign.SignModuleInit";
    //首页业务模块
    private static final String HomeInit = "com.benwunet.home.HomeModuleInit";
    //名片夹业务模块
    private static final String CardsInit = "com.benwunet.cards.CardsModuleInit";
    //消息业务模块
    private static final String MsgInit = "com.benwunet.msg.MsgModuleInit";
    //个人中心业务模块
    private static final String UserInit = "com.benwunet.user.UserModuleInit";
    //发现业务模块
    private static final String FindInit = "com.benwunet.find.FindModuleInit";

    public static String[] initModuleNames = {BaseInit, MainInit,SignInit, HomeInit, CardsInit, MsgInit,UserInit,FindInit};
}
