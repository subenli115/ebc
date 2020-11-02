package com.benwunet.sign.ui.bean;

/**
 * @Package: com.benwunet.sign.ui.bean
 * @ClassName: UserBean
 * @Description: java类作用描述
 * @Author: feng
 * @CreateDate: 2020/10/30 0030 17:48
 * @Version: 1.0
 */


public class UserBean {

    /**
     * memberId : 100003
     * levelId : null
     * vipState : 0
     * isFirstLogin : true
     * avatar :
     * realName : WANGL
     * realNameEn : null
     * mobile : 18508807793
     * password : $2a$10$An2wjGY.Xa3UnBfXbnJKTOYi7MOaCcqcexunlgy8WaFiPA1ff2r4G
     * birthday : null
     * gender :
     * email : null
     * areaProvinceId : null
     * areaCityId : null
     * areaDistrictId : null
     * memberSign : null
     * pushCid : 07a6c9dd4dc32b1e7caf2d5a0662b76a
     */

    private String memberId;
    private Object levelId;
    private String vipState;
    private boolean isFirstLogin;
    private String avatar;
    private String realName;
    private Object realNameEn;
    private String mobile;
    private String password;
    private Object birthday;
    private String gender;
    private Object email;
    private Object areaProvinceId;
    private Object areaCityId;
    private Object areaDistrictId;
    private Object memberSign;
    private String pushCid;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Object getLevelId() {
        return levelId;
    }

    public void setLevelId(Object levelId) {
        this.levelId = levelId;
    }

    public String getVipState() {
        return vipState;
    }

    public void setVipState(String vipState) {
        this.vipState = vipState;
    }

    public boolean isIsFirstLogin() {
        return isFirstLogin;
    }

    public void setIsFirstLogin(boolean isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Object getRealNameEn() {
        return realNameEn;
    }

    public void setRealNameEn(Object realNameEn) {
        this.realNameEn = realNameEn;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(Object birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getAreaProvinceId() {
        return areaProvinceId;
    }

    public void setAreaProvinceId(Object areaProvinceId) {
        this.areaProvinceId = areaProvinceId;
    }

    public Object getAreaCityId() {
        return areaCityId;
    }

    public void setAreaCityId(Object areaCityId) {
        this.areaCityId = areaCityId;
    }

    public Object getAreaDistrictId() {
        return areaDistrictId;
    }

    public void setAreaDistrictId(Object areaDistrictId) {
        this.areaDistrictId = areaDistrictId;
    }

    public Object getMemberSign() {
        return memberSign;
    }

    public void setMemberSign(Object memberSign) {
        this.memberSign = memberSign;
    }

    public String getPushCid() {
        return pushCid;
    }

    public void setPushCid(String pushCid) {
        this.pushCid = pushCid;
    }
}
