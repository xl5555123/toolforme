package com.xingliang.toolforme.ui.person;


import android.os.Bundle;
import android.widget.ListView;

import com.xingliang.toolforme.R;
import com.xingliang.toolforme.adapter.person.StuScoreAdapter;
import com.xingliang.toolforme.api.factory.IpkuServiceFactory;
import com.xingliang.toolforme.model.account.User;
import com.xingliang.toolforme.model.person.dto.ScoreDTO;
import com.xingliang.toolforme.model.navigation.RegisterInPersonPage;
import com.xingliang.toolforme.util.task.LoadDataConfigure;
import com.xingliang.toolforme.util.task.LoadDataDefaultTask;
import com.xingliang.toolforme.util.task.Result;
import com.xingliang.toolforme.ui.AppContext;
import com.xingliang.toolforme.ui.util.BaseActivityIncludingFooterNavigation;
import com.xingliang.toolforme.util.AppContextHolder;

import java.util.List;

/**
 * Created by XingLiang on 2015/2/5.
 */
public class ScoreActivity extends BaseActivityIncludingFooterNavigation implements RegisterInPersonPage {

    private List<ScoreDTO> scoreDTOList;
    private AppContext appContext;
    private ListView scoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_score);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", getPageTitle());
        super.onCreate(savedInstanceState);
        scoreList = (ListView) findViewById(R.id.score_list);
        appContext = (AppContext) getApplicationContext();
        new LoadDataDefaultTask(new LoadScoreConfigure()).execute();
    }

    @Override
    public int getPageDrawableId() {
        return R.drawable.new_grades;
    }

    @Override
    public String getPageTitle() {
        return "成绩查询";
    }

    @Override
    public int getPageBackgroundId() {
        return R.drawable.navigation_border_green;
    }

    @Override
    public Class attachedClassType() {
        return ScoreActivity.class;
    }

    private class LoadScoreConfigure implements LoadDataConfigure {

        @Override
        public void showData() {
            scoreList.setAdapter(new StuScoreAdapter(appContext, scoreDTOList));
        }

        @Override
        public Result getData(boolean cache) {
            User currentUser = AppContextHolder.getAppContext().getCurrentUser();
            if (currentUser != null) {
                scoreDTOList = IpkuServiceFactory.getPersonService(cache).getScores(currentUser.getUsername());
            }
            if (scoreDTOList == null) {
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
