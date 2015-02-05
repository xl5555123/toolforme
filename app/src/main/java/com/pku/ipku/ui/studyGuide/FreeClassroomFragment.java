package com.pku.ipku.ui.studyGuide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pku.ipku.R;

import java.util.ArrayList;
import java.util.List;

public class FreeClassroomFragment extends Fragment {

    private List<String> buildings = new ArrayList<String>() {
        {
            add("第一教学楼");
            add("第二教学楼");
            add("第三教学楼");
            add("第四教学楼");
            add("文史");
            add("电教");
            add("理科教学楼");
            add("化学");
            add("电教听力");
            add("国关");
            add("政管");
        }
    };

    //private ListView

    public FreeClassroomFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_free_class, container, false);
    }

}
