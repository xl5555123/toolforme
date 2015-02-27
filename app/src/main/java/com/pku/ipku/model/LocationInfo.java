package com.pku.ipku.model;

import com.pku.ipku.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vector liu on 2015/1/10.
 */
public class LocationInfo implements Serializable {
    private static final long serialVersionUID = -1010711775392052966L;
    public static List<LocationInfo> infos = new ArrayList<LocationInfo>();

    static {
        infos.add(new LocationInfo(39.998922, 116.310728, R.drawable.pku_ximen, "北京大学西门",
                "距离209米", 1456));
        infos.add(new LocationInfo(40.001112, 116.316675, R.drawable.pku_weiminghu, "北京大学未名湖",
                "距离209米", 1456));
    }

    private double latitude;
    private double longitude;
    private int imgId;
    private String name;
    private String distance;
    private int zan;

    public LocationInfo(double latitude, double longitude, int imgId, String name,
                        String distance, int zan) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.imgId = imgId;
        this.name = name;
        this.distance = distance;
        this.zan = zan;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }
}
