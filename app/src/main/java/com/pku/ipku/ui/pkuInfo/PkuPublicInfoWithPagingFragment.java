package com.pku.ipku.ui.pkuInfo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
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
import com.pku.ipku.ui.util.paging.PagingListView;
import com.pku.ipku.util.AppContextHolder;

import java.util.List;

public class PkuPublicInfoWithPagingFragment extends Fragment {
    private static final String SERVICE = "service";

    private PkuInfoType type;

    private PagingListView listView;

    private List<PkuPublicInfo> pkuPublicInfoList;

    private PkuPublicAdapter adapter;

    public static PkuPublicInfoWithoutPagingFragment newInstance(PkuInfoType pkuInfoType) {
        PkuPublicInfoWithoutPagingFragment fragment = new PkuPublicInfoWithoutPagingFragment();
        Bundle args = new Bundle();
        args.putString(SERVICE, pkuInfoType.getType());
        fragment.setArguments(args);
        return fragment;
    }

    public PkuPublicInfoWithPagingFragment() {
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
                    Intent intent= new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(url);
                    intent.setData(content_url);
                    startActivity(intent);
                }
            }
        });
    }

    private class GetMoreDataTask extends AsyncTask<Void, Void, List<PkuPublicInfo>> {

        @Override
        protected List<PkuPublicInfo> doInBackground(Void... params) {
            try {
                List<PkuPublicInfo> result = IpkuServiceFactory.getPkuInfoService(false).getPkuPublicNotice(type, 0);
                return result;
            } catch (Exception e) {
                return null;
            }
        }
        @Override
        protected void onPostExecute(List<PkuPublicInfo> result) {
            if (result != null) {
                pkuPublicInfoList.addAll(result);
                adapter.notifyDataSetChanged();
            }
            listView.onFinishLoading(true, null);
            listView.setHasMoreItems(false);
        }
    }

}
