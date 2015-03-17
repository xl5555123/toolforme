package com.pku.ipku.ui.pkuInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.pkuInfo.PkuPublicAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.pkuInfo.PkuInfoType;
import com.pku.ipku.model.pkuInfo.dto.PkuPublicInfo;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.task.Result;
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
            public Result getData(boolean cache) {
                pkuPublicInfoList = IpkuServiceFactory.getPkuInfoService(cache).getPkuPublicNotice(pkuInfoType, 0);
                if (pkuPublicInfoList == null) {
                    return new Result(Result.NET_ERROR);
                }
                return new Result(Result.NO_ERROR);
            }

            @Override
            public void showWaiting() {

            }

            @Override
            public void stopWaiting() {

            }

            @Override
            public void processError(Result result) {

            }
        }).execute();
    }

}
