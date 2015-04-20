package com.pku.ipku.ui.pkuInfo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.google.common.collect.Lists;
import com.pku.ipku.R;
import com.pku.ipku.adapter.pkuInfo.PkuPublicAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.PubInfo;
import com.pku.ipku.model.person.navigation.RegisterInPersonPage;
import com.pku.ipku.model.pkuInfo.PkuInfoType;
import com.pku.ipku.ui.util.BaseActivityIncludingFooterNavigation;
import com.pku.ipku.ui.util.paging.PagingListView;
import com.pku.ipku.util.AppContextHolder;
import com.pku.ipku.util.UIHelper;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PkuLectureActivity extends BaseActivityIncludingFooterNavigation implements RegisterInPersonPage {

    private List<PubInfo> datas;

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
                    adapter.addCollectData(pubInfo);
                }
                break;
            case R.id.uncollect:
                if (pkuPublicInfo.isCollected()) {
                    IpkuServiceFactory.getPkuInfoService(true).unCollect(pkuPublicInfo, new PkuInfoType("lecture"));
                    PubInfo pubInfo = new PubInfo(pkuPublicInfo, false);
                    adapter.removeCollectData(pubInfo);
                }
            default:
                return super.onContextItemSelected(item);
        }
        return super.onContextItemSelected(item);
    }


    private void initView() {
        datas = Lists.newLinkedList();
        List<PubInfo> collectResult = IpkuServiceFactory.getPkuInfoService(true).getCollected(new PkuInfoType("lecture"));
        datas.addAll(collectResult);
        listView = (PagingListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PubInfo pubInfo = (PubInfo) view.getTag();
                String url = pubInfo.getLink();
                if (url != null) {
                    /*
                    Intent intent = new Intent(PkuLectureActivity.this, WebViewActivity.class);
                    intent.putExtra("url", url);
                    intent.putExtra("title", "讲座信息");
                    startActivity(intent);
                    */
                    Intent intent = UIHelper.directToWebView(PkuLectureActivity.this, url);
                    startActivity(intent);
                }
            }
        });
        currentDateToLoad.add(Calendar.DATE, 1);
        adapter = new PkuPublicAdapter(AppContextHolder.getAppContext(), datas);
        listView.setAdapter(adapter);
        listView.setHasMoreItems(true);
        listView.setPagingableListener(new PagingListView.Pagingable() {
            @Override
            public void onLoadMoreItems() {
                new GetMoreDataTask().execute();
            }
        });
        registerForContextMenu(listView);
    }

    @Override
    public int getPageDrawableId() {
        return R.drawable.new_lecture;
    }

    @Override
    public String getPageTitle() {
        return "讲座信息";
    }

    @Override
    public int getPageBackgroundId() {
        return R.drawable.navigation_border_blue;
    }

    @Override
    public Class attachedClassType() {
        return PkuLectureActivity.class;
    }

    private class GetMoreDataTask extends AsyncTask<Void, Void, List<PubInfo>> {

        @Override
        protected List<PubInfo> doInBackground(Void... params) {
            currentDateToLoad.add(Calendar.DATE, -1);
            List<PubInfo> result = IpkuServiceFactory.getPkuInfoService(false).getPkuLecture(currentDateToLoad);
            while (result == null || result.size() == 0) {
                currentDateToLoad.add(Calendar.DATE, -1);
                result = IpkuServiceFactory.getPkuInfoService(false).getPkuLecture(currentDateToLoad);
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<PubInfo> result) {
            adapter.addData(result);
            adapter.notifyDataSetChanged();
            listView.onFinishLoading(true, null);
        }
    }
}
