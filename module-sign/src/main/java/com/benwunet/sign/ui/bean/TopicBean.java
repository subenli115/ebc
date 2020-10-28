package com.benwunet.sign.ui.bean;

import java.util.List;

/**
 * @Package: com.benwunet.sign.ui.bean
 * @ClassName: TopicBean
 * @Description: 热门话题
 * @Author: feng
 * @CreateDate: 2020/10/28 0028 16:37
 * @Version: 1.0
 */


public class TopicBean {


    /**
     * code : 0
     * message : SUCCESS
     * data : {"current":1,"size":10,"total":12,"list":[{"topicId":"1","topicTitle":"话题1","topicPic":"https://bw-ebc.oss-cn-chengdu.aliyuncs.com/ebc5507f4c25b77831a128af4e0f28f9943.jpeg","readingQuantity":1000,"discussQuantity":1412,"compere":"小猪猪","takeaway":"导读更好地内容昂发","checkStatus":"1","operatorId":"000027"},{"topicId":"3","topicTitle":"话题3","topicPic":"https://bw-ebc.oss-cn-chengdu.aliyuncs.com/ebcfc10760b70f28f888d6491e47bafb041.png","readingQuantity":1200,"discussQuantity":1100,"compere":"小猪猪","takeaway":"导读","checkStatus":"1","operatorId":"000027"},{"topicId":"2","topicTitle":"话题2","topicPic":"https://bw-ebc.oss-cn-chengdu.aliyuncs.com/ebcfc10760b70f28f888d6491e47bafb041.png","readingQuantity":1200,"discussQuantity":1008,"compere":"小猪猪","takeaway":"导读","checkStatus":"1","operatorId":"000027"},{"topicId":"4","topicTitle":"话题4","topicPic":"https://bw-ebc.oss-cn-chengdu.aliyuncs.com/ebcfc10760b70f28f888d6491e47bafb041.png","readingQuantity":1000,"discussQuantity":1000,"compere":"小猪猪","takeaway":"导读","checkStatus":"1","operatorId":"000027"},{"topicId":"200927112316999794688","topicTitle":"测试话题","topicPic":"https://bw-ebc.oss-cn-chengdu.aliyuncs.com/ebcfc10760b70f28f888d6491e47bafb041.png","readingQuantity":0,"discussQuantity":0,"compere":"WANGL","takeaway":"这是导读","checkStatus":"0","operatorId":"100003"},{"topicId":"201012111549765124096","topicTitle":"撒旦法","topicPic":null,"readingQuantity":0,"discussQuantity":0,"compere":"小清","takeaway":null,"checkStatus":"1","operatorId":"000027"},{"topicId":"201012116825364889600","topicTitle":"撒旦法","topicPic":"https://bw-ebc.oss-cn-chengdu.aliyuncs.com/ebcfc10760b70f28f888d6491e47bafb041.png","readingQuantity":0,"discussQuantity":0,"compere":"小清","takeaway":null,"checkStatus":"1","operatorId":"000027"},{"topicId":"201012117432528142337","topicTitle":"同意","topicPic":"https://bw-ebc.oss-cn-chengdu.aliyuncs.com/ebcfc10760b70f28f888d6491e47bafb041.png","readingQuantity":0,"discussQuantity":0,"compere":"小清","takeaway":null,"checkStatus":"1","operatorId":"000027"},{"topicId":"201012117802558029824","topicTitle":"是","topicPic":"https://bw-ebc.oss-cn-chengdu.aliyuncs.com/ebcfc10760b70f28f888d6491e47bafb041.png","readingQuantity":0,"discussQuantity":0,"compere":"小清","takeaway":null,"checkStatus":"0","operatorId":"000027"},{"topicId":"201012117863868268544","topicTitle":"二","topicPic":"https://bw-ebc.oss-cn-chengdu.aliyuncs.com/ebcfc10760b70f28f888d6491e47bafb041.png","readingQuantity":0,"discussQuantity":0,"compere":"小清","takeaway":null,"checkStatus":"1","operatorId":"000027"}]}
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
             * topicId : 1
             * topicTitle : 话题1
             * topicPic : https://bw-ebc.oss-cn-chengdu.aliyuncs.com/ebc5507f4c25b77831a128af4e0f28f9943.jpeg
             * readingQuantity : 1000
             * discussQuantity : 1412
             * compere : 小猪猪
             * takeaway : 导读更好地内容昂发
             * checkStatus : 1
             * operatorId : 000027
             */

            private String topicId;
            private String topicTitle;
            private String topicPic;
            private int readingQuantity;
            private int discussQuantity;
            private String compere;
            private String takeaway;
            private String checkStatus;
            private String operatorId;

            public String getTopicId() {
                return topicId;
            }

            public void setTopicId(String topicId) {
                this.topicId = topicId;
            }

            public String getTopicTitle() {
                return topicTitle;
            }

            public void setTopicTitle(String topicTitle) {
                this.topicTitle = topicTitle;
            }

            public String getTopicPic() {
                return topicPic;
            }

            public void setTopicPic(String topicPic) {
                this.topicPic = topicPic;
            }

            public int getReadingQuantity() {
                return readingQuantity;
            }

            public void setReadingQuantity(int readingQuantity) {
                this.readingQuantity = readingQuantity;
            }

            public int getDiscussQuantity() {
                return discussQuantity;
            }

            public void setDiscussQuantity(int discussQuantity) {
                this.discussQuantity = discussQuantity;
            }

            public String getCompere() {
                return compere;
            }

            public void setCompere(String compere) {
                this.compere = compere;
            }

            public String getTakeaway() {
                return takeaway;
            }

            public void setTakeaway(String takeaway) {
                this.takeaway = takeaway;
            }

            public String getCheckStatus() {
                return checkStatus;
            }

            public void setCheckStatus(String checkStatus) {
                this.checkStatus = checkStatus;
            }

            public String getOperatorId() {
                return operatorId;
            }

            public void setOperatorId(String operatorId) {
                this.operatorId = operatorId;
            }
        }
}
