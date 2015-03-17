package com.pku.ipku.model.pkuInfo;

import com.pku.ipku.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by XingLiang on 2015/1/26.
 */
public class PkuInfoType {

    public final static String RECENT_SCHOOL_NOTICES = "RECENT_SCHOOL_NOTICES";
    public final static String RECENT_SCHOOL_NEWS = "RECENT_SCHOOL_NEWS";
    public final static String RECENT_DEPT_NOTICES = "RECENT_DEPT_NOTICES";
    public final static String TOP_NEWS = "TOP_NEWS";
    public final static String PAGED_CAREER_RECRUITS = "PAGED_CAREER_RECRUITS";
    public final static String PAGED_CAREER_INTERNS = "PAGED_CAREER_INTERNS";
    public final static String PAGED_CAREER_PROPA = "PAGED_CAREER_PROPA";
    public final static String TOP_LECTURES = "TOP_LECTURES";


    private Map<String, String> titles = new HashMap<String, String>() {
        {
            put(RECENT_SCHOOL_NOTICES, "学校公告");
            put(RECENT_SCHOOL_NEWS, "学校动态");
            put(RECENT_DEPT_NOTICES, "单位公告");
            put(TOP_NEWS, "学校新闻");
            put(PAGED_CAREER_RECRUITS, "招聘信息");
            put(PAGED_CAREER_INTERNS, "实习信息");
            put(TOP_LECTURES, "讲座信息");
            put(PAGED_CAREER_PROPA, "宣讲会信息");
        }
    };

    private String type;

    public PkuInfoType() {

    }

    public PkuInfoType(String type) {
        this.type = type;
    }

    public boolean isRECENT_SCHOOL_NOTICES() {
        return type.equals(RECENT_SCHOOL_NOTICES);
    }

    public boolean isRECENT_SCHOOL_NEWS() {
        return type.equals(RECENT_SCHOOL_NEWS);
    }

    public boolean isRECENT_DEPT_NOTICES() {
        return type.equals(RECENT_DEPT_NOTICES);
    }

    public boolean isTOP_NEWS() {
        return type.equals(TOP_NEWS);
    }

    public boolean isPAGED_CAREER_RECRUITS() {
        return type.equals(PAGED_CAREER_RECRUITS);
    }

    public boolean isPAGED_CAREER_PROPA() {
        return type.equals(PAGED_CAREER_PROPA);
    }

    public boolean isPAGED_CAREER_INTERNS() {
        return type.equals(PAGED_CAREER_INTERNS);
    }

    public boolean isTOP_LECTURES() {
        return type.equals(TOP_LECTURES);
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
}
