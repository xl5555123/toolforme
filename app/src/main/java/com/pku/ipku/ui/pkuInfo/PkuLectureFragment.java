package com.pku.ipku.ui.pkuInfo;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.pkuInfo.PkuLectureAdapter;
import com.pku.ipku.adapter.pkuInfo.PkuPublicAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.pkuInfo.PkuInfoType;
import com.pku.ipku.model.pkuInfo.dto.PkuPublicInfo;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.ui.util.WebViewActivity;
import com.pku.ipku.util.AppContextHolder;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PkuLectureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PkuLectureFragment extends Fragment {

    private ListView listView;
    private List<PkuPublicInfo> pkuPublicInfoList;

    public static PkuLectureFragment newInstance() {
        PkuLectureFragment fragment = new PkuLectureFragment();
        return fragment;
    }

    public PkuLectureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pku_lecture2, container, false);
        initView(view);
        getData();
        return view;
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = (String) view.getTag();
                if (url != null) {
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
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
                listView.setAdapter(new PkuLectureAdapter(getActivity(), pkuPublicInfoList));
            }

            @Override
            public boolean getData(boolean cache) {
                pkuPublicInfoList = IpkuServiceFactory.getPkuInfoService(cache).getPkuPublicNotice(new PkuInfoType(PkuInfoType.PKU_LECTURES), 0);
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
