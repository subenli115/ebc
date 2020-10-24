package com.benwunet.sign.ui.bean;

/**
 * @Package: com.benwunet.sign.ui.bean
 * @ClassName: UserBean
 * @Description: 用户登录数据
 * @Author: feng
 * @CreateDate: 2020/10/22 0022 09:58
 * @Version: 1.0
 */


public class UserLoginBean {


    /**
     * access_token : c0348a10-ea4c-43f1-871d-8f97a6d87329
     * token_type : bearer
     * refresh_token : 5c4d12bc-555a-44ad-a918-b021b0255a15
     * expires_in : 1728000
     * scope : APP
     */

    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
