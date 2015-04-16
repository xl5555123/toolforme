package com.pku.ipku.ui.person.freeClassRoom;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.pku.ipku.R;
import com.pku.ipku.adapter.pkuInfo.FreeClassAdapter;
import com.pku.ipku.api.util.NetHelper;

import org.json.JSONArray;

public class FreeClassRoomInAnBuilding extends FragmentActivity {

    private String seletedBuiding;
    private View progress;
    String url = NetHelper.BASE_URL + "/svcpub/svc/pub/classroom/today?appKey=579d8718c1b911e49c500050568508a5&buildingName=%s";
    RequestQueue mQueue;
    private GridView freeClassTable;
    Context context;
    JSONArray classrooms;
    FreeClassAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_class_room_in_an_building);
        seletedBuiding = getIntent().getStringExtra("buildings");
        url = String.format(url, seletedBuiding);
        mQueue = Volley.newRequestQueue(this);
        classrooms = new JSONArray();
        context = this;
        initView();
        getData();
    }

    private void initView() {
        freeClassTable = (GridView) findViewById(R.id.free_class_table);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setTitle(seletedBuiding + "空闲教室");
        progress = findViewById(R.id.progress);
    }

    void getData(){
        if (progress != null) {
            progress.setVisibility(View.VISIBLE);
        }
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (progress != null) {
                    progress.setVisibility(View.GONE);
                }
                if(response!=null){
                    classrooms = response;
                    String mock="[{\"room\":\"101\",\"c1\":\"第1节占用情况\",\"c2\":\"第2节占用情况\",\"c3\":\"第3节占用情况\",\"c4\":\"第4节占用情况\",\"c5\":\"第5节占用情况\",\"c6\":\"第6节占用情况\",\"c7\":\"第7节占用情况\",\"c8\":\"第8节占用情况\",\"c9\":\"第9节占用情况\",\"c10\":\"第10节占用情况\",\"c11\":\"第11节占用情况\",\"c12\":\"第12节占用情况\"},{\"room\":\"102\",\"c1\":\"第1节占用情况\",\"c2\":\"第2节占用情况\",\"c3\":\"第3节占用情况\",\"c4\":\"第4节占用情况\",\"c5\":\"第5节占用情况\",\"c6\":\"第6节占用情况\",\"c7\":\"第7节占用情况\",\"c8\":\"第8节占用情况\",\"c9\":\"第9节占用情况\",\"c10\":\"第10节占用情况\",\"c11\":\"第11节占用情况\",\"c12\":\"第12节占用情况\"},{\"room\":\"103\",\"c1\":\"第1节占用情况\",\"c2\":\"第2节占用情况\",\"c3\":\"第3节占用情况\",\"c4\":\"第4节占用情况\",\"c5\":\"第5节占用情况\",\"c6\":\"第6节占用情况\",\"c7\":\"第7节占用情况\",\"c8\":\"第8节占用情况\",\"c9\":\"第9节占用情况\",\"c10\":\"第10节占用情况\",\"c11\":\"第11节占用情况\",\"c12\":\"第12节占用情况\"},{\"room\":\"104\",\"c1\":\"第1节占用情况\",\"c2\":\"第2节占用情况\",\"c3\":\"第3节占用情况\",\"c4\":\"第4节占用情况\",\"c5\":\"第5节占用情况\",\"c6\":\"第6节占用情况\",\"c7\":\"第7节占用情况\",\"c8\":\"第8节占用情况\",\"c9\":\"第9节占用情况\",\"c10\":\"第10节占用情况\",\"c11\":\"第11节占用情况\",\"c12\":\"第12节占用情况\"},{\"room\":\"105\",\"c1\":\"第1节占用情况\",\"c2\":\"第2节占用情况\",\"c3\":\"第3节占用情况\",\"c4\":\"第4节占用情况\",\"c5\":\"第5节占用情况\",\"c6\":\"第6节占用情况\",\"c7\":\"第7节占用情况\",\"c8\":\"第8节占用情况\",\"c9\":\"第9节占用情况\",\"c10\":\"第10节占用情况\",\"c11\":\"第11节占用情况\",\"c12\":\"第12节占用情况\"},{\"room\":\"106\",\"c1\":\"第1节占用情况\",\"c2\":\"第2节占用情况\",\"c3\":\"第3节占用情况\",\"c4\":\"第4节占用情况\",\"c5\":\"第5节占用情况\",\"c6\":\"第6节占用情况\",\"c7\":\"第7节占用情况\",\"c8\":\"第8节占用情况\",\"c9\":\"第9节占用情况\",\"c10\":\"第10节占用情况\",\"c11\":\"第11节占用情况\",\"c12\":\"第12节占用情况\"},{\"room\":\"107\",\"c1\":\"第1节占用情况\",\"c2\":\"第2节占用情况\",\"c3\":\"第3节占用情况\",\"c4\":\"第4节占用情况\",\"c5\":\"第5节占用情况\",\"c6\":\"第6节占用情况\",\"c7\":\"第7节占用情况\",\"c8\":\"第8节占用情况\",\"c9\":\"第9节占用情况\",\"c10\":\"第10节占用情况\",\"c11\":\"第11节占用情况\",\"c12\":\"第12节占用情况\"},{\"room\":\"108\",\"c1\":\"第1节占用情况\",\"c2\":\"第2节占用情况\",\"c3\":\"第3节占用情况\",\"c4\":\"第4节占用情况\",\"c5\":\"第5节占用情况\",\"c6\":\"第6节占用情况\",\"c7\":\"第7节占用情况\",\"c8\":\"第8节占用情况\",\"c9\":\"第9节占用情况\",\"c10\":\"第10节占用情况\",\"c11\":\"第11节占用情况\",\"c12\":\"第12节占用情况\"},{\"room\":\"109\",\"c1\":\"第1节占用情况\",\"c2\":\"第2节占用情况\",\"c3\":\"第3节占用情况\",\"c4\":\"第4节占用情况\",\"c5\":\"第5节占用情况\",\"c6\":\"第6节占用情况\",\"c7\":\"第7节占用情况\",\"c8\":\"第8节占用情况\",\"c9\":\"第9节占用情况\",\"c10\":\"第10节占用情况\",\"c11\":\"第11节占用情况\",\"c12\":\"第12节占用情况\"}]";
                    try {
                        classrooms = new JSONArray(mock);
                    }catch(Exception e){

                    }
                    myAdapter = new FreeClassAdapter(context, classrooms,freeClassTable);
                    freeClassTable.setAdapter(myAdapter);

                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (progress != null) {
                    progress.setVisibility(View.GONE);
                }
                classrooms = new JSONArray();
                myAdapter = new FreeClassAdapter(context, classrooms,freeClassTable);
                freeClassTable.setAdapter(myAdapter);
                Log.i("liuyi", error.getMessage());
            }
        });
        mQueue.add(jsonArrayRequest);
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
