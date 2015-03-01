package com.pku.ipku.ui.person.queryClass;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.person.QueryClassResultListAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.studyguide.Lesson;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.ui.studyGuide.ClassDetail;
import com.pku.ipku.util.AppContextHolder;
import com.pku.ipku.util.DataHandleUtil;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
                            Intent intent = new Intent(getActivity(), ClassDetail.class);
                            intent.putExtra("lesson", DataHandleUtil.objectToJson(lesson));
                            startActivity(intent);
                        }
                    }
                });
            }

            @Override
            public boolean getData(boolean cache) {
                queryResult = IpkuServiceFactory.getPersonService(cache).queryLessons(query);
                if (queryResult != null) {
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
        return view;
    }


}
