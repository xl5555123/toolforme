package com.pku.ipku.model.person.dto;

/**
 * Created by XingLiang on 2015/4/1.
 */
public class BalanceDTO {

    private boolean success;
    private double balance;

    public BalanceDTO() {

    }

    public BalanceDTO(boolean success, double balance) {
        this.balance = balance;
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
