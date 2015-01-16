package com.pku.ipku.ui.pkuInfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.pkuInfo.PkuJobAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.pkuInfo.dto.PkuJobDTO;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.ui.AppContext;

import java.util.List;

public class PkuJobFragment extends Fragment {

    private List<PkuJobDTO> pkuJobList;

    private ListView pkuJobListView;
    private AppContext appContext;

    public PkuJobFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_pku_job, container, false);
        pkuJobListView = (ListView) view.findViewById(R.id.job_listview);
        appContext = (AppContext)getActivity().getApplicationContext();
        new LoadDataDefaultTask(new LoadPkuJobConfigure()).execute();
        return view;
    }

    private class LoadPkuJobConfigure implements LoadDataConfigure {

        @Override
        public void showData() {
            pkuJobListView.setAdapter(new PkuJobAdapter(appContext, pkuJobList));
        }

        @Override
        public boolean getData(boolean cache) {
            pkuJobList = IpkuServiceFactory.getPkuInfoService(cache).getPkuJobs();
            if (pkuJobList == null) {
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
