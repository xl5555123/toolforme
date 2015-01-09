package com.pku.ipku.ui.studyGuide;

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

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class CurriculumFragment extends Fragment {

    private WeekView weekView;
    private CurriculumDTO curriculum;

    public CurriculumFragment(CurriculumDTO curriculumDTO) {
        // Required empty public constructor
        this.curriculum = curriculumDTO;
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
        weekView = (WeekView) view.findViewById(R.id.weekView);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        weekView.goToDate(calendar);
        weekView.setOnEventClickListener(new WeekView.EventClickListener() {
            @Override
            public void onEventClick(WeekViewEvent weekViewEvent, RectF rectF) {

            }
        });
        weekView.setEventLongPressListener(new WeekView.EventLongPressListener() {
            @Override
            public void onEventLongPress(WeekViewEvent weekViewEvent, RectF rectF) {

            }
        });
        weekView.setMonthChangeListener(new WeekView.MonthChangeListener() {
            @Override
            public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
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
    }

    private String getLessonTitle(Lesson lesson) {
        return String.format("%s\n%s\n%s\n%s", lesson.getName(), lesson.getTeacherName(), lesson.getLocation(), lesson.getInformation());
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
            WeekViewEvent event = new WeekViewEvent(count++, getLessonTitle(lessonMap.get(dayClass)), startTime, endTime);
            event.setColor(getResources().getColor(R.color.blue));
            events.add(event);
        }

        return events;
    }

}
