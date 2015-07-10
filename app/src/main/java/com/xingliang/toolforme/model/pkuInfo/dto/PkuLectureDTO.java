package com.xingliang.toolforme.model.pkuInfo.dto;

import java.util.Date;

/**
 * Created by XingLiang on 2015/1/6.
 */
public class PkuLectureDTO {

    private String imageUrl;
    private Date startTime;
    private Date createTime;
    private String subject;
    private String location;
    private String attachUrl;

    public PkuLectureDTO() {

    }

    public PkuLectureDTO(PkuLectureDTO pkuLectureDTO) {
        this(pkuLectureDTO.getImageUrl(), pkuLectureDTO.getStartTime(), pkuLectureDTO.getCreateTime(), pkuLectureDTO.getSubject(), pkuLectureDTO.getLocation(), pkuLectureDTO.getAttachUrl());
    }


    public PkuLectureDTO(String imageUrl, Date startTime, Date createTime, String subject, String location, String attachUrl) {
        this.imageUrl = imageUrl;
        this.startTime = startTime;
        this.createTime = createTime;
        this.subject = subject;
        this.location = location;
        this.attachUrl = attachUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAttachUrl() {
        return attachUrl;
    }

    public void setAttachUrl(String attachUrl) {
        this.attachUrl = attachUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
