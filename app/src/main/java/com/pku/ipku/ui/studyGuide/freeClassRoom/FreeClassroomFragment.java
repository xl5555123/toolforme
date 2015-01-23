package com.pku.ipku.ui.studyGuide.freeClassRoom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.pku.ipku.R;

import java.util.ArrayList;
import java.util.List;

public class FreeClassroomFragment extends Fragment {

    private List<String> buildingNames = new ArrayList<String>() {
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

    private List<String> timeSelector = new ArrayList<String>() {
        {
            add("第1节");
            add("第2节");
            add("第3节");
            add("第4节");
            add("第5节");
            add("第6节");
            add("第7节");
            add("第8节");
            add("第9节");
            add("第10节");
            add("第11节");
            add("第12节");
        }
    };

    private TextView buildingsName;
    private TextView timeList;

    private ArrayList<String> seletedBuidings;
    private ArrayList<String> selectedTime;

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
        View view = inflater.inflate(R.layout.fragment_free_class, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        buildingsName = (TextView) view.findViewById(R.id.buidings_name);
        timeList = (TextView) view.findViewById(R.id.times_list);
        seletedBuidings = Lists.newArrayList();
        selectedTime = Lists.newArrayList();
        view.findViewById(R.id.buidings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new SelectDialogFragment("选择想要去的教学楼", buildingNames, buildingsName, seletedBuidings);
                dialogFragment.show(getFragmentManager(), "buidings");
            }
        });
        view.findViewById(R.id.times).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new SelectDialogFragment("选择想要自习的时间", timeSelector, timeList, selectedTime);
                dialogFragment.show(getFragmentManager(), "buidings");
            }
        });
        view.findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FreeClassRoomInAnBuilding.class);
                intent.putStringArrayListExtra("buildings", seletedBuidings);
                intent.putStringArrayListExtra("selectedTime", selectedTime);
                startActivity(intent);
            }
        });
    }

}
