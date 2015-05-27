package com.pku.portal.ui.setting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.common.collect.Lists;
import com.pku.portal.R;
import com.pku.portal.adapter.person.PhoneAdapter;
import com.pku.portal.model.setting.Telephone;
import com.pku.portal.model.setting.TelephoneStatic;
import com.pku.portal.ui.util.BaseActivityIncludingFooterNavigation;
import com.pku.portal.util.DataHandleUtil;

import java.util.List;

public class PhoneActivity extends BaseActivityIncludingFooterNavigation {

    private List<Telephone> phoneList = Lists.newArrayList(DataHandleUtil.stringToObject(Telephone[].class, TelephoneStatic.TELEPHONE_JSON));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_phone);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", "常用电话");
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        ListView listView = (ListView) findViewById(R.id.phone_list);
        if (listView != null) {
            listView.setAdapter(new PhoneAdapter(this, phoneList));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final Telephone telephone = phoneList.get(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(PhoneActivity.this);
                    builder.setTitle("拨打电话")
                            .setMessage(String.format("拨打%s电话：%s?", telephone.getDepartment(), telephone.getTelephone()))
                            .setPositiveButton("拨打", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String strMobile = telephone.getTelephone();
                                    //此处应该对电话号码进行验证。。
                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + strMobile));

                                    startActivity(intent);
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).create().show();
                }
            });
        }
    }
}
