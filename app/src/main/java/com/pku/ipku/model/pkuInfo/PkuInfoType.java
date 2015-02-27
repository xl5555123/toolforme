package com.pku.ipku.model.pkuInfo;

import com.pku.ipku.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by XingLiang on 2015/1/26.
 */
public class PkuInfoType {

    public final static String PKU_NEWS = "pku_news";
    public final static String PKU_NOTICES = "pku_notices";
    public final static String PKU_LECTURES = "pku_lectures";
    public final static String PKU_CAREER = "pku_career";

    private Map<String, String> titles = new HashMap<String, String>() {
        {
            put(PKU_NEWS, "学校新闻");
            put(PKU_NOTICES, "学校通知");
            put(PKU_LECTURES, "校内讲座");
            put(PKU_CAREER, "招聘信息");
        }
    };

    private Map<String, Integer> icons = new HashMap<String, Integer>() {
        {
            put(PKU_NEWS, R.drawable.college_news);
            put(PKU_NOTICES, R.drawable.college_inform);
            put(PKU_LECTURES, R.drawable.college_lecture);
            put(PKU_CAREER, R.drawable.college_job);
        }
    };

    private String type;
    private String title;

    public PkuInfoType() {

    }

    public PkuInfoType(String type) {
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return titles.get(type);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIconId() {
        return icons.get(type);

    }
}
