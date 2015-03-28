package com.pku.ipku.ui.person;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.person.PersonInfoListAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.person.dto.StuInfoDTO;
import com.pku.ipku.model.person.navigation.RegisterInPersonPage;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.task.Result;
import com.pku.ipku.ui.util.BaseActivityIncludingFooterNavigation;
import com.pku.ipku.util.AppContextHolder;
import com.pku.ipku.util.UIHelper;

/**
 * Created by XingLiang on 2015/2/5.
 */
public class PersonInfoActivity extends BaseActivityIncludingFooterNavigation implements RegisterInPersonPage {

    LoadDataDefaultTask loadDataDefaultTask = new LoadDataDefaultTask(new LoadStuInfoConfigure());
    private StuInfoDTO stuInfo;
    private ListView listView;

    private View progress;

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
        return R.drawable.new_person_information;
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
        progress = findViewById(R.id.progress);
        if (progress != null) {
            progress.setVisibility(View.VISIBLE);
        }
    }

    private class LoadStuInfoConfigure implements LoadDataConfigure {
        @Override
        public void showData() {
            listView.setAdapter(new PersonInfoListAdapter(AppContextHolder.getAppContext(), stuInfo));
            if (progress != null) {
                progress.setVisibility(View.GONE);
            }
        }

        @Override
        public Result getData(boolean cache) throws Exception {
            try {
                int studentId = Integer.decode(AppContextHolder.getAppContext().getCurrentUser().getUsername());
                stuInfo = IpkuServiceFactory.getPersonService(cache).getStuInfo(studentId);
                if (stuInfo == null)
                    return new Result(Result.ACCOUNT_ERROR);
                else {
                    return new Result(Result.NO_ERROR);
                }
            } catch (Exception e) {
                return new Result(Result.ACCOUNT_ERROR);
            }

        }

        @Override
        public void showWaiting() {

        }

        @Override
        public void stopWaiting() {

        }

        @Override
        public void processError(Result result) {
            if (result.isAccountError()) {
                UIHelper.directToLoginPage(PersonInfoActivity.this);
            }
        }
    }
}
