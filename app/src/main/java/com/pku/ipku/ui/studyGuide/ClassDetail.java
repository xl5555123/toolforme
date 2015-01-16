package com.pku.ipku.ui.studyGuide;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.pku.ipku.R;
import com.pku.ipku.model.studyguide.Lesson;
import com.pku.ipku.util.DataHandleUtil;

public class ClassDetail extends ActionBarActivity {

    private Lesson lesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail);
        String lessonJson = getIntent().getStringExtra("lesson");
        lesson = DataHandleUtil.StringToObject(Lesson.class, lessonJson);
        initView();
    }

    private void initView() {
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        TextView className = (TextView) findViewById(R.id.class_name);
        TextView classroom = (TextView) findViewById(R.id.classroom);
        TextView classSector = (TextView) findViewById(R.id.class_sector);
        TextView classTeacher = (TextView) findViewById(R.id.class_teacher);
        TextView classTime = (TextView) findViewById(R.id.class_time);
        className.setText(lesson.getName());
        classroom.setText(lesson.getLocation());
        classSector.setText(lesson.getSector());
        classTeacher.setText(lesson.getTeacherName());
        classTime.setText(lesson.getTime());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
