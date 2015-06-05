package com.pku.portal.ui.person.queryClass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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
import com.pku.portal.util.UIHelper;

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
        getActionBar().setTitle("课程查询");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
