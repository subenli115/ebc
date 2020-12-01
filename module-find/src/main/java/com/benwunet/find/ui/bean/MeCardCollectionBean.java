package com.benwunet.find.ui.bean;

import java.util.List;

public class MeCardCollectionBean {

    /**
     * current : 1
     * size : 10
     * total : 2
     * list : [{"collectionId":"200731116105211281408","memberId":"000027","imagePhoto":"https://bw-ebc.oss-cn-chengdu.aliyuncs.com/ebc86c5cdd84ec6e3cd055043ee039ba45f.jpg","realName":"小清","mobile":"不可见(需双方交换名片)","positionName":null,"isFriend":false},{"collectionId":"200729115523813638144","memberId":"100002","imagePhoto":"https://bw-ebc.oss-cn-chengdu.aliyuncs.com/ebc7edb03b64800ab7bfa18ccfde6f755bb.png","realName":"川","mobile":"不可见(需双方交换名片)","positionName":null,"isFriend":false}]
     */

    private int current;
    private int size;
    private int total;
    private List<ListBean> list;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * collectionId : 200731116105211281408
         * memberId : 000027
         * imagePhoto : https://bw-ebc.oss-cn-chengdu.aliyuncs.com/ebc86c5cdd84ec6e3cd055043ee039ba45f.jpg
         * realName : 小清
         * mobile : 不可见(需双方交换名片)
         * positionName : null
         * isFriend : false
         */

        private String collectionId;
        private String memberId;
        private String imagePhoto;
        private String realName;
        private String mobile;
        private Object positionName;
        private boolean isFriend;

        public String getCollectionId() {
            return collectionId;
        }

        public void setCollectionId(String collectionId) {
            this.collectionId = collectionId;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getImagePhoto() {
            return imagePhoto;
        }

        public void setImagePhoto(String imagePhoto) {
            this.imagePhoto = imagePhoto;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getPositionName() {
            return positionName;
        }

        public void setPositionName(Object positionName) {
            this.positionName = positionName;
        }

        public boolean isIsFriend() {
            return isFriend;
        }

        public void setIsFriend(boolean isFriend) {
            this.isFriend = isFriend;
        }
    }
}
