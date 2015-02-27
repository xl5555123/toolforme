package com.pku.ipku.ui.person.library;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.SearchView;

import com.pku.ipku.R;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.person.dto.LibBorrowDTO;
import com.pku.ipku.model.person.navigation.RegisterInPersonPage;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.ui.AppContext;
import com.pku.ipku.util.UIHelper;

import java.util.List;

/**
 * Created by XingLiang on 2015/2/5.
 */
public class LibraryActivity extends Activity implements RegisterInPersonPage {

    private List<LibBorrowDTO> bookList;
    private ListView bookListView;
    private AppContext appContext;
    private SearchView searchView;
    private int searchType;

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
                } else if (title.equals(LIBRARY_TAB_TITLE.SCHOLAR_SEARCH)) {
                    searchView.setQueryHint("未名学术搜索");
                } else if (title.equals(LIBRARY_TAB_TITLE.MY_LEND)) {
                    UIHelper.ToastMessage("我的借阅");
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
        return R.drawable.user_library;
    }

    @Override
    public String getPageTitle() {
        return "图书馆";
    }

    @Override
    public Class attachedClassType() {
        return LibraryActivity.class;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.library_search, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    private final static class LIBRARY_TAB_TITLE {
        public final static String COLLECT_INDEX = "馆藏目录";
        public final static String SCHOLAR_SEARCH = "未名学术搜索";
        public final static String MY_LEND = "我的借阅";
    }

    private final static class LIBRARY_SEARCH_TYPE {
        public final static int COLLECT_INDEX = 1;
        public final static int SCHOLAR_SEARCH = 2;
        public final static int MY_LEND = 3;
    }

    private class LoadLibStateConfigure implements LoadDataConfigure {

        @Override
        public void showData() {
            bookListView.setAdapter(new com.pku.ipku.adapter.person.LibStateAdapter(appContext, bookList));
        }

        @Override
        public boolean getData(boolean cache) {
            bookList = IpkuServiceFactory.getPersonService(cache).getLibBorrowInfo();
            if (bookList == null) {
                return false;
            }
            return true;
        }

        @Override
        public void showWaiting() {

        }

        @Override
        public void stopWaiting() {

        }
    }
}
