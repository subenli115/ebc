package com.benwunet.base.router;

/**
 * 用于组件开发中，ARouter多Fragment跳转的统一路径注册
 * 在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 * Created by feng on 2020/10/15.
 */

public class RouterFragmentPath {
    /**
     * 首页组件
     */
    public static class Home {
        private static final String HOME = "/home";
        /*首页*/
        public static final String PAGER_HOME = HOME + "/Home";
    }

    /**
     * 名片夹组件
     */
    public static class Card {
        private static final String Card = "/card";
        /*名片夹*/
        public static final String PAGER_CARD = Card + "/Card";
    }

    /**
     * 消息组件
     */
    public static class Msg {
        private static final String MSG = "/msg";
        /*消息*/
        public static final String PAGER_MSG = MSG + "/Msg";
    }

    /**
     * 发现组件
     */
    public static class Find {
        private static final String FIND = "/find";
        /*消息*/
        public static final String PAGER_FIND = FIND + "/Find";
    }

    /**
     * 用户组件
     */
    public static class User {
        private static final String USER = "/user";
        /*我的*/
        public static final String PAGER_ME = USER + "/Me";
    }


}
