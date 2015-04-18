package com.pku.ipku.ui.pkuInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.common.collect.Lists;
import com.pku.ipku.R;
import com.pku.ipku.adapter.pkuInfo.PkuPublicAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.PubInfo;
import com.pku.ipku.model.pkuInfo.PkuInfoType;
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
    private List<PubInfo> datas;

    private PkuPublicAdapter pkuPublicAdapter;

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
        datas = Lists.newLinkedList();
        listView = (ListView) findViewById(R.id.pku_pub_info_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PubInfo pubInfo = (PubInfo) view.getTag();
                String url = pubInfo.getLink();
                if (url != null) {
                    Intent intent = new Intent(PkuPublicInfoActivity.this, WebViewActivity.class);
                    intent.putExtra("url", url);
                    intent.putExtra("title", "公共信息");
                    startActivity(intent);
                }
            }
        });
        registerForContextMenu(listView);
        pkuPublicAdapter = new PkuPublicAdapter(AppContextHolder.getAppContext(), datas);
        listView.setAdapter(pkuPublicAdapter);
    }

    //创建ContextMenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.list) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.pku_pub_context_menu, menu);
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // 得到当前被选中的item信息
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        View view = menuInfo.targetView;

        PubInfo pkuPublicInfo = (PubInfo) view.getTag();
        switch(item.getItemId()) {
            case R.id.collect:
                if (!pkuPublicInfo.isCollected()) {
                    IpkuServiceFactory.getPkuInfoService(true).collect(pkuPublicInfo, new PkuInfoType("lecture"));
                    PubInfo pubInfo = new PubInfo(pkuPublicInfo, true);
                    pkuPublicAdapter.addCollectData(pubInfo);
                }
                break;
            case R.id.uncollect:
                if (pkuPublicInfo.isCollected()) {
                    IpkuServiceFactory.getPkuInfoService(true).unCollect(pkuPublicInfo, new PkuInfoType("lecture"));
                    PubInfo pubInfo = new PubInfo(pkuPublicInfo, false);
                    pkuPublicAdapter.removeCollectData(pubInfo);
                }
            default:
                return super.onContextItemSelected(item);
        }
        return super.onContextItemSelected(item);
    }


    private void getData() {
        new LoadDataDefaultTask(new LoadDataConfigure() {
            @Override
            public void showData() {
                pkuPublicAdapter.notifyDataSetChanged();
            }

            @Override
            public Result getData(boolean cache) {
                List<PubInfo> collectResult = IpkuServiceFactory.getPkuInfoService(true).getCollected(pkuInfoType);
                datas.addAll(collectResult);
                List<PubInfo> result  = IpkuServiceFactory.getPkuInfoService(cache).getPkuPublicNotice(pkuInfoType, 0);
                pkuPublicAdapter.addData(result);
                if (result == null) {
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
