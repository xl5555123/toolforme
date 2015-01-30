package com.pku.ipku.model.pkuInfo;

import com.pku.ipku.model.pkuInfo.dto.PkuPublicInfo;

import java.util.List;

/**
 * Created by XingLiang on 2015/1/26.
 */
public class PkuInfoType {

    public final static String PKU_NEWS = "pku_news";
    public final static String PKU_NOTICES = "pku_notices";
    public final static String PKU_LECTURES = "pku_lectures";
    public final static String PKU_CAREER = "pku_career";

    private String type;
    private String title;

    public PkuInfoType() {

    }

    public boolean isPkuNews() {
        return type.equals(PKU_NEWS);
    }

    public boolean isPkuNotices() {
        return type.equals(PKU_NOTICES);
    }

    public boolean isPkuLectures() {
        return type.equals(PKU_LECTURES);
    }

    public boolean isPkuCareer() {
        return type.equals(PKU_CAREER);
    }

    public PkuInfoType(String type, String title) {
        this.type = type;
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
