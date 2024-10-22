package com.benwunet.sign.ui.source;

/**
 * Created by goldze on 2019/3/26.
 */
public interface LocalDataSource {
    /**
     * 保存用户名
     */
    void saveUserName(String userName);

    /**
     * 保存用户密码
     */

    void savePassword(String password);

    /**
     * 保存token
     */

    void saveToken(String token);

    /**
     * 保存token
     */

    void saveRefreshToken(String reToken);

    /**
     * 获取用户名
     */
    String getUserName();

    /**
     * 获取用户密码
     */
    String getPassword();

    /**
     * 获取token
     */
    String getToken();



}
