package com.benwunet.sign.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;

/**
 * @Package: com.benwunet.sign.ui.bean
 * @ClassName: CompleteInfoBean
 * @Description: 完善信息数据
 * @Author: feng
 * @CreateDate: 2020/10/28 0028 18:12
 * @Version: 1.0
 */


public class CompleteInfoBean extends BaseObservable implements Parcelable {

    /**
     * avatar :
     * birthday :
     * company : 本无网络
     * gender :
     * industry :
     * position : 经理
     * province :
     * realName :
     * topic :
     */

    private String avatar;
    private String birthday;
    private String company;
    private String gender;
    private String industry;
    private String position;
    private String province;
    private String realName;
    private String topic;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.avatar);
        dest.writeString(this.birthday);
        dest.writeString(this.company);
        dest.writeString(this.gender);
        dest.writeString(this.industry);
        dest.writeString(this.position);
        dest.writeString(this.province);
        dest.writeString(this.realName);
        dest.writeString(this.topic);
    }

    public CompleteInfoBean() {
    }

    protected CompleteInfoBean(Parcel in) {
        this.avatar = in.readString();
        this.birthday = in.readString();
        this.company = in.readString();
        this.gender = in.readString();
        this.industry = in.readString();
        this.position = in.readString();
        this.province = in.readString();
        this.realName = in.readString();
        this.topic = in.readString();
    }

    public static final Parcelable.Creator<CompleteInfoBean> CREATOR = new Parcelable.Creator<CompleteInfoBean>() {
        @Override
        public CompleteInfoBean createFromParcel(Parcel source) {
            return new CompleteInfoBean(source);
        }

        @Override
        public CompleteInfoBean[] newArray(int size) {
            return new CompleteInfoBean[size];
        }
    };
}
