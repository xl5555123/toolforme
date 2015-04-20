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
    public static JSONArray courses = new JSONArray();
    public static CurriculumListFragment fragment;
    RelativeLayout curriculum_container;
    int todayInWeek = 0;
    RequestQueue mQueue;
    public static String courseTable = "<!DOCTYPE html>\n" +
            "<html lang=\"zh-CN\">\n" +
            "  <head>\n" +
            "    <meta charset=\"utf-8\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=0.5\">\n" +
            "    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->\n" +
            "    <title>课程表\n</title>" +
            "<style>" +
            ".course-header, .course-footer {\n" +
            "  background-color: #CCDDEE;\n" +
            "  color: #555588;\n" +
            "  font-size: 14px;\n" +
            "  text-align: center;\n" +
            "  vertical-align: baseline;\n" +
            "  line-height: 18px;\n" +
            "  border-color: #999999;\n" +
            "  border-style: solid;\n" +
            "  border-width: 1px;\n" +
            "  padding-left: 10px;}" +
            "table {\n" +
            "  display: table;\n" +
            "  border-collapse: separate;\n" +
            "  border-spacing: 2px;\n" +
            "  border-color: gray;\n" +
            "}" +
            ".course {\n" +
            "  border: 1px solid #E2E2E2;\n" +
            "  border-collapse: collapse;\n" +
            "  padding-left: 5px;\n" +
            "}" +
            "</style>" +
            "  </head>\n" +
            "  <body>";

    String weeks[] = {"mon", "tue", "wed", "thu", "fri", "sat", "sun"};
    public static List<ArrayList<CurriculumDTO>> coursesForWeek = new ArrayList<ArrayList<CurriculumDTO>>();

    public CurriculumListFragment() {
        // Required empty public constructor
        for (int i = 0; i < 7; i++) {
            coursesForWeek.add(new ArrayList<CurriculumDTO>());
        }
    }

    public static CurriculumListFragment newInstance() {
        if(fragment==null)
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
                Intent intent = new Intent(getActivity(), CurriculumWeekActivity.class);
                intent.putExtra("todayInWeek", todayInWeek);
                startActivity(intent);
            }
        });
        no_class_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getActivity(), CurriculumAsWebActivity.class);
                Intent intent = new Intent(getActivity(), CurriculumWeekActivity.class);
                intent.putExtra("todayInWeek", todayInWeek);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        todayInWeek = getTodayInWeek();
