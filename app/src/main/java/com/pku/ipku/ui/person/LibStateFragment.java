package com.pku.ipku.ui.person;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.person.LibStateAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.person.dto.LibBorrowDTO;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.ui.AppContext;

import java.util.List;

public class LibStateFragment extends Fragment {

    private List<LibBorrowDTO> bookList;
    private ListView bookListView;
    private AppContext appContext;

    public LibStateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_library, container, false);
        bookListView = (ListView)view.findViewById(R.id.lib_book_listview);
        appContext = (AppContext)getActivity().getApplicationContext();
        new LoadDataDefaultTask(new LoadLibStateConfigure()).execute();
        return view;
    }

    private class LoadLibStateConfigure implements LoadDataConfigure {

        @Override
        public void showData() {
            bookListView.setAdapter(new LibStateAdapter(appContext, bookList));
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
