package com.benwunet.home.ui.bean;

import java.util.List;

public class CardDetailsBean {

    /**
     * addressInfo :
     * areaCityId :
     * backImage :
     * cardId :
     * companyList : [{"authStatus":1,"companyId":"0001201","companyName":"本无网络科技有限公司","companyPosition":"技术总监","id":"0001201","isMainCompany":true,"memberId":"000020"}]
     * email :
     * imagePhoto :
     * industry :
     * isCollection : false
     * isFriend : false
     * isReceiveCard : false
     * isRecommendCard : false
     * isSingleFriend : false
     * mobile :
     * mobileAttribution :
     * organisation : 本无网络公司
     * phone :
     * positionName :
     * qq :
     * realName :
     * realNamePinyinFirst :
     * remark :
     * shortVideo :
     * styleId :
     * vipState :
     * weChat :
     */

    private String addressInfo;
    private String areaCityId;
    private String backImage;
    private String cardId;
    private String email;
    private String imagePhoto;
    private String industry;
    private boolean isCollection;
    private boolean isFriend;
    private boolean isReceiveCard;
    private boolean isRecommendCard;
    private boolean isSingleFriend;
    private String mobile;
    private String mobileAttribution;
    private String organisation;
    private String phone;
    private String positionName;
    private String qq;
    private String realName;
    private String realNamePinyinFirst;
    private String remark;
    private String shortVideo;
    private String styleId;
    private String vipState;
    private String weChat;
    private List<CompanyListBean> companyList;

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public String getAreaCityId() {
        return areaCityId;
    }

    public void setAreaCityId(String areaCityId) {
        this.areaCityId = areaCityId;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagePhoto() {
        return imagePhoto;
    }

    public void setImagePhoto(String imagePhoto) {
        this.imagePhoto = imagePhoto;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public boolean isIsCollection() {
        return isCollection;
    }

    public void setIsCollection(boolean isCollection) {
        this.isCollection = isCollection;
    }

    public boolean isIsFriend() {
        return isFriend;
    }

    public void setIsFriend(boolean isFriend) {
        this.isFriend = isFriend;
    }

    public boolean isIsReceiveCard() {
        return isReceiveCard;
    }

    public void setIsReceiveCard(boolean isReceiveCard) {
        this.isReceiveCard = isReceiveCard;
    }

    public boolean isIsRecommendCard() {
        return isRecommendCard;
    }

    public void setIsRecommendCard(boolean isRecommendCard) {
        this.isRecommendCard = isRecommendCard;
    }

    public boolean isIsSingleFriend() {
        return isSingleFriend;
    }

    public void setIsSingleFriend(boolean isSingleFriend) {
        this.isSingleFriend = isSingleFriend;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobileAttribution() {
        return mobileAttribution;
    }

    public void setMobileAttribution(String mobileAttribution) {
        this.mobileAttribution = mobileAttribution;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealNamePinyinFirst() {
        return realNamePinyinFirst;
    }

    public void setRealNamePinyinFirst(String realNamePinyinFirst) {
        this.realNamePinyinFirst = realNamePinyinFirst;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getShortVideo() {
        return shortVideo;
    }

    public void setShortVideo(String shortVideo) {
        this.shortVideo = shortVideo;
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public String getVipState() {
        return vipState;
    }

    public void setVipState(String vipState) {
        this.vipState = vipState;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public List<CompanyListBean> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<CompanyListBean> companyList) {
        this.companyList = companyList;
    }

    public static class CompanyListBean {
        /**
         * authStatus : 1
         * companyId : 0001201
         * companyName : 本无网络科技有限公司
         * positionName : 技术总监
         * id : 0001201
         * isMainCompany : true
         * memberId : 000020
         */

        private int authStatus;
        private String companyId;
        private String companyName;

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        private String positionName;
        private String id;
        private boolean isMainCompany;
        private String memberId;

        public int getAuthStatus() {
            return authStatus;
        }

        public void setAuthStatus(int authStatus) {
            this.authStatus = authStatus;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIsMainCompany() {
            return isMainCompany;
        }

        public void setIsMainCompany(boolean isMainCompany) {
            this.isMainCompany = isMainCompany;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }
    }
}
