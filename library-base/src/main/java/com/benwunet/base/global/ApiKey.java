package com.benwunet.base.global;

/**
 * @Package: com.benwunet.base.global
 * @ClassName: ApiKey
 * @Description: api接口常量
 * @Author: feng
 * @CreateDate: 2020/10/20 0020 17:41
 * @Version: 1.0
 */

public interface ApiKey {

    /**
     * 获取验证码
     */
    String NOTIFY_SMS = "notify/sms";

    /**
     * 短信登录
     */
    String OAUTH_SMS = "oauth/sms";

    /**
     * 注册
     */
    String MEMBER_REG = "member/reg";

    /**
     * 密码登录
     */
    String OAUTH_PASS = "oauth/pass";

    /**
     *  人脸token
     */
    String OAUTH_FACE_TOKEN = "oauth/face/token";

    /**
     *  人脸登录
     */
    String OAUTH_FACE = "oauth/face";

    /**
     *  找回密码
     */
    String MEMBER_RES = "member/res";


    /**
     *  职称数据
     */
    String SYS_POSITION = "sys/position";

    /**
     *  行业数据
     */
    String SYS_INDUSTRY = "sys/industry";

    /**
     *  热门话题
     */
    String CIRCLE_TOPIC_HOT = "circle/topic/hot";


    /**
     *  最新话题
     */
    String CIRCLE_TOPIC = "circle/topic";


    /**
     *  完善信息
     */
    String MEMBER_COMPLETE_INFORMATION = "member/complete/information";

    /**
     *  上传文件
     */
    String OSS_FILE_ANON = "oss/file-anon";

    /**
     *  获取用户信息
     */
    String MEMBER_CURRENT = "member/current";

    /**
     *  个人中心首页
     */
    String MEMBER_HOME = "member/home";

    /**
     *  个人资料-查询修改
     */
    String MEMBER_SETTINGS_PERSONAL = "member/settings/personal";


    /**
     *  名片收藏列表
     */
    String MEMBER_COLLECTION_CARD = "member/collection/card";
}
