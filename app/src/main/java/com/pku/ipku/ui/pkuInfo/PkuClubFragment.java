package com.pku.ipku.ui.pkuInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.pku.ipku.R;
import com.pku.ipku.adapter.pkuInfo.PkuClubAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.pkuInfo.dto.PkuClubDTO;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.ui.AppContext;
import java.util.List;


public class PkuClubFragment extends Fragment implements AdapterView.OnItemClickListener {

    private List<PkuClubDTO> pkuClubDTOList;

    private ListView clubActivitiesListView;
    private AppContext appContext;

    public static final String CLUB_ACTIVITY_KEY  = "club_activity_key";

    public PkuClubFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pku_club, container, false);
        clubActivitiesListView = (ListView)view.findViewById(R.id.club_activities);
        appContext = (AppContext)getActivity().getApplicationContext();
        new LoadDataDefaultTask(new LoadPkuClubConfigure()).execute();
        //点击事件无法跳转
        //clubActivitiesListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent();
        intent.putExtra("CLUB_ACTIVITY_KEY", pkuClubDTOList.get(i).getAttachUrl());
        intent.setClass(this.getActivity(), PkuClubDetailActivity.class);
        startActivity(intent);
    }

    private class LoadPkuClubConfigure implements LoadDataConfigure {

        @Override
        public void showData() {
            clubActivitiesListView.setAdapter(new PkuClubAdapter(appContext, pkuClubDTOList));
        }

        @Override
        public boolean getData(boolean cache) {
            pkuClubDTOList = IpkuServiceFactory.getPkuInfoService(cache).getPkuClubActivities();
            if (pkuClubDTOList == null) {
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
    }
}
