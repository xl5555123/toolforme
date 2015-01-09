package com.pku.ipku.ui.pkuInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.pkuInfo.PkuClubAdapter;
import com.pku.ipku.api.MockPkuClubActivityList;
import com.pku.ipku.model.pkuInfo.dto.PkuClubDTO;
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
        //获取社团活动对象
        pkuClubDTOList = MockPkuClubActivityList.get();
        clubActivitiesListView.setAdapter(new PkuClubAdapter(appContext, pkuClubDTOList));
        //点击事件
        clubActivitiesListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent();
        intent.setClass(this.getActivity(),PkuClubDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(CLUB_ACTIVITY_KEY, pkuClubDTOList.get(i));
        intent.putExtras(bundle);
        Log.d("DEBUG", "============Intent start" + getActivity().toString() + this.toString() + i);
        this.startActivity(intent);
    }
}
