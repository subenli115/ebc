package com.benwunet.cards.ui.bean;

import java.util.List;

public class CardsHomeBean {

    /**
     * paperCardNum : 0
     * cardCollectNum : 1
     * groups : [{"id":"64604286222336000","groupId":"201023110021048270848","groupName":"好友","cardNum":2,"sortOrder":1,"cardIds":["000028","000026"],"imagePhotos":["https://bw-ebc.oss-cn-chengdu.aliyuncs.com/ebcd8251161514134f8d5db0c980597bb65.png","https://bw-ebc.oss-cn-chengdu.aliyuncs.com/ebc56654b4a81fc196f967d0f4bbda270f7.png"]}]
     */

    private int paperCardNum;
    private int cardCollectNum;
    private List<GroupsBean> groups;

    public int getPaperCardNum() {
        return paperCardNum;
    }

    public void setPaperCardNum(int paperCardNum) {
        this.paperCardNum = paperCardNum;
    }

    public int getCardCollectNum() {
        return cardCollectNum;
    }

    public void setCardCollectNum(int cardCollectNum) {
        this.cardCollectNum = cardCollectNum;
    }

    public List<GroupsBean> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupsBean> groups) {
        this.groups = groups;
    }

    public static class GroupsBean {
        /**
         * id : 64604286222336000
         * groupId : 201023110021048270848
         * groupName : 好友
         * cardNum : 2
         * sortOrder : 1
         * cardIds : ["000028","000026"]
         * imagePhotos : ["https://bw-ebc.oss-cn-chengdu.aliyuncs.com/ebcd8251161514134f8d5db0c980597bb65.png","https://bw-ebc.oss-cn-chengdu.aliyuncs.com/ebc56654b4a81fc196f967d0f4bbda270f7.png"]
         */

        private String id;
        private String groupId;
        private String groupName;
        private int cardNum;
        private int sortOrder;
        private List<String> cardIds;
        private List<String> imagePhotos;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public int getCardNum() {
            return cardNum;
        }

        public void setCardNum(int cardNum) {
            this.cardNum = cardNum;
        }

        public int getSortOrder() {
            return sortOrder;
        }

        public void setSortOrder(int sortOrder) {
            this.sortOrder = sortOrder;
        }

        public List<String> getCardIds() {
            return cardIds;
        }

        public void setCardIds(List<String> cardIds) {
            this.cardIds = cardIds;
        }

        public List<String> getImagePhotos() {
            return imagePhotos;
        }

        public void setImagePhotos(List<String> imagePhotos) {
            this.imagePhotos = imagePhotos;
        }
    }
}
