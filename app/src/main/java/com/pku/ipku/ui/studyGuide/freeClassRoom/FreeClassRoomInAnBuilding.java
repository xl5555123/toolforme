package com.pku.ipku.ui.studyGuide.freeClassRoom;

import android.support.v7.app.ActionBarActivity;
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

public class FreeClassRoomInAnBuilding extends ActionBarActivity {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_free_class_room_in_an_building, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
