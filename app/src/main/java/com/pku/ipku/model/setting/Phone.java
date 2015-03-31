package com.pku.ipku.model.setting;

/**
 * Created by XingLiang on 2015/3/31.
 */
public class Phone {

    private String departmentName;
    private String phoneNumber;

    public Phone() {

    }


    public Phone(String departmentName, String phoneNumber) {
        this.departmentName = departmentName;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}
