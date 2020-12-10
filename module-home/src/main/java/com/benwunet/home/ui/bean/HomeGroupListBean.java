package com.benwunet.home.ui.bean;

public class HomeGroupListBean {

    /**
     * groupAvatar :
     * groupId :
     * groupName :
     * identity : 0
     * isReceive : 0
     * isTop : 0
     */

    private String groupAvatar;
    private String groupId;
    private String groupName;
    private int identity;
    private int isReceive;
    private int isTop;
    private boolean isSelect;


    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getGroupAvatar() {
        return groupAvatar;
    }

    public void setGroupAvatar(String groupAvatar) {
        this.groupAvatar = groupAvatar;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public int getIsReceive() {
        return isReceive;
    }

    public void setIsReceive(int isReceive) {
        this.isReceive = isReceive;
    }

    public int getIsTop() {
        return isTop;
    }

    public void setIsTop(int isTop) {
        this.isTop = isTop;
    }
}
