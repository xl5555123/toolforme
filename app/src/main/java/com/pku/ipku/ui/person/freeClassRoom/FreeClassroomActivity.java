package com.pku.ipku.ui.person.freeClassRoom;


import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.pku.ipku.R;
import com.pku.ipku.model.person.navigation.RegisterInPersonPage;
import com.pku.ipku.ui.util.BaseActivityIncludingFooterNavigation;

import java.util.ArrayList;
import java.util.List;

public class FreeClassroomActivity extends BaseActivityIncludingFooterNavigation implements RegisterInPersonPage {

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_free_class);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", "空闲教室");
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        buildingsName = (TextView) findViewById(R.id.buidings_name);
        timeList = (TextView) findViewById(R.id.times_list);
        seletedBuidings = Lists.newArrayList();
        selectedTime = Lists.newArrayList();
        findViewById(R.id.buidings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new SelectDialogFragment("选择想要去的教学楼", buildingNames, buildingsName, seletedBuidings);
                dialogFragment.show(getFragmentManager(), "buidings");
            }
        });
        findViewById(R.id.times).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new SelectDialogFragment("选择想要自习的时间", timeSelector, timeList, selectedTime);
                dialogFragment.show(getFragmentManager(), "buidings");
            }
        });
        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FreeClassroomActivity.this, FreeClassRoomInAnBuilding.class);
                intent.putStringArrayListExtra("buildings", seletedBuidings);
                intent.putStringArrayListExtra("selectedTime", selectedTime);
                startActivity(intent);
            }
        });
    }

    @Override
    public int getPageDrawableId() {
        return R.drawable.user_classroom;
    }

    @Override
    public String getPageTitle() {
        return "空闲教室";
    }

    @Override
    public Class attachedClassType() {
        return FreeClassroomActivity.class;
    }
}
