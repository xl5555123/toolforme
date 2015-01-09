package com.pku.ipku.model.studyguide;

import android.graphics.Point;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by XingLiang on 2015/1/7.
 */
public class Lesson {
    private String name;
    private String teacherName;
    private String location;
    private String information;

    public Lesson() {

    }

    public Lesson(String name, String teacherName, String location, String information) {
        this.name = name;
        this.teacherName = teacherName;
        this.location = location;
        this.information = information;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
