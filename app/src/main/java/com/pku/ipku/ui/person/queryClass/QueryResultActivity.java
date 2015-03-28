package com.pku.ipku.ui.person.queryClass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.person.QueryClassResultListAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.studyguide.Lesson;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.task.Result;
import com.pku.ipku.ui.util.WebViewActivity;
import com.pku.ipku.util.AppContextHolder;
import com.pku.ipku.util.UIHelper;

import java.util.List;

public class QueryResultActivity extends Activity {


    private static final String QUERY = "query";

    private String query;
    private ListView listView;
    private View progress;

    private List<Lesson> queryResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_result);
        query = getIntent().getStringExtra(QUERY);
        initView();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listview);
        progress = findViewById(R.id.progress);

        new LoadDataDefaultTask(new LoadDataConfigure() {
            @Override
            public void showData() {
                if (queryResult == null || queryResult.size() == 0) {
                    UIHelper.ToastMessage("没有此课程");
                    finish();
                }
                listView.setAdapter(new QueryClassResultListAdapter(AppContextHolder.getAppContext(), queryResult));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Lesson lesson = (Lesson) view.getTag();
                        if (lesson != null) {
                            Intent intent = new Intent(QueryResultActivity.this, WebViewActivity.class);
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
                progress.setVisibility(View.VISIBLE);
            }

            @Override
            public void stopWaiting() {
                progress.setVisibility(View.GONE);
            }

            @Override
            public void processError(Result result) {

            }
        }).execute();
    }
}
