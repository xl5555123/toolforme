package com.pku.ipku.model.person.dto;

/**
 * Created by Allen on 2015/1/19.
 */
public class ScholarShipDTO {
    private String scholarshipName;
    private String semester;
    private double money;

    public ScholarShipDTO(String scholarshipName, String semester, double money) {
        this.scholarshipName = scholarshipName;
        this.semester = semester;
        this.money = money;
    }

    public String getScholarshipName() {
        return scholarshipName;
    }

    public void setScholarshipName(String scholarshipName) {
        this.scholarshipName = scholarshipName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
