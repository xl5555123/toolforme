package com.pku.ipku.adapter.person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pku.ipku.R;
import com.pku.ipku.model.studyguide.Lesson;

import java.util.List;

/**
 * Created by xingliang on 15-3-1.
 */
public class QueryClassResultListAdapter extends BaseAdapter {

    private List<Lesson> lessonList;

    private LayoutInflater layoutInflater;

    public QueryClassResultListAdapter(Context context, List<Lesson> lessonList) {
        this.lessonList = lessonList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lessonList.size();
    }

    @Override
    public Object getItem(int position) {
        return lessonList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.class_list_item, null);
        TextView englishName = (TextView) convertView.findViewById(R.id.enlish_name);
        TextView courseName = (TextView) convertView.findViewById(R.id.course_name);
        Lesson lesson = lessonList.get(position);
        courseName.setText(lesson.getCourseName());
        englishName.setText(lesson.getCourseEngName());
        TextView courseId = (TextView) convertView.findViewById(R.id.course_id);
        courseId.setText(lesson.getCourseId());
        convertView.setTag(lesson);
        return convertView;
    }
}
