package com.benwunet.home.ui.bean;

import com.benwunet.base.contract.BaseCustomViewModel;

/**
 * @Package: com.benwunet.home.ui.bean
 * @ClassName: IndustryListBean
 * @Description: 行业列表
 * @Author: feng
 * @CreateDate: 2020/10/28 0028 11:39
 * @Version: 1.0
 */


public class IndustryListBean extends BaseCustomViewModel {

    /**
     * industryId : 01
     * industryName : 农业
     * industryNameEn : N
     */

    private String industryId;
    private String industryName;
    private String industryNameEn;
    private boolean isSelect;

    public boolean isVisit() {
        return isVisit;
    }

    public void setVisit(boolean visit) {
        isVisit = visit;
    }

    private boolean isVisit=true;


    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

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
