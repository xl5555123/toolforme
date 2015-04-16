package com.pku.ipku.ui.person.freeClassRoom;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.pkuInfo.SelectAdapter;
import com.pku.ipku.model.person.navigation.RegisterInPersonPage;
import com.pku.ipku.ui.util.BaseActivityIncludingFooterNavigation;
import com.pku.ipku.util.UIHelper;

import java.util.ArrayList;
import java.util.List;

public class FreeClassroomActivity extends BaseActivityIncludingFooterNavigation implements RegisterInPersonPage {

    PopupWindow pop;
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
        initView();
    }

    private void initView() {
        buildingsName = (TextView) findViewById(R.id.buidings_name);

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
        pop  = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,false);
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
        pop.showAsDropDown(textViewToChange);
    }
}
