package com.benwunet.user.ui.bean;

import java.util.List;
/**
 * @Package: com.benwunet.user.ui.bean
 * @ClassName: MeSafeBean
 * @Description: 个人安全资料
 * @Author: feng
 * @CreateDate: 2020/11/11 0011 11:15
 * @Version: 1.0
 */
public class MeSafeBean {

    /**
     * email : wf2016@bw.com
     * isAuth : false
     * isFaceId : false
     * mobile : 138****2547
     * thirdPlatform : []
     */

    private String email;
    private boolean isAuth;
    private boolean isFaceId;
    private String mobile;
    private List<?> thirdPlatform;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsAuth() {
        return isAuth;
    }

    public void setIsAuth(boolean isAuth) {
        this.isAuth = isAuth;
    }

    public boolean isIsFaceId() {
        return isFaceId;
    }

    public void setIsFaceId(boolean isFaceId) {
        this.isFaceId = isFaceId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<?> getThirdPlatform() {
        return thirdPlatform;
    }

    public void setThirdPlatform(List<?> thirdPlatform) {
        this.thirdPlatform = thirdPlatform;
    }
}
