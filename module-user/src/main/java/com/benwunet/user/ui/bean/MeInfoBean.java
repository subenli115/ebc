package com.benwunet.user.ui.bean;

/**
 * @Package: com.benwunet.user.ui.bean
 * @ClassName: MeInfoBean
 * @Description: 个人资料数据
 * @Author: feng
 * @CreateDate: 2020/11/11 0011 11:15
 * @Version: 1.0
 */


public class MeInfoBean {


    /**
     * areaCityId : 1001
     * areaCityName : 1001
     * areaDistrictId : 1001
     * areaDistrictName : 1001
     * areaProvinceId : 1001
     * areaProvinceName : 1001
     * birthday : 2020-06-17
     * gender : 1
     * memberId : 000016
     * memberSign : 这个人很懒，什么也没留下。
     */

    private int areaCityId;
    private String areaCityName;
    private int areaDistrictId;
    private String areaDistrictName;
    private int areaProvinceId;
    private String areaProvinceName;
    private String birthday;
    private int gender;
    private String memberId;
    private String memberSign;

    public int getAreaCityId() {
        return areaCityId;
    }

    public void setAreaCityId(int areaCityId) {
        this.areaCityId = areaCityId;
    }

    public String getAreaCityName() {
        return areaCityName;
    }

    public void setAreaCityName(String areaCityName) {
        this.areaCityName = areaCityName;
    }

    public int getAreaDistrictId() {
        return areaDistrictId;
    }

    public void setAreaDistrictId(int areaDistrictId) {
        this.areaDistrictId = areaDistrictId;
    }

    public String getAreaDistrictName() {
        return areaDistrictName;
    }

    public void setAreaDistrictName(String areaDistrictName) {
        this.areaDistrictName = areaDistrictName;
    }

    public int getAreaProvinceId() {
        return areaProvinceId;
    }

    public void setAreaProvinceId(int areaProvinceId) {
        this.areaProvinceId = areaProvinceId;
    }

    public String getAreaProvinceName() {
        return areaProvinceName;
    }

    public void setAreaProvinceName(String areaProvinceName) {
        this.areaProvinceName = areaProvinceName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberSign() {
        return memberSign;
    }

    public void setMemberSign(String memberSign) {
        this.memberSign = memberSign;
    }
}
