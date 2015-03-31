package com.pku.ipku.ui.person;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.pku.ipku.R;
import com.pku.ipku.adapter.person.CurriculumAdapter;
import com.pku.ipku.adapter.person.CurriculumAdapterForHome;
import com.pku.ipku.api.PersonService;
import com.pku.ipku.api.util.NetHelper;
import com.pku.ipku.model.person.dto.CurriculumDTO;
import com.pku.ipku.util.AppContextHolder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CurriculumListFragment extends Fragment {

    ListView curriculum_lv;
    RelativeLayout no_class_rl;

    JSONObject courseInfo = new JSONObject();
    public static JSONArray courses = new JSONArray();
    public static CurriculumListFragment fragment;
    RelativeLayout curriculum_container;
    int todayInWeek = 0;
    RequestQueue mQueue;
    String weeks[] = {"mon", "tue", "wed", "thu", "fri", "sat", "sun"};
    public static List<ArrayList<CurriculumDTO>> coursesForWeek = new ArrayList<ArrayList<CurriculumDTO>>();
    public CurriculumListFragment() {
        // Required empty public constructor
        for(int i = 0; i < 7; i++){
            coursesForWeek.add(new ArrayList<CurriculumDTO>());
        }
    }

    public static CurriculumListFragment newInstance() {
        if(fragment == null)
            fragment = new CurriculumListFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_curriculum_list, container, false);
        initView(view);
        mQueue = Volley.newRequestQueue((Context) getActivity());
        getData();
        return view;
    }

    private void initView(View view) {
        curriculum_lv = (ListView) view.findViewById(R.id.curriculum_lv);
        no_class_rl = (RelativeLayout) view.findViewById(R.id.no_class_rl);
        curriculum_container = (RelativeLayout) view.findViewById(R.id.curriculum_container);
        curriculum_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CurriculumActivity.class);
                intent.putExtra("todayInWeek", todayInWeek);
                startActivity(intent);
            }
        });
        no_class_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CurriculumActivity.class);
                intent.putExtra("todayInWeek", todayInWeek);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        todayInWeek = getTodayInWeek();
        if(coursesForWeek!=null && coursesForWeek.size()!=0)
            curriculum_lv.setAdapter(new CurriculumAdapter(getActivity(), coursesForWeek.get(todayInWeek)));
        if(courses==null || courses.length()==0)
            getDataByVolley();
    }

    private int getTodayInWeek()
    {
        Calendar date = Calendar.getInstance(Locale.CHINA);
        return (date.get(Calendar.DAY_OF_WEEK) + 5) % 7;
    }

    @Override
    public void setInitialSavedState(SavedState state) {
        super.setInitialSavedState(state);
    }

    @Override
    public void setRetainInstance(boolean retain) {
        super.setRetainInstance(retain);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void getDataByVolley()
    {
        int user_id = Integer.decode(AppContextHolder.getAppContext().getCurrentUser().getUsername());
        int timestamp = (int) new Date().getTime();
        String msg = NetHelper.getMd5(NetHelper.concatParameter(user_id, timestamp));
        String url = NetHelper.getAuthUrl(PersonService.PERSON_COURSE_TABLE_URL, user_id, timestamp, msg);
        coursesForWeek = new ArrayList<ArrayList<CurriculumDTO>>();
        for(int i = 0; i < 7; i++){
            coursesForWeek.add(new ArrayList<CurriculumDTO>());
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            courses = response.getJSONArray("course");
                        }catch(Exception e){

                        }
                        for(int i = 0; i < courses.length(); i++){
                            try {
                                JSONObject course = courses.getJSONObject(i);
                                String timeNum = course.getString("timeNum");
                                for(int j = 0; j < 7; j++){
                                    String week = weeks[j];
                                    if(course.has(week)){
                                        JSONObject tmp = course.getJSONObject(week);
                                        String courseName = tmp.getString("courseName");
                                        String courseNameCopy = courseName;
                                        if(courseName==null || courseName.equals(""))
                                            continue;
                                        String sty = tmp.getString("sty");
                                        String parity = tmp.getString("parity");
                                        int left = courseNameCopy.indexOf("<br>");
                                        int right = courseNameCopy.lastIndexOf("<br>");
                                        String extra = courseNameCopy.substring(courseName.length() - 2);
                                        String roomName = "";
                                        if(left > 0) {
                                            roomName = courseNameCopy.substring(left + 5, right - 1);
                                            courseName = courseNameCopy.substring(0,left);
                                        }
                                        CurriculumDTO tmp_cm = new CurriculumDTO(week, timeNum, courseName, roomName, extra,1);
                                        CurriculumDTO last3 = coursesForWeek.get(j).size()>0 ? coursesForWeek.get(j).get(coursesForWeek.get(j).size() - 1) : null;
                                        if(last3!=null && tmp_cm.getCourseName().equals(last3.getCourseName()))
                                        {
                                            coursesForWeek.get(j).get(coursesForWeek.get(j).size() - 1).addClassCount();
                                        }else
                                            coursesForWeek.get(j).add(tmp_cm);
                                    }
                                }
                            }catch (Exception e){

                            }
                        }

                        //curriculum_lv.setAdapter(new CurriculumAdapter(getActivity(),  todayCourses));
                        if(coursesForWeek.get(todayInWeek).size()==0){
                            curriculum_lv.setVisibility(View.GONE);
                            no_class_rl.setVisibility(View.VISIBLE);
                        }else {
                            if(coursesForWeek!=null && coursesForWeek.size()!=0)
                                curriculum_lv.setAdapter(new CurriculumAdapterForHome(getActivity(), coursesForWeek.get(todayInWeek)));
                            curriculum_lv.setVisibility(View.VISIBLE);
                            no_class_rl.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        mQueue.add(jsonObjectRequest);
    }


}
