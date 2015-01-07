package com.pku.ipku.model.studyguide.util;

/**
 * Created by XingLiang on 2015/1/7.
 */
public class ClassIndex {

    public final static int FIRSTCLASS = 1;
    public final static int SECONDCLASS = 2;
    public final static int THIRDCLASS = 3;
    public final static int FOURTHCLASS = 4;
    public final static int FIFTHCLASS = 5;
    public final static int SIXTHCLASS = 6;
    public final static int SEVENTHCLASS = 7;
    public final static int EIGHTHCLASS = 8;
    public final static int NINTHCLASS = 9;
    public final static int TENTHCLASS = 10;
    public final static int ELEVENTHCLASS = 11;
    public final static int TWELVETHCLASS = 12;

    private int getClass(int classIndex) {
        return classIndex * 2;
    }
}
