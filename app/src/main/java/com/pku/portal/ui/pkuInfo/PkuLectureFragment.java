package com.pku.portal.ui.pkuInfo;


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

import com.pku.portal.R;
import com.pku.portal.adapter.person.PkuLectureAdapter;
import com.pku.portal.api.factory.IpkuServiceFactory;
import com.pku.portal.model.PubInfo;
import com.pku.portal.util.UIHelper;

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
    private List<PubInfo> pkuPublicInfoList = new ArrayList<PubInfo>();
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


    private class GetMoreDataTask extends AsyncTask<Void, Void, List<PubInfo>> {

        @Override
        protected List<PubInfo> doInBackground(Void... params) {
            List<PubInfo> result = IpkuServiceFactory.getPkuInfoService(false).getPkuLecture(currentDateToLoad);
            return result;
        }

        @Override
        protected void onPostExecute(List<PubInfo> result) {
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
