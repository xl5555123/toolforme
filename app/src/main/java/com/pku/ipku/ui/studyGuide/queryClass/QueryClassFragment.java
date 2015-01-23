package com.pku.ipku.ui.studyGuide.queryClass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.pku.ipku.R;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.studyguide.Lesson;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.ui.studyGuide.ClassDetail;
import com.pku.ipku.util.DataHandleUtil;
import com.pku.ipku.util.UIHelper;

import java.util.ArrayList;
import java.util.List;

public class QueryClassFragment extends Fragment {

    private String term;
    private String year;

    private TextView termTextView;
    private TextView yearTextView;
    private EditText lessonName;

    private Lesson lesson;

    private List<String> yearToSelect = new ArrayList<String>() {
        {
            add("06-07");
            add("07-08");
            add("08-09");
            add("09-10");
            add("10-11");
            add("11-12");
            add("12-13");
            add("13-14");
            add("14-15");
            add("15-16");
        }
    };

    private List<String> teamToSelect = new ArrayList<String>() {
        {
            add("第一学期");
            add("第二学期");
            add("暑期学校");
        }
    };

    public QueryClassFragment() {
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
        View view = inflater.inflate(R.layout.fragment_query_class, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        lessonName = (EditText) view.findViewById(R.id.lesson_name);
        termTextView = (TextView) view.findViewById(R.id.team);
        yearTextView = (TextView) view.findViewById(R.id.year);
        view.findViewById(R.id.team_selector).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new SingleSelectDialogFragment("请选择学期", teamToSelect, termTextView, term);
                dialogFragment.show(getFragmentManager(), "team");
            }
        });
        view.findViewById(R.id.year_selector).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new SingleSelectDialogFragment("请选择学年", yearToSelect, yearTextView, year);
                dialogFragment.show(getFragmentManager(), "year");
            }
        });
        view.findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable lesson = lessonName.getText();
                if (lesson == null || lesson.length() == 0) {
                    UIHelper.ToastMessage("请输入课程名");
                    return;
                }
                loadLesson();

            }
        });
    }

    private void loadLesson() {
        new LoadDataDefaultTask(new LoadDataConfigure() {
            @Override
            public void showData() {
                String lessonJson = DataHandleUtil.objectToJson(lesson);
                Intent intent = new Intent(getActivity(), ClassDetail.class);
                intent.putExtra("lesson", lessonJson);
                startActivity(intent);
            }

            @Override
            public boolean getData(boolean cache) {
                lesson = IpkuServiceFactory.getStudyGuideService(cache).queryLesson(year, term, lessonName.getText().toString());
                if (lesson != null) {
                    return true;
                }
                return false;
            }

            @Override
            public void showWaiting() {

            }

            @Override
            public void stopWaiting() {

            }
        }).execute();
    }


}
