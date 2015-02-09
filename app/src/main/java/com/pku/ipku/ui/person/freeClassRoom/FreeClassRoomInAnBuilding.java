package com.pku.ipku.ui.person.freeClassRoom;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.studyGuide.freeClassRoom.BuidingAdapter;
import com.pku.ipku.adapter.studyGuide.freeClassRoom.FreeClassAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FreeClassRoomInAnBuilding extends Activity {

    private ArrayList<String> seletedBuidings;
    private ArrayList<String> selectedTime;

    private Map<String, List<Integer>> freeClassrooms;

    private GridView freeClassTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_class_room_in_an_building);
        seletedBuidings = getIntent().getStringArrayListExtra("buildings");
        selectedTime = getIntent().getStringArrayListExtra("selectedTime");
        initView();
        initData();
    }

    private void initView() {
        freeClassTable = (GridView) findViewById(R.id.free_class_table);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setTitle("空闲教室");
    }

    private void initData() {
        new LoadDataDefaultTask(new LoadDataConfigure() {
            @Override
            public void showData() {
                freeClassTable.setAdapter(new FreeClassAdapter(freeClassrooms));
            }

            @Override
            public boolean getData(boolean cache) {
                freeClassrooms =  IpkuServiceFactory.getStudyGuideService(cache).getFreeClassrooms(seletedBuidings, selectedTime);
                if (freeClassrooms == null) {
                    return false;
                }
                return true;
            }

            @Override
            public void showWaiting() {

            }

            @Override
            public void stopWaiting() {

            }
        }).execute();
    }
}
