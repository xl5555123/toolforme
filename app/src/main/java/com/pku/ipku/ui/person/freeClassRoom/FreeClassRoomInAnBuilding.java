package com.pku.ipku.ui.person.freeClassRoom;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.google.common.collect.Lists;
import com.pku.ipku.R;
import com.pku.ipku.adapter.studyGuide.freeClassRoom.FreeClassAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.task.Result;
import com.pku.ipku.util.UIHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FreeClassRoomInAnBuilding extends FragmentActivity {

    private List<String> seletedBuidings;
    private List<Integer> selectedTime;

    private Map<String, List<String>> freeClassrooms;
    private View progress;

    private GridView freeClassTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_class_room_in_an_building);
        seletedBuidings = getIntent().getStringArrayListExtra("buildings");
        selectedTime = getIntent().getIntegerArrayListExtra("selectedTime");
        initView();
        initData();
    }

    private void initView() {
        freeClassTable = (GridView) findViewById(R.id.free_class_table);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setTitle("空闲教室");
        progress = findViewById(R.id.progress);
    }

    private Boolean hasClassroom(Map<String, List<String>> freeClassrooms) {
        for (String name : freeClassrooms.keySet()) {
            List<String> rooms = freeClassrooms.get(name);
            if (rooms != null && rooms.size() > 0) {
                return true;
            }
        }
        return false;
    }

    private void initData() {
        new LoadDataDefaultTask(new LoadDataConfigure() {
            @Override
            public void showData() {
                if (!hasClassroom(freeClassrooms)) {
                    UIHelper.ToastMessage("抱歉没有空闲教室");
                    finish();
                }
                freeClassTable.setAdapter(new FreeClassAdapter(freeClassrooms));
            }

            @Override
            public Result getData(boolean cache) {
                List<Integer> times = Lists.newArrayList();
                times.add(1);
                freeClassrooms = IpkuServiceFactory.getStudyGuideService(cache).getFreeClassrooms(seletedBuidings, times);
                if (freeClassrooms == null) {
                    return new Result(Result.NET_ERROR);
                }
                return new Result(Result.NO_ERROR);
            }

            @Override
            public void showWaiting() {
                if (progress != null) {
                    progress.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void stopWaiting() {
                if (progress != null) {
                    progress.setVisibility(View.GONE);
                }

            }

            @Override
            public void processError(Result result) {

            }
        }).execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
