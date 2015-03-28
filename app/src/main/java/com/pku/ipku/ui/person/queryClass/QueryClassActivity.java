package com.pku.ipku.ui.person.queryClass;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;

import com.pku.ipku.R;
import com.pku.ipku.model.person.navigation.RegisterInPersonPage;
import com.pku.ipku.model.studyguide.Lesson;
import com.pku.ipku.ui.util.BaseActivityIncludingFooterNavigation;
import com.pku.ipku.util.UIHelper;

import java.util.ArrayList;
import java.util.List;

public class QueryClassActivity extends BaseActivityIncludingFooterNavigation implements RegisterInPersonPage {

    private EditText query;

    public QueryClassActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_query_class);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", getPageTitle());
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        query = (EditText) findViewById(R.id.class_name);
        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (query != null) {
                    String queryString = query.getText().toString();
                    if (queryString == null || queryString.length() == 0) {
                        UIHelper.ToastMessage("课程名不能为空");
                        return;
                    }
                    Intent intent = new Intent(QueryClassActivity.this, QueryResultActivity.class);
                    intent.putExtra("query", queryString);
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public int getPageDrawableId() {
        return R.drawable.new_classes;
    }

    @Override
    public String getPageTitle() {
        return "课程查询";
    }

    @Override
    public int getPageBackgroundId() {
        return R.drawable.navigation_border_pink;
    }

    @Override
    public Class attachedClassType() {
        return QueryClassActivity.class;
    }
}
