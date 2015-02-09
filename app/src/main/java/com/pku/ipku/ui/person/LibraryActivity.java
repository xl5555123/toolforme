package com.pku.ipku.ui.person;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListView;

import com.pku.ipku.R;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.person.dto.LibBorrowDTO;
import com.pku.ipku.model.person.navigation.RegisterInPersonPage;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_library);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", getPageTitle());
        super.onCreate(savedInstanceState);
        bookListView = (ListView) findViewById(R.id.lib_book_listview);
        appContext = (AppContext) getApplicationContext();
        new LoadDataDefaultTask(new LoadLibStateConfigure()).execute();
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
