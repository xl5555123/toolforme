package com.pku.ipku.ui.studyGuide;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.google.common.collect.Lists;
import com.pku.ipku.R;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.studyguide.Curriculum;
import com.pku.ipku.model.studyguide.Lesson;
import com.pku.ipku.model.studyguide.dto.CurriculumDTO;
import com.pku.ipku.model.studyguide.util.DayClass;
import com.pku.ipku.model.studyguide.util.WeekIndex;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.util.CurriculumView;
import com.pku.ipku.util.DataHandleUtil;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class CurriculumFragment extends Fragment {

    private CurriculumView weekView;
    private CurriculumDTO curriculum;
    private Map<Integer, Lesson> lessons;

    public CurriculumFragment(CurriculumDTO curriculumDTO) {
        // Required empty public constructor
        this.curriculum = curriculumDTO;
        this.lessons = Curriculum.getAllLessons(curriculumDTO);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_curriculum, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        weekView = (CurriculumView) view.findViewById(R.id.weekView);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        weekView.goToDate(calendar);
        weekView.setOnEventClickListener(new CurriculumView.EventClickListener() {
            @Override
            public void onEventClick(WeekViewEvent weekViewEvent, RectF rectF) {

            }
        });
        weekView.setEventLongPressListener(new CurriculumView.EventLongPressListener() {
            @Override
            public void onEventLongPress(WeekViewEvent weekViewEvent, RectF rectF) {

            }
        });
        weekView.setMonthChangeListener(new CurriculumView.MonthChangeListener() {
            @Override
            public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
                goToThisMonday(newYear, newMonth);
                List<WeekViewEvent> events = getEvents(newYear, newMonth);
                return events;
            }
        });
        weekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar calendar) {
                return WeekIndex.getChineseName(calendar.get(Calendar.DAY_OF_WEEK));
            }

            @Override
            public String interpretTime(int i) {
                if (i % 2 == 0) {
                    return String.format("第%d节", i/2 + 1);
                }
                else {
                    return "";
                }
            }
        });
        weekView.setOnEventClickListener(new CurriculumView.EventClickListener() {
            @Override
            public void onEventClick(WeekViewEvent event, RectF eventRect) {
                Integer lessonId = (int)event.getId();
                Lesson lesson = lessons.get(lessonId);
                String lessonJson = DataHandleUtil.objectToJson(lesson);
                Intent intent = new Intent(getActivity(), ClassDetail.class);
                intent.putExtra("lesson", lessonJson);
                startActivity(intent);
            }
        });
    }

    private void goToThisMonday(int newYear, int newMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.MONTH, newMonth - 1);
        calendar.set(Calendar.YEAR, newYear);
        weekView.goToDate(calendar);
    }

    private List<WeekViewEvent> getEvents(int newYear, int newMonth) {
        List<WeekViewEvent> events = Lists.newArrayList();
        Map<DayClass, Lesson> lessonMap = curriculum.getLessonMap();
        int count = 0;
        for (DayClass dayClass : lessonMap.keySet()) {
            WeekViewEvent weekViewEvent = new WeekViewEvent();
            weekViewEvent.setColor(Color.BLUE);
            Calendar startTime = Calendar.getInstance();
            startTime.set(Calendar.DAY_OF_WEEK, dayClass.getDay());
            startTime.set(Calendar.HOUR_OF_DAY, dayClass.getClassIndex() * 2);
            startTime.set(Calendar.MINUTE, 0);
            startTime.set(Calendar.MONTH, newMonth - 1);
            startTime.set(Calendar.YEAR, newYear);
            Calendar endTime = (Calendar) startTime.clone();
            endTime.add(Calendar.HOUR_OF_DAY,  1);
            endTime.add(Calendar.MINUTE, 30);
            endTime.set(Calendar.MONTH, newMonth - 1);
            WeekViewEvent event = new WeekViewEvent(count++, lessonMap.get(dayClass).getName(), startTime, endTime);
            event.setColor(getResources().getColor(R.color.blue));
            event.setId(lessonMap.get(dayClass).getId());
            events.add(event);
        }


        return events;
    }

}
