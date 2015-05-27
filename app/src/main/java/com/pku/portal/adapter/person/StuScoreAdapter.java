package com.pku.portal.adapter.person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pku.portal.R;
import com.pku.portal.model.person.dto.ScoreDTO;

import java.util.List;

/**
 * Created by pktxq on 15-1-23.
 */
public class StuScoreAdapter extends BaseAdapter {
    private final List<ScoreDTO> scoreDTOList;
    private final LayoutInflater listContainer;// 视图容器
    private Context context;

    public StuScoreAdapter(Context context, List<ScoreDTO> scoreDTOList) {
        this.scoreDTOList = scoreDTOList;
        this.context = context;
        this.listContainer = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return scoreDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return scoreDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ScoreDTO scoreDTO = scoreDTOList.get(i);
        view = listContainer.inflate(R.layout.score_item, null);

        TextView courseIdTextView = (TextView) view.findViewById(R.id.course_id);
        TextView courseNameTextView = (TextView) view.findViewById(R.id.course_name);
        TextView termTextView = (TextView) view.findViewById(R.id.term);
        TextView typeTextView = (TextView) view.findViewById(R.id.type);
        TextView scoreTextView = (TextView) view.findViewById(R.id.score);

        courseIdTextView.setText(scoreDTO.getKch());
        courseNameTextView.setText(scoreDTO.getKcmc());
        termTextView.setText(scoreDTO.getXf());
        typeTextView.setText(scoreDTO.getKclb());
        scoreTextView.setText(scoreDTO.getCj());

        return view;
    }
}
