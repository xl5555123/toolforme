package com.pku.ipku.model.pkuInfo.dto;


import java.util.Date;

/**
 * Created by Allen on 2015/1/9.
 */
public class PkuJobDTO {
    private String subject;
    private Date createTime;
    private String attachUrl;
    private String type;

    public PkuJobDTO(){

    }

    public PkuJobDTO(String subject, String type, Date createTime, String attachUrl) {
        this.subject = subject;
        this.type = type;
        this.createTime = createTime;
        this.attachUrl = attachUrl;
    }

    public String getAttachUrl() {
        return attachUrl;
    }

    public void setAttachUrl(String attachUrl) {
        this.attachUrl = attachUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
