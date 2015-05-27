package com.pku.portal.ui.person.queryClass;


import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pku.portal.R;
import com.pku.portal.adapter.person.QueryClassResultListAdapter;
import com.pku.portal.api.factory.IpkuServiceFactory;
import com.pku.portal.model.person.studyguide.Lesson;
import com.pku.portal.util.task.LoadDataConfigure;
import com.pku.portal.util.task.LoadDataDefaultTask;
import com.pku.portal.util.task.Result;
import com.pku.portal.ui.util.WebViewActivity;
import com.pku.portal.util.AppContextHolder;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QueryClassResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QueryClassResultFragment extends Fragment {
    private static final String ARG_PARAM1 = "query";

    private String query;
    private ListView listView;

    private List<Lesson> queryResult;

    public QueryClassResultFragment() {
        // Required empty public constructor
    }

    public static QueryClassResultFragment newInstance(String query) {
        QueryClassResultFragment fragment = new QueryClassResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, query);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            query = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_query_class_result, container, false);
        listView = (ListView) view.findViewById(R.id.listview);

        new LoadDataDefaultTask(new LoadDataConfigure() {
            @Override
            public void showData() {
                listView.setAdapter(new QueryClassResultListAdapter(AppContextHolder.getAppContext(), queryResult));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Lesson lesson = (Lesson) view.getTag();
                        if (lesson != null) {
                            Intent intent = new Intent(getActivity(), WebViewActivity.class);
                            intent.putExtra("url", lesson.getUrl());
                            intent.putExtra("title", lesson.getCourseName());
                            startActivity(intent);
                        }
                    }
                });
            }

            @Override
            public Result getData(boolean cache) {
                queryResult = IpkuServiceFactory.getPersonService(cache).queryLessons(query);
                if (queryResult == null) {
                    return new Result(Result.NET_ERROR);
                }
                return new Result(Result.NO_ERROR);
            }

            @Override
            public void showWaiting() {

            }

            @Override
            public void stopWaiting() {

            }

            @Override
            public void processError(Result result) {

            }
        }).execute();
        return view;
    }


}
