package com.pku.ipku.ui.person;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.person.PersonInfoListAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.person.dto.StuInfoDTO;
import com.pku.ipku.model.person.navigation.RegisterInPersonPage;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.ui.util.BaseActivityIncludingFooterNavigation;
import com.pku.ipku.util.AppContextHolder;

/**
 * Created by XingLiang on 2015/2/5.
 */
public class PersonInfoActivity extends BaseActivityIncludingFooterNavigation implements RegisterInPersonPage {

    LoadDataDefaultTask loadDataDefaultTask = new LoadDataDefaultTask(new LoadStuInfoConfigure());
    private StuInfoDTO stuInfo;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_person_info);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", getPageTitle());
        super.onCreate(savedInstanceState);
        initView();
        loadDataDefaultTask.execute();
    }

    @Override
    public int getPageDrawableId() {
        return R.drawable.id;
    }

    @Override
    public String getPageTitle() {
        return "个人信息";
    }

    @Override
    public int getPageBackgroundId() {
        return R.drawable.navigation_border_blue;
    }

    @Override
    public Class attachedClassType() {
        return PersonInfoActivity.class;
    }

    public void initView() {
        listView = (ListView) findViewById(R.id.person_info_list);

    }

    private class LoadStuInfoConfigure implements LoadDataConfigure {
        @Override
        public void showData() {
            listView.setAdapter(new PersonInfoListAdapter(AppContextHolder.getAppContext(), stuInfo));
        }

        @Override
        public boolean getData(boolean cache) {
            stuInfo = IpkuServiceFactory.getPersonService(cache).getStuInfo();
            if (stuInfo == null)
                return false;
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
