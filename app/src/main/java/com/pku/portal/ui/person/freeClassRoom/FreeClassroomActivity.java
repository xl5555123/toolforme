package com.pku.portal.ui.person.freeClassRoom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.pku.portal.R;
import com.pku.portal.adapter.person.FreeClassHistoryAdapter;
import com.pku.portal.adapter.person.SelectAdapter;
import com.pku.portal.model.navigation.RegisterInPersonPage;
import com.pku.portal.ui.util.BaseActivityIncludingFooterNavigation;
import com.pku.portal.util.UIHelper;

import java.util.ArrayList;
import java.util.List;

public class FreeClassroomActivity extends BaseActivityIncludingFooterNavigation implements RegisterInPersonPage {

    PopupWindow pop;
    GridView history_gv;
    String history;
    Context context;
    static final String SEARCH_HISTORY = "SEARCH_HISTORY";
    SharedPreferences mySharedPreferences;
    SharedPreferences.Editor editor;
    FreeClassHistoryAdapter myAdapter;
    private List<String> buildingNames = new ArrayList<String>() {
        {
            add("一教");
            add("二教");
            add("三教");
            add("四教");
            add("文史");
            add("电教");
            add("哲学");
            add("理教");
            add("地学");
            add("技物");
            add("外文");
            add("体教");
            add("数学");
            add("化学");
            add("电子");
            add("电教听力");
            add("国关");
            add("政管");
            add("理科3#楼");
            add("理科2#楼");

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

    private String seletedBuiding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_free_class);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", "空闲教室");
        super.onCreate(savedInstanceState);
        mySharedPreferences= getSharedPreferences(SEARCH_HISTORY,Activity.MODE_PRIVATE);
        editor = mySharedPreferences.edit();
        history = mySharedPreferences.getString(SEARCH_HISTORY, "");
        context = this;
        initView();
    }

    private void initView() {
        buildingsName = (TextView) findViewById(R.id.buidings_name);
        history_gv = (GridView) findViewById(R.id.history_gv);

        myAdapter = new FreeClassHistoryAdapter(context,history);
        history_gv.setAdapter(myAdapter);

        history_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FreeClassroomActivity.this, FreeClassRoomInAnBuilding.class);
                intent.putExtra("buildings", myAdapter.getItem(position).toString());
                startActivity(intent);
            }
        });

        findViewById(R.id.buidings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DialogFragment dialogFragment = new SelectDialogFragment("选择想要去的教学楼", buildingNames, buildingsName, seletedBuidings, seletedBuidingPositions);
                //dialogFragment.show(getFragmentManager(), "buidings");
                createPopWindow(buildingNames, buildingsName);
            }
        });
        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seletedBuiding == null || seletedBuiding.equals("")) {
                    UIHelper.ToastMessage("请选择自习教室");
                    return;
                } else {
                    Intent intent = new Intent(FreeClassroomActivity.this, FreeClassRoomInAnBuilding.class);
                    intent.putExtra("buildings", seletedBuiding);
                    if(!history.contains(seletedBuiding))
                    {
                        if(history.equals(""))
                            history = history + seletedBuiding;
                        else
                            history = history + ","+seletedBuiding;
                        editor.putString(SEARCH_HISTORY,history);
                        editor.commit();
                    }
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getPageDrawableId() {
        return R.drawable.new_classroom;
    }

    @Override
    public String getPageTitle() {
        return "空闲教室";
    }

    @Override
    public int getPageBackgroundId() {
        return R.drawable.navigation_border_blue;
    }

    @Override
    public Class attachedClassType() {
        return FreeClassroomActivity.class;
    }


    @Override

    public boolean onTouchEvent(MotionEvent event) {

        // TODO Auto-generated method stub
        switch(event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (pop != null && pop.isShowing()) {
                    pop.dismiss();
                }
            default:
                break;
        }
        return super.onTouchEvent(event);

    }

    private void  createPopWindow(final List<String> itemToSelect, final TextView textViewToChange){
        View view = LayoutInflater.from(this).inflate(R.layout.popup_window, null);
        pop  = new PopupWindow(view, 250,LinearLayout.LayoutParams.WRAP_CONTENT,false);
        pop.setBackgroundDrawable(new BitmapDrawable());
        //设置点击窗口外边窗口消失
        pop.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        pop.setFocusable(true);
        final ListView content_lv = (ListView) view.findViewById(R.id.content_lv);
        final SelectAdapter selectAdapter = new SelectAdapter(this, itemToSelect);
        content_lv.setAdapter(selectAdapter);
        content_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                seletedBuiding = itemToSelect.get(position);
                textViewToChange.setText(seletedBuiding);
                pop.dismiss();
            }
        });
        pop.showAsDropDown(textViewToChange,0,20);
    }
}
