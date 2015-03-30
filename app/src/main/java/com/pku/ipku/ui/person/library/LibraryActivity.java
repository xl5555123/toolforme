package com.pku.ipku.ui.person.library;


import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.pku.ipku.R;
import com.pku.ipku.model.person.dto.LibBorrowDTO;
import com.pku.ipku.model.person.navigation.RegisterInPersonPage;
import com.pku.ipku.ui.AppContext;
import com.pku.ipku.ui.util.BaseActivityIncludingFooterNavigation;

import java.util.List;

/**
 * Created by XingLiang on 2015/2/5.
 */
public class LibraryActivity extends BaseActivityIncludingFooterNavigation implements RegisterInPersonPage {

    private List<LibBorrowDTO> bookList;
    private ListView bookListView;
    private AppContext appContext;
    private MenuItem searchItem;
    private SearchView searchView;
    private int searchType;
    private Fragment searchResultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_library);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", getPageTitle());
        setUpActionBar();
        super.onCreate(savedInstanceState);
    }

    private void setUpActionBar() {
        ActionBar actionBar = getActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(getPageTitle());
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                String title = tab.getText().toString();
                if (title.equals(LIBRARY_TAB_TITLE.COLLECT_INDEX)) {
                    searchView.setQueryHint("搜索馆藏图书");
                    searchItem.expandActionView();
                    getSupportFragmentManager().beginTransaction().remove(searchResultFragment).commit();
                } else if (title.equals(LIBRARY_TAB_TITLE.SCHOLAR_SEARCH)) {
                    searchView.setQueryHint("未名学术搜索");
                    searchItem.expandActionView();
                    getSupportFragmentManager().beginTransaction().remove(searchResultFragment).commit();
                } else if (title.equals(LIBRARY_TAB_TITLE.MY_LEND)) {
                    searchResultFragment = LendListFragment.newInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.search_content, searchResultFragment).commit();
                    if (searchItem != null) {
                        searchItem.collapseActionView();
                    }
                }
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };
        actionBar.addTab(actionBar.newTab().setText(LIBRARY_TAB_TITLE.MY_LEND).setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText(LIBRARY_TAB_TITLE.COLLECT_INDEX).setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText(LIBRARY_TAB_TITLE.SCHOLAR_SEARCH).setTabListener(tabListener));
    }

    @Override
    public int getPageDrawableId() {
        return R.drawable.new_library;
    }

    @Override
    public String getPageTitle() {
        return "图书馆";
    }

    @Override
    public int getPageBackgroundId() {
        return R.drawable.navigation_border_red;
    }

    @Override
    public Class attachedClassType() {
        return LibraryActivity.class;
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
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchResultFragment = LendListFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.search_content, searchResultFragment).commit();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    private final static class LIBRARY_TAB_TITLE {
        public final static String COLLECT_INDEX = "馆藏目录";
        public final static String SCHOLAR_SEARCH = "未名学术搜索";
        public final static String MY_LEND = "我的借阅";
    }
}
