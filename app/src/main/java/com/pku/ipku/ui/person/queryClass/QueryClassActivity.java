package com.pku.ipku.ui.person.queryClass;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.pku.ipku.R;
import com.pku.ipku.model.person.navigation.RegisterInPersonPage;
import com.pku.ipku.model.studyguide.Lesson;
import com.pku.ipku.ui.util.BaseActivityIncludingFooterNavigation;

import java.util.ArrayList;
import java.util.List;

public class QueryClassActivity extends BaseActivityIncludingFooterNavigation implements RegisterInPersonPage {

    private MenuItem searchItem;
    private SearchView searchView;

    private QueryClassResultFragment searchResultFragment;

    private Lesson lesson;

    private List<String> yearToSelect = new ArrayList<String>() {
        {
            add("06-07");
            add("07-08");
            add("08-09");
            add("09-10");
            add("10-11");
            add("11-12");
            add("12-13");
            add("13-14");
            add("14-15");
            add("15-16");
        }
    };

    private List<String> teamToSelect = new ArrayList<String>() {
        {
            add("第一学期");
            add("第二学期");
            add("暑期学校");
        }
    };

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
        findViewById(R.id.query_class_frame);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.library_search, menu);
        searchItem = menu.findItem(R.id.search);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =
                (SearchView) searchItem.getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchItem.expandActionView();
        searchView.setQueryHint("搜索课程");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchResultFragment = QueryClassResultFragment.newInstance(query);
                getFragmentManager().beginTransaction().replace(R.id.query_class_frame, searchResultFragment).commit();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
}
