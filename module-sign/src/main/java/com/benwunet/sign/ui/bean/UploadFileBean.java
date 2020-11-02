package com.benwunet.sign.ui.bean;

/**
 * @Package: com.benwunet.sign.ui.bean
 * @ClassName: UploadFileBean
 * @Description: 文件上传返回
 * @Author: feng
 * @CreateDate: 2020/10/30 0030 14:27
 * @Version: 1.0
 */


public class UploadFileBean {

    /**
     * fileCode :
     * fileName :
     * fileType :
     * gmtCreate :
     * gmtModified :
     * id :
     * isImg : true
     * modifierId :
     * netUrl :
     * operatorId :
     * path :
     * size : 0
     * source :
     * suffix :
     */

    private String fileCode;
    private String fileName;
    private String fileType;
    private String gmtCreate;
    private String gmtModified;
    private String id;
    private boolean isImg;
    private String modifierId;
    private String netUrl;
    private String operatorId;
    private String path;
    private int size;
    private String source;
    private String suffix;

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIsImg() {
        return isImg;
    }

    public void setIsImg(boolean isImg) {
        this.isImg = isImg;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public String getNetUrl() {
        return netUrl;
    }

    public void setNetUrl(String netUrl) {
        this.netUrl = netUrl;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
