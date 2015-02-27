package com.pku.ipku.model.studyguide;

/**
 * Created by XingLiang on 2015/1/7.
 */
public class Lesson {
    private int id;
    private String name;
    private String teacherName;
    private String location;
    private String information;
    private String time;
    private String sector;

    public Lesson() {

    }

    public Lesson(int id, String name, String teacherName, String location, String information, String time, String sector) {
        this.id = id;
        this.name = name;
        this.teacherName = teacherName;
        this.location = location;
        this.information = information;
        this.time = time;
        this.sector = sector;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
