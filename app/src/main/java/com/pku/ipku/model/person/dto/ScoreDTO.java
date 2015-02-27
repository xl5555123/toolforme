package com.pku.ipku.model.person.dto;

/**
 * Created by pktxq on 15-1-22.
 */
public class ScoreDTO {
    private String stuId;
    private String department;
    private String schoolYear;
    private String semester;
    private String courseId;
    private String courseName;
    private String credit;
    private String score;
    private String courseType;

    public ScoreDTO(String stuId, String department, String schoolYear, String semester, String courseId, String courseName, String credit, String score, String courseType) {
        this.stuId = stuId;
        this.department = department;
        this.schoolYear = schoolYear;
        this.semester = semester;
        this.courseId = courseId;
        this.courseName = courseName;
        this.credit = credit;
        this.score = score;
        this.courseType = courseType;
    }

    public ScoreDTO() {
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
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

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }


}
