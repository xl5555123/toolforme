package com.xingliang.toolforme.model.person.studyguide;

/**
 * Created by XingLiang on 2015/1/7.
 */
public class Lesson {
    public Lesson(String courseEngName, String courseId, String courseName, String credits, String setupFor, String totalHrs, String url, String weekHrs) {
        this.courseEngName = courseEngName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.setupFor = setupFor;
        this.totalHrs = totalHrs;
        this.url = url;
        this.weekHrs = weekHrs;
    }

    public Lesson() {

    }

    public String getCourseEngName() {
        return courseEngName;
    }

    public void setCourseEngName(String courseEngName) {
        this.courseEngName = courseEngName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getSetupFor() {
        return setupFor;
    }

    public void setSetupFor(String setupFor) {
        this.setupFor = setupFor;
    }

    public String getTotalHrs() {
        return totalHrs;
    }

    public void setTotalHrs(String totalHrs) {
        this.totalHrs = totalHrs;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWeekHrs() {
        return weekHrs;
    }

    public void setWeekHrs(String weekHrs) {
        this.weekHrs = weekHrs;
    }

    private String courseEngName;
    private String courseId;
    private String courseName;
    private String credits;
    private String setupFor;
    private String totalHrs;
    private String url;
    private String weekHrs;
}
