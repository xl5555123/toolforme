package com.pku.ipku.ui.pkuInfo;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.pkuInfo.PkuLectureAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.pkuInfo.dto.PkuPublicInfo;
import com.pku.ipku.util.UIHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PkuLectureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PkuLectureFragment extends Fragment {

    private ListView listView;
    private List<PkuPublicInfo> pkuPublicInfoList = new ArrayList<PkuPublicInfo>();
    private Calendar currentDateToLoad;
    private Activity activity;

    public static PkuLectureFragment newInstance() {
        return new PkuLectureFragment();
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
        currentDateToLoad = Calendar.getInstance();
        currentDateToLoad.setTime(new Date());
        currentDateToLoad.add(Calendar.DATE, 1);
        initView(view);
        new GetMoreDataTask().execute();
        return view;
    }

    private void initView(View view) {
        activity = getActivity();
        listView = (ListView) view.findViewById(R.id.lecture_list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = (String) view.getTag();
                if (url != null) {
                    Intent intent = UIHelper.directToWebView(getActivity(), url);
                    startActivity(intent);
                }
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
                try {
                    result = IpkuServiceFactory.getPkuInfoService(false).getPkuLecture(currentDateToLoad);
                } catch (Exception e) {
                    e.printStackTrace();
                    return result;
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<PkuPublicInfo> result) {
            if (result != null) {
                //noResult.setVisibility(View.GONE);
                pkuPublicInfoList = result;
                listView.setAdapter(new PkuLectureAdapter(activity, pkuPublicInfoList));
            } else {
                UIHelper.showNetError();
            }
        }
    }

}
