package com.pku.ipku.ui.pkuInfo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.google.common.collect.Lists;
import com.pku.ipku.R;
import com.pku.ipku.adapter.pkuInfo.PkuPublicAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.pkuInfo.dto.PkuPublicInfo;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.ui.util.BaseActivityIncludingFooterNavigation;
import com.pku.ipku.ui.util.WebViewActivity;
import com.pku.ipku.ui.util.paging.PagingListView;
import com.pku.ipku.util.AppContextHolder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PkuLectureActivity extends BaseActivityIncludingFooterNavigation {

    private List<PkuPublicInfo> pkuPublicInfoList;

    private Calendar currentDateToLoad;

    private PagingListView listView;

    private PkuPublicAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pku_lecture2);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", "讲座信息");
        super.onCreate(savedInstanceState);
        currentDateToLoad = Calendar.getInstance();
        currentDateToLoad.setTime(new Date());
        initView();
    }


    private void initView() {
        listView = (PagingListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = (String) view.getTag();
                if (url != null) {
                    Intent intent = new Intent(PkuLectureActivity.this, WebViewActivity.class);
                    intent.putExtra("url", url);
                    intent.putExtra("title", "讲座信息");
                    startActivity(intent);
                }
            }
        });
        currentDateToLoad.add(Calendar.DATE, 1);
        pkuPublicInfoList = Lists.newArrayList();
        adapter = new PkuPublicAdapter(AppContextHolder.getAppContext(), pkuPublicInfoList);
        listView.setAdapter(adapter);
        listView.setHasMoreItems(true);
        listView.setPagingableListener(new PagingListView.Pagingable() {
            @Override
            public void onLoadMoreItems() {
                new GetMoreDataTask().execute();
            }
        });
    }

    private class GetMoreDataTask extends AsyncTask<Void, Void, List<PkuPublicInfo>> {

        @Override
        protected List<PkuPublicInfo> doInBackground(Void... params) {
            currentDateToLoad.add(Calendar.DATE, -1);
            List<PkuPublicInfo> result = IpkuServiceFactory.getPkuInfoService(false).getPkuLecture(currentDateToLoad);
            while (result == null || result.size() == 0) {
                currentDateToLoad.add(Calendar.DATE, -1);
                result = IpkuServiceFactory.getPkuInfoService(false).getPkuLecture(currentDateToLoad);
            }
            return result;
        }
        @Override
        protected void onPostExecute(List<PkuPublicInfo> result) {
            pkuPublicInfoList.addAll(result);
            adapter.notifyDataSetChanged();
            listView.onFinishLoading(true, null);
        }
    }
}
