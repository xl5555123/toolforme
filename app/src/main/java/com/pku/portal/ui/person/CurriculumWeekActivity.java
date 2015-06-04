package com.pku.portal.ui.person;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.pku.portal.R;
import com.pku.portal.model.person.dto.CurriculumDTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by vector liu on 2015/4/20.
 */
public class CurriculumWeekActivity extends Activity implements WeekView.MonthChangeListener,
        WeekView.EventClickListener, WeekView.EventLongPressListener {
    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private WeekView mWeekView;
    String colors[] = {"#59dbe0","#f57f68","#87d288","#f8b552"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum_week);
        getActionBar().setTitle("课程表");

        // Get a reference for the week view in the layout.
        mWeekView = (WeekView) findViewById(R.id.weekView);

        // Show a toast message about the touched event.
        mWeekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);



//        mWeekViewType = TYPE_WEEK_VIEW;
//        mWeekView.setNumberOfVisibleDays(7);
//
//        // Lets change some dimensions to best fit the view.
//        mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
//        mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
//        mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_curriculum_week, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_today:
                mWeekView.goToToday();
                return true;
            case R.id.action_day_view:
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(1);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_three_day_view:
                if (mWeekViewType != TYPE_THREE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_THREE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(3);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_week_view:
                if (mWeekViewType != TYPE_WEEK_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_WEEK_VIEW;
                    mWeekView.setNumberOfVisibleDays(7);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        // Populate the week view with some events.
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        Calendar startTime;
        Calendar endTime;
        WeekViewEvent event;

        List<CurriculumDTO> curriculums;
        CurriculumDTO curriculum;
        int weeknum = CurriculumListFragment.coursesForWeek.size();
        for(int i = 0; i < weeknum; i++){
            curriculums = CurriculumListFragment.coursesForWeek.get(i);
            int len = curriculums.size();
            for(int j =0 ;j < len; j++){
                curriculum = curriculums.get(j);
                curriculum.getTime();
                String course_name = curriculum.getCourseName();
                int start_hour = curriculum.start_hour;
                int start_min = curriculum.start_min;
                int end_hour = curriculum.end_hour;
                int end_min = curriculum.end_min;
                //Log.v("liuyi", course_name + " " + curriculum.getDetailTime() + " " + start_hour + " " + start_min + " " + end_hour + " " + end_min);
                startTime = Calendar.getInstance();
                startTime.set(Calendar.DAY_OF_WEEK, (i+2)%7);
                startTime.set(Calendar.HOUR_OF_DAY, start_hour);
                startTime.set(Calendar.MINUTE, start_min);
                startTime.set(Calendar.MONTH, newMonth-1);
                startTime.set(Calendar.YEAR, newYear);
                endTime = (Calendar) startTime.clone();
                endTime.set(Calendar.HOUR_OF_DAY, end_hour);
                endTime.set(Calendar.MINUTE, end_min);
                endTime.set(Calendar.MONTH, newMonth-1);
                event = new WeekViewEvent(i*10 + j, course_name, startTime, endTime);
                event.setColor(Color.parseColor(colors[(i*10+j)%4]));
                events.add(event);
            }
        }
        return events;
    }



    private String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(CurriculumWeekActivity.this, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(CurriculumWeekActivity.this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();
    }
}
