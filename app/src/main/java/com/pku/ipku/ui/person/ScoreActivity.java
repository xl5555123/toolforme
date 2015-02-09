package com.pku.ipku.ui.person;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.person.StuScoreAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.person.dto.ScoreDTO;
import com.pku.ipku.model.person.navigation.RegisterInPersonPage;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.ui.AppContext;
import com.pku.ipku.ui.util.BaseActivityIncludingFooterNavigation;

import java.util.List;

/**
 * Created by XingLiang on 2015/2/5.
 */
public class ScoreActivity extends BaseActivityIncludingFooterNavigation implements RegisterInPersonPage {

    private ListView scoreListView;
    private List<ScoreDTO> scoreDTOList;
    private AppContext appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_score);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", getPageTitle());
        super.onCreate(savedInstanceState);
        scoreListView = (ListView) findViewById(R.id.scoreListView);
        appContext = (AppContext) getApplicationContext();
        new LoadDataDefaultTask(new LoadScoreConfigure()).execute();
    }

    @Override
    public int getPageDrawableId() {
        return R.drawable.user_grade;
    }

    @Override
    public String getPageTitle() {
        return "成绩查询";
    }

    @Override
    public Class attachedClassType() {
        return ScoreActivity.class;
    }

    private class LoadScoreConfigure implements LoadDataConfigure {

        @Override
        public void showData() {
            scoreListView.setAdapter(new StuScoreAdapter(appContext, scoreDTOList));
        }

        @Override
        public boolean getData(boolean cache) {
            scoreDTOList = IpkuServiceFactory.getPersonService(cache).getScores();
            if (scoreDTOList == null) {
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
