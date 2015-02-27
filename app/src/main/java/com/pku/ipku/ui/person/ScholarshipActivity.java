package com.pku.ipku.ui.person;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.pku.ipku.R;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.person.dto.ScholarShipDTO;
import com.pku.ipku.model.person.navigation.RegisterInPersonPage;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.ui.AppContext;
import com.pku.ipku.ui.person.queryClass.SelectYearFragment;
import com.pku.ipku.ui.util.BaseActivityIncludingFooterNavigation;

import java.util.List;

/**
 * Created by XingLiang on 2015/2/5.
 */
public class ScholarshipActivity extends BaseActivityIncludingFooterNavigation implements RegisterInPersonPage, SelectYearFragment.OnFinishSelectListener {

    private List<ScholarShipDTO> scholarshipList;
    private ListView scholarshipListView;
    private AppContext appContext;

    private String currentYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_scholarship);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", getPageTitle());
        super.onCreate(savedInstanceState);
        //scholarshipListView = (ListView) findViewById(R.id.scholarship_list);
        appContext = (AppContext) getApplicationContext();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.year_frame, SelectYearFragment.newInstance(-1)).commit();
        findViewById(R.id.pull).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.year_frame).setVisibility(View.VISIBLE);
            }
        });
        currentYear = "全部";
        new LoadDataDefaultTask(new LoadScholarShipConfigure()).execute();
    }

    @Override
    public int getPageDrawableId() {
        return R.drawable.user_scholar;
    }

    @Override
    public String getPageTitle() {
        return "奖学金信息";
    }

    @Override
    public Class attachedClassType() {
        return ScholarshipActivity.class;
    }

    @Override
    public void onFinishSelect(String year) {
        TextView selectText = (TextView) findViewById(R.id.select_text);
        if (selectText != null) {
            selectText.setText(year);
        }
    }

    private class LoadScholarShipConfigure implements LoadDataConfigure {

        @Override
        public void showData() {
            //scholarshipListView.setAdapter(new ScholarshipAdapter(appContext, scholarshipList));
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
