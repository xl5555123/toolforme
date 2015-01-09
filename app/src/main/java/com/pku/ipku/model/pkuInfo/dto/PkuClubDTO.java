package com.pku.ipku.model.pkuInfo.dto;

import java.util.Date;

/**
 * Created by Allen on 2015/1/7.
 */
public class PkuClubDTO {

    private String clubName;
    private String subject;
    private String location;
    private Date startTime;
    private Date createTime;
    private String attachUrl;

    public PkuClubDTO(){}

    public PkuClubDTO(PkuClubDTO pkuClubDTO){
        this(pkuClubDTO.getClubName(),pkuClubDTO.getSubject(),pkuClubDTO.getLocation(),pkuClubDTO.getStartTime(),pkuClubDTO.getCreateTime(),pkuClubDTO.getAttachUrl());
    }

    public PkuClubDTO(String clubName,String subject,String location,Date startTime,Date createTime,String attachUrl){
        this.clubName = clubName;
        this.subject = subject;
        this.location = location;
        this.startTime = startTime;
        this.createTime = createTime;
        this.attachUrl = attachUrl;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getAttachUrl() {
        return attachUrl;
    }

    public void setAttachUrl(String attachUrl) {
        this.attachUrl = attachUrl;
    }

}

