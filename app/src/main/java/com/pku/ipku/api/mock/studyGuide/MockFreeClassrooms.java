package com.pku.ipku.api.mock.studyGuide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XingLiang on 2015/1/22.
 */
public class MockFreeClassrooms {

    private final static Map<String, List<Integer>> MOCK_FREE_CLASSROOM  = new HashMap<String, List<Integer>>() {
        {
            put("二教", new ArrayList<Integer>() {
                {
                    add(110);
                    add(210);
                    add(310);
                    add(212);
                    add(113);
                    add(116);
                    add(117);
                }
            });
            put("三教", new ArrayList<Integer>() {
                {
                    add(110);
                    add(210);
                    add(310);
                    add(212);
                    add(113);
                    add(116);
                    add(117);
                }
            });
            put("四教", new ArrayList<Integer>() {
                {
                    add(110);
                    add(210);
                    add(310);
                    add(212);
                    add(113);
                    add(116);
                    add(117);
                }
            });
            put("电教", new ArrayList<Integer>() {
                {
                    add(110);
                    add(210);
                    add(310);
                    add(212);
                    add(113);
                    add(116);
                    add(117);
                }
            });
        }
    };

    public static Map<String, List<Integer>> get() {
        return MOCK_FREE_CLASSROOM;
    }
}
