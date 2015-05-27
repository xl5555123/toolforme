package com.pku.portal.model.person.studyguide.util;

/**
 * Created by XingLiang on 2015/1/7.
 */
public class ClassIndex {

    public final static int FIRSTCLASS = 0;
    public final static int SECONDCLASS = 1;
    public final static int THIRDCLASS = 2;
    public final static int FOURTHCLASS = 3;
    public final static int FIFTHCLASS = 4;
    public final static int SIXTHCLASS = 5;
    public final static int SEVENTHCLASS = 6;
    public final static int EIGHTHCLASS = 7;
    public final static int NINTHCLASS = 8;
    public final static int TENTHCLASS = 9;
    public final static int ELEVENTHCLASS = 10;
    public final static int TWELVETHCLASS = 11;

    private int getClass(int classIndex) {
        return classIndex * 2;
    }
}
