package com.pku.ipku.ui.person;

import android.os.Bundle;
import android.widget.ListView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.person.ScholarshipAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.person.dto.ScholarShipDTO;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.ui.AppContext;
import com.pku.ipku.ui.util.BaseActivityIncludingFooterNavigation;

import java.util.List;

/**
 * Created by XingLiang on 2015/2/5.
 */
public class ScholarshipActivity extends BaseActivityIncludingFooterNavigation {

    private List<ScholarShipDTO> scholarshipList;
    private ListView scholarshipListView;
    private AppContext appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_scholarship);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", "奖学金");
        super.onCreate(savedInstanceState);
        scholarshipListView = (ListView) findViewById(R.id.scholarship_list);
        appContext = (AppContext) getApplicationContext();
        new LoadDataDefaultTask(new LoadScholarShipConfigure()).execute();
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
