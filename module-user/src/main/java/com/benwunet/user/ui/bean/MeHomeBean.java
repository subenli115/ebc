package com.benwunet.user.ui.bean;

/**
 * @Package: com.benwunet.user.ui.bean
 * @ClassName: MeHomeBean
 * @Description: 个人中心数据
 * @Author: feng
 * @CreateDate: 2020/11/10 0010 11:02
 * @Version: 1.0
 */


public class MeHomeBean {


    /**
     * memberId : 100013
     * realName : 冯文骏
     * avatar : chengdu.aliyuncs.com/ebcd8251161514134f8d5db0c980597bb65.png
     * gender : 1
     * positionName : null
     * memberSign : null
     * backImg : chengdu.aliyuncs.com/ebc5bd98069e5b2dd08f70f24a2f5766647.jpg
     * leverName : 2
     * areaProvinceName : 重庆
     * areaCityName : 重庆市
     * areaDistrictName : null
     * contactsTotal : 0
     * attentionTotal : 0
     * fansTotal : 0
     * thumbTotal : null
     * receiveCardNum : 0
     * recentVisitorNum : 0
     * isFriend : null
     * isAttention : null
     * isCreateCard : false
     */

    private String memberId;
    private String realName;
    private String avatar;
    private String gender;
    private String positionName;
    private Object memberSign;
    private String backImg;
    private String leverName;
    private String areaProvinceName;
    private String areaCityName;
    private Object areaDistrictName;
    private int contactsTotal;
    private int attentionTotal;
    private int fansTotal;
    private String thumbTotal;
    private int receiveCardNum;
    private int recentVisitorNum;
    private Object isFriend;
    private Object isAttention;
    private boolean isCreateCard;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Object getMemberSign() {
        return memberSign;
    }

    public void setMemberSign(Object memberSign) {
        this.memberSign = memberSign;
    }

    public String getBackImg() {
        return backImg;
    }

    public void setBackImg(String backImg) {
        this.backImg = backImg;
    }

    public String getLeverName() {
        return leverName;
    }

    public void setLeverName(String leverName) {
        this.leverName = leverName;
    }

    public String getAreaProvinceName() {
        return areaProvinceName;
    }

    public void setAreaProvinceName(String areaProvinceName) {
        this.areaProvinceName = areaProvinceName;
    }

    public String getAreaCityName() {
        return areaCityName;
    }

    public void setAreaCityName(String areaCityName) {
        this.areaCityName = areaCityName;
    }

    public Object getAreaDistrictName() {
        return areaDistrictName;
    }

    public void setAreaDistrictName(Object areaDistrictName) {
        this.areaDistrictName = areaDistrictName;
    }

    public int getContactsTotal() {
        return contactsTotal;
    }

    public void setContactsTotal(int contactsTotal) {
        this.contactsTotal = contactsTotal;
    }

    public int getAttentionTotal() {
        return attentionTotal;
    }

    public void setAttentionTotal(int attentionTotal) {
        this.attentionTotal = attentionTotal;
    }

    public int getFansTotal() {
        return fansTotal;
    }

    public void setFansTotal(int fansTotal) {
        this.fansTotal = fansTotal;
    }

    public String getThumbTotal() {
        return thumbTotal;
    }

    public void setThumbTotal(String thumbTotal) {
        this.thumbTotal = thumbTotal;
    }

    public int getReceiveCardNum() {
        return receiveCardNum;
    }

    public void setReceiveCardNum(int receiveCardNum) {
        this.receiveCardNum = receiveCardNum;
    }

    public int getRecentVisitorNum() {
        return recentVisitorNum;
    }

    public void setRecentVisitorNum(int recentVisitorNum) {
        this.recentVisitorNum = recentVisitorNum;
    }

    public Object getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(Object isFriend) {
        this.isFriend = isFriend;
    }

    public Object getIsAttention() {
        return isAttention;
    }

    public void setIsAttention(Object isAttention) {
        this.isAttention = isAttention;
    }

    public boolean isIsCreateCard() {
        return isCreateCard;
    }

    public void setIsCreateCard(boolean isCreateCard) {
        this.isCreateCard = isCreateCard;
    }
}
