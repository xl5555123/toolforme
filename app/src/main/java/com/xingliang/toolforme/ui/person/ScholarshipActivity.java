package com.xingliang.toolforme.ui.person;

import android.os.Bundle;
import android.widget.ListView;

import com.xingliang.toolforme.R;
import com.xingliang.toolforme.adapter.person.ScholarshipAdapter;
import com.xingliang.toolforme.api.factory.IpkuServiceFactory;
import com.xingliang.toolforme.model.person.dto.ScholarShipDTO;
import com.xingliang.toolforme.model.navigation.RegisterInPersonPage;
import com.xingliang.toolforme.util.task.LoadDataConfigure;
import com.xingliang.toolforme.util.task.LoadDataDefaultTask;
import com.xingliang.toolforme.util.task.Result;
import com.xingliang.toolforme.ui.util.BaseActivityIncludingFooterNavigation;
import com.xingliang.toolforme.util.AppContextHolder;

import java.util.List;

/**
 * Created by XingLiang on 2015/2/5.
 */
public class ScholarshipActivity extends BaseActivityIncludingFooterNavigation implements RegisterInPersonPage {

    private List<ScholarShipDTO> scholarshipList;
    private ListView scholarshipListView;

    private String currentYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_scholarship);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", getPageTitle());
        super.onCreate(savedInstanceState);
        scholarshipListView = (ListView) findViewById(R.id.scholarship_list);
        new LoadDataDefaultTask(new LoadScholarShipConfigure()).execute();
    }

    @Override
    public int getPageDrawableId() {
        return R.drawable.sholarship;
    }

    @Override
    public String getPageTitle() {
        return "奖学金信息";
    }

    @Override
    public int getPageBackgroundId() {
        return R.drawable.navigation_border_yellow;
    }

    @Override
    public Class attachedClassType() {
        return ScholarshipActivity.class;
    }

    private class LoadScholarShipConfigure implements LoadDataConfigure {

        @Override
        public void showData() {
            scholarshipListView.setAdapter(new ScholarshipAdapter(AppContextHolder.getAppContext(), scholarshipList));
        }

        @Override
        public Result getData(boolean cache) {
            scholarshipList = IpkuServiceFactory.getPersonService(cache).getScholarShips();
            if (scholarshipList == null) {
                return new Result(Result.NET_ERROR);
            }
            return new Result(Result.NO_ERROR);
        }

        @Override
        public void showWaiting() {

        }

        @Override
        public void stopWaiting() {

        }

        @Override
        public void processError(Result result) {

        }
    }
}
