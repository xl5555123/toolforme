package com.pku.portal.model.person.dto;

/**
 * @author XingLiang
 */
public class StuInfoDTO {
    private boolean success;
    private String name;
    private String nameAbbr;
    private String sex;
    private String userIdentity;
    private String department;
    private String studentType;
    private String studentId;
    private String birthDate;
    private String ethnic;
    private String enrollDate;
    private String nativePlace;
    private String originPlace;
    private String direction;
    private String politics;
    private String tutor;
    private String address;
    private String speciality;
    private String lastEducation;
    private String highestDegree;

    public StuInfoDTO() {

    }

    public StuInfoDTO(boolean success, String name, String nameAbbr, String sex, String userIdentity, String department, String studentType, String studentId, String birthDate, String ethnic, String enrollDate, String nativePlace, String originPlace, String direction, String politics, String tutor, String address, String speciality, String lastEducation, String highestDegree) {
        this.success = success;
        this.name = name;
        this.nameAbbr = nameAbbr;
        this.sex = sex;
        this.userIdentity = userIdentity;
        this.department = department;
        this.studentType = studentType;
        this.studentId = studentId;
        this.birthDate = birthDate;
        this.ethnic = ethnic;
        this.enrollDate = enrollDate;
        this.nativePlace = nativePlace;
        this.originPlace = originPlace;
        this.direction = direction;
        this.politics = politics;
        this.tutor = tutor;
        this.address = address;
        this.speciality = speciality;
        this.lastEducation = lastEducation;
        this.highestDegree = highestDegree;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAbbr() {
        return nameAbbr;
    }

    public void setNameAbbr(String nameAbbr) {
        this.nameAbbr = nameAbbr;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(String enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getLastEducation() {
        return lastEducation;
    }

    public void setLastEducation(String lastEducation) {
        this.lastEducation = lastEducation;
    }

    public String getHighestDegree() {
        return highestDegree;
    }

    public void setHighestDegree(String highestDegree) {
        this.highestDegree = highestDegree;
    }
}
