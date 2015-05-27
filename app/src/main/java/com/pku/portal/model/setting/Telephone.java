package com.pku.portal.model.setting;

/**
 * Created by XingLiang on 2015/4/21.
 */
public class Telephone {

    private String department;

    public Telephone() {

    }

    public Telephone(String department, String telephone) {
        this.department = department;
        this.telephone = telephone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    private String telephone;
}
