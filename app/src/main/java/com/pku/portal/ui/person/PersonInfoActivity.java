package com.pku.portal.ui.person;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.pku.portal.R;
import com.pku.portal.adapter.person.PersonInfoListAdapter;
import com.pku.portal.api.factory.IpkuServiceFactory;
import com.pku.portal.model.account.User;
import com.pku.portal.model.person.dto.StuInfoDTO;
import com.pku.portal.model.navigation.RegisterInPersonPage;
import com.pku.portal.util.task.LoadDataConfigure;
import com.pku.portal.util.task.LoadDataDefaultTask;
import com.pku.portal.util.task.Result;
import com.pku.portal.ui.util.BaseActivityIncludingFooterNavigation;
import com.pku.portal.util.AppContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XingLiang on 2015/2/5.
 */
public class PersonInfoActivity extends BaseActivityIncludingFooterNavigation implements RegisterInPersonPage {

    LoadDataDefaultTask loadDataDefaultTask = new LoadDataDefaultTask(new LoadStuInfoConfigure());
    private StuInfoDTO stuInfo;
    private ListView listView;

    private List<String> headerList = new ArrayList<String>() {
        {
            add("姓名");
            add("性别");
            add("学号");
            add("所在院系");
            add("身份");
            add("专业");
            add("研究方向");
            add("导师");
            add("学生类别");
            add("出生日期");
            add("民族");
            add("入学年份");
            add("籍贯");
            add("政治面貌");
            add("名字简写");
        }
    };

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
            listView.setAdapter(new PersonInfoListAdapter(AppContextHolder.getAppContext(), stuInfo, headerList));
            if (progress != null) {
                progress.setVisibility(View.GONE);
            }
        }

        @Override
        public Result getData(boolean cache) throws Exception {
            try {
                User user = AppContextHolder.getAppContext().getCurrentUser();
                stuInfo = IpkuServiceFactory.getPersonService(cache).getStuInfo(user.getUsername());
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
        }
    }
}
