package com.pku.ipku.model.person.dto;

/**
 * Created by pktxq on 15-1-6.
 */
public class StuInfoDTO {
    private String name;
    private String sex;
    private String race;
    private String nativePlace;
    private String supervisor;
    private String politicalStatus;
    private String stuType;
    private String credentials;
    private String credentialsId;
    private String birthday;
    private String enterSchoolDate;
    private String stuExamId;
    private String stuId;
    private String department;
    private String major;
    private String researchArea;
    public StuInfoDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getStuType() {
        return stuType;
    }

    public void setStuType(String stuType) {
        this.stuType = stuType;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public String getCredentialsId() {
        return credentialsId;
    }

    public void setCredentialsId(String credentialsId) {
        this.credentialsId = credentialsId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEnterSchoolDate() {
        return enterSchoolDate;
    }

    public void setEnterSchoolDate(String enterSchoolDate) {
        this.enterSchoolDate = enterSchoolDate;
    }

    public String getStuExamId() {
        return stuExamId;
    }

    public void setStuExamId(String stuExamId) {
        this.stuExamId = stuExamId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getResearchArea() {
        return researchArea;
    }

    public void setResearchArea(String researchArea) {
        this.researchArea = researchArea;
    }

    public StuInfoDTO(String name, String sex, String race, String nativePlace, String supervisor, String politicalStatus, String stuType,
                      String credentials, String credentialsId, String birthday, String enterSchoolDate, String stuExamId, String stuId,
                      String department, String major, String researchArea) {
        this.name = name;
        this.sex = sex;
        this.race = race;
        this.nativePlace = nativePlace;
        this.supervisor = supervisor;
        this.politicalStatus = politicalStatus;
        this.stuType = stuType;
        this.credentials = credentials;
        this.credentialsId = credentialsId;
        this.birthday = birthday;
        this.enterSchoolDate = enterSchoolDate;
        this.stuExamId = stuExamId;
        this.stuId = stuId;
        this.department = department;
        this.major = major;
        this.researchArea = researchArea;
    }



}
