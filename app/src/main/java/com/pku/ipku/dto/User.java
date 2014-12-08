package com.pku.ipku.dto;

/**
 * Created by XingLiang on 2014/11/28.
 */
public class User {

    private String username;
    private String nickname;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
