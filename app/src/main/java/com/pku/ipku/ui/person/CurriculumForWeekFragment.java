package com.pku.ipku.ui.person;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.person.CurriculumAdapter;
import com.pku.ipku.model.person.dto.CurriculumDTO;

import java.util.ArrayList;


public class CurriculumForWeekFragment extends Fragment {

    ListView curriculum_lv;
    ImageView hehe_imv;
    int todayInWeek = 0;
    String weeks[] = {"mon", "tue", "wed", "thu", "fri", "sat", "sun"};
    ArrayList<CurriculumDTO> todayCourses = new ArrayList<CurriculumDTO>();

    public CurriculumForWeekFragment() {
        super();
        Bundle bundle = getArguments();
        if(bundle!=null) {
            todayInWeek = bundle.getInt("day");
            todayCourses = (ArrayList<CurriculumDTO>) bundle.getParcelableArrayList("curriculum").get(0);
        }
    }

    public void setDayInWeek(int index){
        this.todayInWeek = index;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_curriculum_for_week, container, false);
        initView(view);
        return view;
    }
    private void initView(View view) {
        curriculum_lv = (ListView) view.findViewById(R.id.curriculum_lv);
        hehe_imv = (ImageView) view.findViewById(R.id.heh_imv);
        todayCourses = CurriculumListFragment.coursesForWeek.get(todayInWeek);
        if(todayCourses.size()==0){
            curriculum_lv.setVisibility(View.GONE);
            hehe_imv.setVisibility(View.VISIBLE);
        }else {
            curriculum_lv.setAdapter(new CurriculumAdapter(getActivity(), todayCourses));
            curriculum_lv.setVisibility(View.VISIBLE);
            hehe_imv.setVisibility(View.GONE);
        }
    }

}
