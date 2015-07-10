package com.xingliang.toolforme.model.person.dto;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;

import java.util.Vector;

/**
 * Created by vector liu on 2015/3/21.
 */
public class CurriculumDTO implements Parcelable {
    String week;
    String timeNum;
    String courseName;
    String roomName;
    String parity;
    String detailTime;
    int classCount;
    public int start_hour = 0;
    public int start_min = 0;
    public int end_hour = 0;
    public int end_min = 0;

    public static String timeNums[] = {"第一节", "第二节", "第三节", "第四节", "第五节", "第六节", "第七节", "第八节", "第九节", "第十节", "十一节", "第十二节"};
    public static Vector<Pair<String, String>> classTime = new Vector<Pair<String, String>>();

    static {
        classTime.add(new Pair("8:00", "8:50"));
        classTime.add(new Pair("9:00", "9:50"));
        classTime.add(new Pair("10:10", "11:00"));
        classTime.add(new Pair("11:10", "12:00"));
        classTime.add(new Pair("13:00", "13:50"));
        classTime.add(new Pair("14:00", "14:50"));
        classTime.add(new Pair("15:10", "16:00"));
        classTime.add(new Pair("16:10", "17:00"));
        classTime.add(new Pair("17:10", "18:00"));
        classTime.add(new Pair("18:40", "19:30"));
        classTime.add(new Pair("19:40", "20:30"));
        classTime.add(new Pair("20:40", "21:30"));
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(week);
        dest.writeString(timeNum);
        dest.writeString(courseName);
        dest.writeString(roomName);
        dest.writeString(parity);
        dest.writeString(detailTime);
        dest.writeInt(classCount);
    }

    public CurriculumDTO(String week, String timeNum, String courseName, String roomName, String parity, int classCount) {
        this.week = week;
        this.timeNum = timeNum;
        this.courseName = courseName;
        this.roomName = roomName;
        this.parity = parity;
        this.classCount = classCount;
        this.detailTime = getTime();
    }


    public String getTime() {
        String detailTime = "";
        for (int i = 0; i < 12; i++) {
            if (this.timeNum.indexOf(timeNums[i]) >= 0) {
                String begin = classTime.get(i).first;
                String end = classTime.get(i + classCount - 1).second;
                start_hour = Integer.parseInt(begin.substring(0,begin.indexOf(":")));
                start_min = Integer.parseInt(begin.substring(begin.indexOf(":")+1));
                end_hour = Integer.parseInt(end.substring(0,end.indexOf(":")));
                end_min = Integer.parseInt(end.substring(end.indexOf(":")+1));
                this.timeNum = timeNums[i] + " - " + timeNums[i + classCount - 1];
                detailTime = begin + "-" + end;
                break;
            }
        }
        this.detailTime = detailTime;
        return detailTime;
    }

    public CurriculumDTO(Parcel source) {
        this.week = source.readString();
        this.timeNum = source.readString();
        this.courseName = source.readString();
        this.roomName = source.readString();
        this.parity = source.readString();
        this.detailTime = source.readString();
        this.classCount = source.readInt();
    }

    public String getWeek() {
        return week;
    }

    public String getTimeNum() {
        return timeNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getParity() {
        return parity;
    }

    public void setTimeNum(String timeNum) {
        this.timeNum = timeNum;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setParity(String parity) {
        this.parity = parity;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getClassCount() {
        return classCount;
    }

    public void setClassCount(int classCount) {
        this.classCount = classCount;
    }

    public void setDetailTime(String detailTime) {
        this.detailTime = detailTime;
    }

    public String getDetailTime() {
        return detailTime;
    }

    public static String[] getTimeNums() {
        return timeNums;
    }

    public static void setTimeNums(String[] timeNums) {
        CurriculumDTO.timeNums = timeNums;
    }

    public void addClassCount() {
        this.classCount++;
    }

    @Override
    public String toString() {
        String str = "week: " + week + ", timeNum: " + timeNum + ", courseName: " + courseName + ", roomName: " + roomName + ", parity: " + parity;
        return str;
    }


    public static final Parcelable.Creator<CurriculumDTO> CREATOR = new Creator<CurriculumDTO>() {

        @Override
        public CurriculumDTO createFromParcel(Parcel source) {
            return new CurriculumDTO(source);
        }

        @Override
        public CurriculumDTO[] newArray(int size) {
            return new CurriculumDTO[size];
        }
    };
}