//        if (coursesForWeek != null && coursesForWeek.size() != 0)
//            curriculum_lv.setAdapter(new CurriculumAdapter(getActivity(), coursesForWeek.get(todayInWeek)));
//        if (courses == null || courses.length() == 0)
         getDataByVolley();
    }

    private int getTodayInWeek() {
        Calendar date = Calendar.getInstance(Locale.CHINA);
        return (date.get(Calendar.DAY_OF_WEEK) + 5) % 7;
    }



    private void getDataByVolley() {
        long user_id = Integer.decode(AppContextHolder.getAppContext().getCurrentUser().getUsername());
        long timestamp = new Date().getTime();
        String msg = NetHelper.getMd5(NetHelper.concatParameter(user_id, timestamp));
        String url = NetHelper.getAuthUrl(PersonService.PERSON_COURSE_TABLE_URL, user_id, timestamp, msg);
        coursesForWeek = new ArrayList<ArrayList<CurriculumDTO>>();
        for (int i = 0; i < 7; i++) {
            coursesForWeek.add(new ArrayList<CurriculumDTO>());
        }
        Log.v("liuyi",url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            courses = response.getJSONArray("course");
                        } catch (Exception e) {

                        }
                        courseTable = courseTable +
                                "<table border=\"0\" cellspacing=\"3px\" align=\"center\" width=\"1000\">" +
                                "<tr class=\"course-header\">\n" +
                                "\t\t\t\t<th class=\"course\">节数</th>\n" +
                                "\n" +
                                "\n" +
                                "\t\t\t\t<th class=\"course\">星期一</th>\n" +
                                "\n" +
                                "\n" +
                                "\t\t\t\t<th class=\"course\">星期二</th>\n" +
                                "\n" +
                                "\n" +
                                "\t\t\t\t<th class=\"course\">星期三</th>\n" +
                                "\n" +
                                "\n" +
                                "\t\t\t\t<th class=\"course\">星期四</th>\n" +
                                "\n" +
                                "\n" +
                                "\t\t\t\t<th class=\"course\">星期五</th>\n" +
                                "\n" +
                                "\n" +
                                "\t\t\t\t<th class=\"course\">星期六</th>\n" +
                                "\n" +
                                "\n" +
                                "\t\t\t\t<th class=\"course\">星期日</th>\n" +
                                "\n" +
                                "\n" +
                                "\t\t\t\n" +
                                "</tr>";
                        for (int i = 0; i < courses.length(); i++) {
                            courseTable = courseTable +
                                    "<tr>" +
                                    "<td class=\"course\" align=\"center\"><span>";
                            try {
                                JSONObject course = courses.getJSONObject(i);
                                String timeNum = course.getString("timeNum");
                                courseTable = courseTable +
                                        timeNum + "</span></td>";
                                for (int m = 0; m < weeks.length; m++) {
                                    String stmp = course.getJSONObject(weeks[m]).getString("courseName");
                                    String style = course.getJSONObject(weeks[m]).getString("sty");
                                    courseTable = courseTable +
                                            "<td class=\"course\" align=\"center\" style=\"" + style +

                                            "\" ><span>" + stmp + "</span></td>";
                                }
                                courseTable = courseTable +
                                        "</tr>";
                                for (int j = 0; j < 7; j++) {
                                    String week = weeks[j];
                                    if (course.has(week)) {
                                        JSONObject tmp = course.getJSONObject(week);
                                        String courseName = tmp.getString("courseName");
                                        String courseNameCopy = courseName;
                                        if (courseName == null || courseName.equals(""))
                                            continue;
                                        String sty = tmp.getString("sty");
                                        String parity = tmp.getString("parity");
                                        int left = courseNameCopy.indexOf("<br>");
                                        int right = courseNameCopy.lastIndexOf("<br>");
                                        String extra = courseNameCopy.substring(courseName.length() - 2);
                                        String roomName = "";
                                        if (left > 0) {
                                            roomName = courseNameCopy.substring(left + 5, right - 1);
                                            courseName = courseNameCopy.substring(0, left);
                                        }
                                        CurriculumDTO tmp_cm = new CurriculumDTO(week, timeNum, courseName, roomName, extra, 1);
                                        CurriculumDTO last3 = coursesForWeek.get(j).size() > 0 ? coursesForWeek.get(j).get(coursesForWeek.get(j).size() - 1) : null;
                                        if (last3 != null && tmp_cm.getCourseName().equals(last3.getCourseName())) {
                                            coursesForWeek.get(j).get(coursesForWeek.get(j).size() - 1).addClassCount();
                                        } else
                                            coursesForWeek.get(j).add(tmp_cm);
                                    }
                                }
                            } catch (Exception e) {

                            }

                        }

                        courseTable = courseTable + "</table></body></html>";
                        //curriculum_lv.setAdapter(new CurriculumAdapter(getActivity(),  todayCourses));
                        if (coursesForWeek.get(todayInWeek).size() == 0) {
                            curriculum_lv.setVisibility(View.GONE);
                            no_class_rl.setVisibility(View.VISIBLE);
                        } else {
                            if (coursesForWeek != null && coursesForWeek.size() != 0)
                                curriculum_lv.setAdapter(new CurriculumAdapterForHome(getActivity(), coursesForWeek.get(todayInWeek)));
                            curriculum_lv.setVisibility(View.VISIBLE);
                            no_class_rl.setVisibility(View.GONE);
                        }
                        Log.v("liuyi", coursesForWeek.toString());
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


/*
{"flag":"success","course":[{"fri":{"courseName":"","parity":"","sty":""},"mon":{"courseName":"","parity":"","sty":""},"sat":{"courseName":"","parity":"","sty":""},"sun":{"courseName":"","parity":"","sty":""},"thu":{"courseName":"","parity":"","sty":""},"timeNum":"第一节","tue":{"courseName":"","parity":"","sty":""},"wed":{"courseName":"","parity":"","sty":""}},{"fri":{"courseName":"","parity":"","sty":""},"mon":{"courseName":"","parity":"","sty":""},"sat":{"courseName":"","parity":"","sty":""},"sun":{"courseName":"","parity":"","sty":""},"thu":{"courseName":"","parity":"","sty":""},"timeNum":"第二节","tue":{"courseName":"","parity":"","sty":""},"wed":{"courseName":"","parity":"","sty":""}},{"fri":{"courseName":"","parity":"","sty":""},"mon":{"courseName":"","parity":"","sty":""},"sat":{"courseName":"","parity":"","sty":""},"sun":{"courseName":"","parity":"","sty":""},"thu":{"courseName":"","parity":"","sty":""},"timeNum":"第三节","tue":{"courseName":"","parity":"","sty":""},"wed":{"courseName":"","parity":"","sty":""}},{"fri":{"courseName":"","parity":"","sty":""},"mon":{"courseName":"","parity":"","sty":""},"sat":{"courseName":"","parity":"","sty":""},"sun":{"courseName":"","parity":"","sty":""},"thu":{"courseName":"","parity":"","sty":""},"timeNum":"第四节","tue":{"courseName":"","parity":"","sty":""},"wed":{"courseName":"","parity":"","sty":""}},{"fri":{"courseName":"中国特色社会主义理论与实践研究<br>(理教108)<br>(备注：) 单周","parity":"","sty":"background-color: lightgoldenrodyellow"},"mon":{"courseName":"","parity":"","sty":""},"sat":{"courseName":"","parity":"","sty":""},"sun":{"courseName":"","parity":"","sty":""},"thu":{"courseName":"","parity":"","sty":""},"timeNum":"第五节","tue":{"courseName":"","parity":"","sty":""},"wed":{"courseName":"","parity":"","sty":""}},{"fri":{"courseName":"中国特色社会主义理论与实践研究<br>(理教108)<br>(备注：) 单周","parity":"","sty":"background-color: lightgoldenrodyellow"},"mon":{"courseName":"","parity":"","sty":""},"sat":{"courseName":"","parity":"","sty":""},"sun":{"courseName":"","parity":"","sty":""},"thu":{"courseName":"","parity":"","sty":""},"timeNum":"第六节","tue":{"courseName":"","parity":"","sty":""},"wed":{"courseName":"","parity":"","sty":""}},{"fri":{"courseName":"中国特色社会主义理论与实践研究<br>(理教108)<br>(备注：) 单周","parity":"","sty":"background-color: lightgoldenrodyellow"},"mon":{"courseName":"应用密码学<br>(文史113)<br>(备注：学术型研究生选修，本部上课。) 每周","parity":"","sty":"background-color: lightpink"},"sat":{"courseName":"国学概论<br>(二教412)<br>(备注：工学博士及工学硕士必修！本部上课) 每周","parity":"","sty":"background-color: lightblue"},"sun":{"courseName":"国学概论<br>(二教412)<br>(备注：工学博士及工学硕士必修！本部上课) 每周","parity":"","sty":"background-color: lightblue"},"thu":{"courseName":"软件工程前沿专题<br>(二教106)<br>(备注：工学博士及工学硕士必修！本部上课) 每周","parity":"","sty":"background-color: aquamarine"},"timeNum":"第七节","tue":{"courseName":"云计算技术及应用<br>(文史115)<br>(备注：学术型研究生选修，本部上课。) 每周","parity":"","sty":"background-color: lightgreen"},"wed":{"courseName":"操作系统与虚拟化安全<br>(文史204)<br>(备注：学术型研究生选修，本部上课。) 每周","parity":"","sty":"background-color: lightgrey"}},{"fri":{"courseName":"中国特色社会主义理论与实践研究<br>(理教108)<br>(备注：) 单周","parity":"","sty":"background-color: lightgoldenrodyellow"},"mon":{"courseName":"应用密码学<br>(文史113)<br>(备注：学术型研究生选修，本部上课。) 每周","parity":"","sty":"background-color: lightpink"},"sat":{"courseName":"国学概论<br>(二教412)<br>(备注：工学博士及工学硕士必修！本部上课) 每周","parity":"","sty":"background-color: lightblue"},"sun":{"courseName":"国学概论<br>(二教412)<br>(备注：工学博士及工学硕士必修！本部上课) 每周","parity":"","sty":"background-color: lightblue"},"thu":{"courseName":"软件工程前沿专题<br>(二教106)<br>(备注：工学博士及工学硕士必修！本部上课) 每周","parity":"","sty":"background-color: aquamarine"},"timeNum":"第八节","tue":{"courseName":"云计算技术及应用<br>(文史115)<br>(备注：学术型研究生选修，本部上课。) 每周","parity":"","sty":"background-color: lightgreen"},"wed":{"courseName":"操作系统与虚拟化安全<br>(文史204)<br>(备注：学术型研究生选修，本部上课。) 每周","parity":"","sty":"background-color: lightgrey"}},{"fri":{"courseName":"","parity":"","sty":""},"mon":{"courseName":"应用密码学<br>(文史113)<br>(备注：学术型研究生选修，本部上课。) 每周","parity":"","sty":"background-color: lightpink"},"sat":{"courseName":"国学概论<br>(二教412)<br>(备注：工学博士及工学硕士必修！本部上课) 每周","parity":"","sty":"background-color: lightblue"},"sun":{"courseName":"国学概论<br>(二教412)<br>(备注：工学博士及工学硕士必修！本部上课) 每周","parity":"","sty":"background-color: lightblue"},"thu":{"courseName":"软件工程前沿专题<br>(二教106)<br>(备注：工学博士及工学硕士必修！本部上课) 每周","parity":"","sty":"background-color: aquamarine"},"timeNum":"第九节","tue":{"courseName":"云计算技术及应用<br>(文史115)<br>(备注：学术型研究生选修，本部上课。) 每周","parity":"","sty":"background-color: lightgreen"},"wed":{"courseName":"操作系统与虚拟化安全<br>(文史204)<br>(备注：学术型研究生选修，本部上课。) 每周","parity":"","sty":"background-color: lightgrey"}},{"fri":{"courseName":"国学概论<br>(二教412)<br>(备注：工学博士及工学硕士必修！本部上课) 每周","parity":"","sty":"background-color: lightblue"},"mon":{"courseName":"","parity":"","sty":""},"sat":{"courseName":"","parity":"","sty":""},"sun":{"courseName":"","parity":"","sty":""},"thu":{"courseName":"","parity":"","sty":""},"timeNum":"第十节","tue":{"courseName":"","parity":"","sty":""},"wed":{"courseName":"高级软件工程<br>(二教423)<br>(备注：) 每周","parity":"","sty":"background-color: lightsalmon"}},{"fri":{"courseName":"国学概论<br>(二教412)<br>(备注：工学博士及工学硕士必修！本部上课) 每周","parity":"","sty":"background-color: lightblue"},"mon":{"courseName":"","parity":"","sty":""},"sat":{"courseName":"","parity":"","sty":""},"sun":{"courseName":"","parity":"","sty":""},"thu":{"courseName":"","parity":"","sty":""},"timeNum":"第十一节","tue":{"courseName":"","parity":"","sty":""},"wed":{"courseName":"高级软件工程<br>(二教423)<br>(备注：) 每周","parity":"","sty":"background-color: lightsalmon"}},{"fri":{"courseName":"国学概论<br>(二教412)<br>(备注：工学博士及工学硕士必修！本部上课) 每周","parity":"","sty":"background-color: lightblue"},"mon":{"courseName":"","parity":"","sty":""},"sat":{"courseName":"","parity":"","sty":""},"sun":{"courseName":"","parity":"","sty":""},"thu":{"courseName":"","parity":"","sty":""},"timeNum":"第十二节","tue":{"courseName":"","parity":"","sty":""},"wed":{"courseName":"高级软件工程<br>(二教423)<br>(备注：) 每周","parity":"","sty":"background-color: lightsalmon"}}],"remark":""}
 */
