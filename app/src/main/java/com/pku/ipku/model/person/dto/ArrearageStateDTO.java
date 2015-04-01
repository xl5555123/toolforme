package com.pku.ipku.model.person.dto;

/**
 * Created by XingLiang on 2015/1/9.
 */
public class ArrearageStateDTO {
    private double libraryFee;
    private double schoolCardBalance;
    private double netBalance;

    public ArrearageStateDTO() {
        setLibraryFee(0);
        setSchoolCardBalance(0);
        setNetBalance(0);
    }

    public ArrearageStateDTO(double libraryFee, double schoolCardBalance, double netBalance) {
        this.libraryFee = libraryFee;
        this.schoolCardBalance = schoolCardBalance;
        this.netBalance = netBalance;
    }

    public double getLibraryFee() {
        return libraryFee;
    }

    public void setLibraryFee(double libraryFee) {
        this.libraryFee = libraryFee;
    }

    public double getSchoolCardBalance() {
        return schoolCardBalance;
    }

    public void setSchoolCardBalance(double schoolCardBalance) {
        this.schoolCardBalance = schoolCardBalance;
    }

    public double getNetBalance() {
        return netBalance;
    }

    public void setNetBalance(double netBalance) {
        this.netBalance = netBalance;
    }
}
