package com.pku.ipku.ui.pkuInfo;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.common.collect.Lists;
import com.pku.ipku.R;
import com.pku.ipku.adapter.pkuInfo.PkuPublicAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.pkuInfo.PkuInfoType;
import com.pku.ipku.model.pkuInfo.dto.PkuPublicInfo;
import com.pku.ipku.ui.util.WebViewActivity;
import com.pku.ipku.ui.util.paging.PagingListView;
import com.pku.ipku.util.AppContextHolder;
import com.pku.ipku.util.UIHelper;

import java.util.Calendar;
import java.util.List;

public class PkuPublicInfoWithoutPagingFragment extends Fragment {

    private static final String SERVICE = "service";

    private PkuInfoType type;

    private int currentPage;

    private PagingListView listView;

    private List<PkuPublicInfo> pkuPublicInfoList;

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
        pkuPublicInfoList = Lists.newArrayList();
        adapter = new PkuPublicAdapter(AppContextHolder.getAppContext(), pkuPublicInfoList);
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
                String url = (String) view.getTag();
                if (url != null) {
                    /*
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("url", url);
                    intent.putExtra("title", type.getTitle());
                    startActivity(intent);
                    */
                    Intent intent= UIHelper.directToWebView(getActivity(), url);
                    startActivity(intent);
                }
            }
        });
    }

    private class GetMoreDataTask extends AsyncTask<Void, Void, List<PkuPublicInfo>> {

        @Override
        protected List<PkuPublicInfo> doInBackground(Void... params) {
            try {
                currentPage++;
                List<PkuPublicInfo> result = IpkuServiceFactory.getPkuInfoService(false).getPkuPublicNotice(type, currentPage);
                return result;
            } catch (Exception e) {
                return null;
            }
        }
        @Override
        protected void onPostExecute(List<PkuPublicInfo> result) {
            listView.onFinishLoading(true, null);
            if (result != null) {
                pkuPublicInfoList.addAll(result);
                adapter.notifyDataSetChanged();
            }
            else {
                listView.setHasMoreItems(false);
            }
        }
    }

}
