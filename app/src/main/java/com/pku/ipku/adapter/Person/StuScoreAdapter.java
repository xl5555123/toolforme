package com.pku.ipku.adapter.person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pku.ipku.R;
import com.pku.ipku.model.person.dto.ScoreDTO;

import java.util.List;

/**
 * Created by pktxq on 15-1-23.
 */
public class StuScoreAdapter extends BaseAdapter {
    private final List<ScoreDTO> scoreDTOList;
    private final LayoutInflater listContainer;// 视图容器
    private Context context;

    public StuScoreAdapter(Context context,List<ScoreDTO> scoreDTOList) {
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



        TextView departmentView = (TextView)view.findViewById(R.id.department);
        TextView schoolYearView = (TextView) view.findViewById(R.id.schoolYear);
        TextView semesterView = (TextView) view.findViewById(R.id.semester);
        TextView courseIdView = (TextView)view.findViewById(R.id.courseId);
        TextView courseNameView = (TextView)view.findViewById(R.id.courseName);
        TextView creditView = (TextView) view.findViewById(R.id.credit);
        TextView scoreView = (TextView) view.findViewById(R.id.score);
        TextView courseTypeView = (TextView)view.findViewById(R.id.courseType);


        departmentView.setText(scoreDTO.getDepartment());
        schoolYearView.setText(scoreDTO.getSchoolYear());
        semesterView.setText(scoreDTO.getSemester());
        courseIdView.setText(scoreDTO.getCourseId());
        courseNameView.setText(scoreDTO.getCourseName());
        creditView.setText(scoreDTO.getCredit());
        scoreView.setText(scoreDTO.getScore());
        courseTypeView.setText(scoreDTO.getCourseType());

        return view;
    }
}
