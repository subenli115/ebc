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


}
