package com.pku.ipku.ui.person;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.person.ScholarshipAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.person.dto.ScholarShipDTO;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.ui.AppContext;

import java.util.List;


public class ScholarshipFragment extends Fragment {
    private List<ScholarShipDTO> scholarshipList;
    private ListView scholarshipListView;
    private AppContext appContext;

    public ScholarshipFragment() {
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
        View view = inflater.inflate(R.layout.activity_scholarship, container, false);
        scholarshipListView = (ListView)view.findViewById(R.id.scholarship_list);
        appContext = (AppContext)getActivity().getApplicationContext();
        new LoadDataDefaultTask(new LoadScholarShipConfigure()).execute();
        return view;
    }

    private class LoadScholarShipConfigure implements LoadDataConfigure {

        @Override
        public void showData() {
            scholarshipListView.setAdapter(new ScholarshipAdapter(appContext, scholarshipList));
        }

        @Override
        public boolean getData(boolean cache) {
            scholarshipList = IpkuServiceFactory.getPersonService(cache).getScholarShips();
            if (scholarshipList == null) {
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
