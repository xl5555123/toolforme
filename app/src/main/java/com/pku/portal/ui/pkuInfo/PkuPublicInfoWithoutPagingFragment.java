package com.pku.portal.ui.pkuInfo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.common.collect.Lists;
import com.pku.portal.R;
import com.pku.portal.adapter.person.PkuPublicAdapter;
import com.pku.portal.api.factory.IpkuServiceFactory;
import com.pku.portal.model.PubInfo;
import com.pku.portal.model.pkuInfo.PkuInfoType;
import com.pku.portal.ui.util.paging.PagingListView;
import com.pku.portal.util.AppContextHolder;
import com.pku.portal.util.UIHelper;

import java.util.List;

public class PkuPublicInfoWithoutPagingFragment extends Fragment {

    private static final String SERVICE = "service";

    private PkuInfoType type;

    private int currentPage;

    private PagingListView listView;

    private List<PubInfo> datas;

    private PkuPublicAdapter adapter;

    public static PkuPublicInfoWithPagingFragment newInstance(PkuInfoType pkuInfoType) {
        PkuPublicInfoWithPagingFragment fragment = new PkuPublicInfoWithPagingFragment();
        Bundle args = new Bundle();
        args.putString(SERVICE, pkuInfoType.getType());
        fragment.setArguments(args);
        return fragment;
    }

    public PkuPublicInfoWithoutPagingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = new PkuInfoType(getArguments().getString(SERVICE));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pku_public_info2, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        datas = Lists.newLinkedList();
        List<PubInfo> collectResult = IpkuServiceFactory.getPkuInfoService(true).getCollected(type);
        datas.addAll(collectResult);
        adapter = new PkuPublicAdapter(AppContextHolder.getAppContext(), datas);
        currentPage = -1;
        listView = (PagingListView) view.findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setHasMoreItems(true);
        listView.setPagingableListener(new PagingListView.Pagingable() {
            @Override
            public void onLoadMoreItems() {
                new GetMoreDataTask().execute();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PubInfo pubInfo = (PubInfo) view.getTag();
                String url = pubInfo.getLink();
                if (url != null) {
                    /*
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("url", url);
                    intent.putExtra("title", type.getTitle());
                    startActivity(intent);
                    */
                    Intent intent = UIHelper.directToWebView(getActivity(), url);
                    startActivity(intent);
                }
            }
        });
    }

    //创建ContextMenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.list) {
            MenuInflater inflater = getActivity().getMenuInflater();
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
                    IpkuServiceFactory.getPkuInfoService(true).collect(pkuPublicInfo, type);
                    PubInfo pubInfo = new PubInfo(pkuPublicInfo, true);
                    adapter.addCollectData(pubInfo);
                }
                break;
            case R.id.uncollect:
                if (pkuPublicInfo.isCollected()) {
                    IpkuServiceFactory.getPkuInfoService(true).unCollect(pkuPublicInfo, type);
                    PubInfo pubInfo = new PubInfo(pkuPublicInfo, false);
                    adapter.removeCollectData(pubInfo);
                }
            default:
                return super.onContextItemSelected(item);
        }
        return super.onContextItemSelected(item);
    }

    private class GetMoreDataTask extends AsyncTask<Void, Void, List<PubInfo>> {

        @Override
        protected List<PubInfo> doInBackground(Void... params) {
            try {
                currentPage++;
                List<PubInfo> result = IpkuServiceFactory.getPkuInfoService(false).getPkuPublicNotice(type, currentPage);
                return result;
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<PubInfo> result) {
            listView.onFinishLoading(true, null);
            if (result != null) {
                adapter.addData(result);
                adapter.notifyDataSetChanged();
            } else {
                listView.setHasMoreItems(false);
            }
        }
    }

}
