package com.benwunet.sign.ui.bean;

/**
 * @Package: com.benwunet.sign.ui.bean
 * @ClassName: IndustryListBean
 * @Description: 行业列表
 * @Author: feng
 * @CreateDate: 2020/10/28 0028 11:39
 * @Version: 1.0
 */


public class IndustryListBean {

        /**
         * industryId : 01
         * industryName : 农业
         * industryNameEn : N
         */

        private String industryId;
        private String industryName;
        private String industryNameEn;

        public String getIndustryId() {
            return industryId;
        }

        public void setIndustryId(String industryId) {
            this.industryId = industryId;
        }

        public String getIndustryName() {
            return industryName;
        }

        public void setIndustryName(String industryName) {
            this.industryName = industryName;
        }

        public String getIndustryNameEn() {
            return industryNameEn;
        }

        public void setIndustryNameEn(String industryNameEn) {
            this.industryNameEn = industryNameEn;
        }
}
