package com.pku.ipku.ui.person;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.person.StuScoreAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.person.dto.ScoreDTO;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.ui.AppContext;

import java.util.List;

public class ScoreFragment extends Fragment {

    private ListView scoreListView;
    private List<ScoreDTO> scoreDTOList;
    private AppContext appContext;

    public ScoreFragment() {
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
        View view = inflater.inflate(R.layout.activity_score, container, false);
        scoreListView = (ListView) view.findViewById(R.id.scoreListView);
        appContext = (AppContext)getActivity().getApplicationContext();
        new LoadDataDefaultTask(new LoadScoreConfigure()).execute();

        return view;
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
