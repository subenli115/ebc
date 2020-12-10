package com.benwunet.cards.ui.bean;


import java.util.List;

/**
 *
 * 各类型智能分组列表
 *
 */
public class CardsMindGroupListBean {

    /**
     * id : null
     * groupId : null
     * groupName : 普通员工
     * cardNum : 2
     * sortOrder : null
     * cardIds : ["000028","000026"]
     * imagePhotos : null
     */

    private Object id;
    private Object groupId;
    private String groupName;
    private int cardNum;
    private Object sortOrder;
    private Object imagePhotos;
    private List<String> cardIds;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getGroupId() {
        return groupId;
    }

    public void setGroupId(Object groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    public Object getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Object sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Object getImagePhotos() {
        return imagePhotos;
    }

    public void setImagePhotos(Object imagePhotos) {
        this.imagePhotos = imagePhotos;
    }

    public List<String> getCardIds() {
        return cardIds;
    }

    public void setCardIds(List<String> cardIds) {
        this.cardIds = cardIds;
    }
}
