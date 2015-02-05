package com.pku.ipku.ui.pkuInfo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.pkuInfo.PkuPublicAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.pkuInfo.PkuInfoType;
import com.pku.ipku.model.pkuInfo.dto.PkuPublicInfo;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.ui.util.BaseActivityIncludingFooterNavigation;
import com.pku.ipku.ui.util.WebViewActivity;
import com.pku.ipku.util.AppContextHolder;

import java.util.List;

public class PkuPublicInfoActivity extends BaseActivityIncludingFooterNavigation {
    private static final String TYPE = "type";
    private PkuInfoType pkuInfoType;
    private ListView listView;
    private List<PkuPublicInfo> pkuPublicInfoList;

    public PkuPublicInfoActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_pku_public_info);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        if (getIntent() != null) {
            String pkuInfoTypeString = getIntent().getStringExtra(TYPE);
            if (pkuInfoTypeString != null) {
                pkuInfoType = new PkuInfoType();
                pkuInfoType.setType(pkuInfoTypeString);
            }
        }
        savedInstanceState.putString("title", pkuInfoType.getTitle());
        super.onCreate(savedInstanceState);
        initView();
        getData();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.pku_pub_info_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = (String) view.getTag();
                if (url != null) {
                    Intent intent = new Intent(PkuPublicInfoActivity.this, WebViewActivity.class);
                    intent.putExtra("url", url);
                    intent.putExtra("title", "公共信息");
                    startActivity(intent);
                }
            }
        });
    }

    private void getData() {
        new LoadDataDefaultTask(new LoadDataConfigure() {
            @Override
            public void showData() {
                listView.setAdapter(new PkuPublicAdapter(AppContextHolder.getAppContext(), pkuPublicInfoList));
            }

            @Override
            public boolean getData(boolean cache) {
                pkuPublicInfoList = IpkuServiceFactory.getPkuInfoService(cache).getPkuPublicNotice(pkuInfoType, 0);
                if (pkuPublicInfoList == null) {
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
