package com.benwunet.sign.ui.bean;

/**
 * @Package: com.benwunet.sign.ui.bean
 * @ClassName: jobListBean
 * @Description: 职业列表
 * @Author: feng
 * @CreateDate: 2020/10/28 0028 11:37
 * @Version: 1.0
 */


public class jobListBean {

    /**
     * code : 0
     * message : SUCCESS
     * data : [{"positionId":"1","positionName":"董事长","positionNameEn":"D"},{"positionId":"2","positionName":"总经理","positionNameEn":"Z"},{"positionId":"3","positionName":"总监","positionNameEn":"Z"},{"positionId":"4","positionName":"主管","positionNameEn":"Z"},{"positionId":"5","positionName":"员工","positionNameEn":"Y"}]
     */


        private String positionId;
        private String positionName;
        private String positionNameEn;

        public String getPositionId() {
            return positionId;
        }

        public void setPositionId(String positionId) {
            this.positionId = positionId;
        }

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public String getPositionNameEn() {
            return positionNameEn;
        }

        public void setPositionNameEn(String positionNameEn) {
            this.positionNameEn = positionNameEn;
    }
}
