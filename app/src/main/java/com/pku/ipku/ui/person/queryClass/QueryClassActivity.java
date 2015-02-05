package com.pku.ipku.ui.person.queryClass;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pku.ipku.R;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.studyguide.Lesson;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.ui.studyGuide.ClassDetail;
import com.pku.ipku.ui.util.BaseActivityIncludingFooterNavigation;
import com.pku.ipku.util.DataHandleUtil;
import com.pku.ipku.util.UIHelper;

import java.util.ArrayList;
import java.util.List;

public class QueryClassActivity extends BaseActivityIncludingFooterNavigation {

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

    public QueryClassActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_query_class);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", "课程查询");
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        lessonName = (EditText) findViewById(R.id.lesson_name);
        termTextView = (TextView) findViewById(R.id.team);
        yearTextView = (TextView) findViewById(R.id.year);
        findViewById(R.id.team_selector).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new SingleSelectDialogFragment("请选择学期", teamToSelect, termTextView, term);
                dialogFragment.show(getFragmentManager(), "team");
            }
        });
        findViewById(R.id.year_selector).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new SingleSelectDialogFragment("请选择学年", yearToSelect, yearTextView, year);
                dialogFragment.show(getFragmentManager(), "year");
            }
        });
        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
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
                Intent intent = new Intent(QueryClassActivity.this, ClassDetail.class);
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
