package com.xingliang.toolforme.model.person.studyguide.util;

/**
 * Created by XingLiang on 2015/1/7.
 */
public class DayClass {
    private int day;
    private int classIndex;

    public DayClass(int day, int classIndex) {
        this.day = day;
        this.classIndex = classIndex;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
    }
}
