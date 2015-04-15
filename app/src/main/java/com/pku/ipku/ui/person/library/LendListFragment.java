package com.pku.ipku.ui.person.library;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.person.library.MyLendAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.person.dto.LibBorrowDTO;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.task.Result;
import com.pku.ipku.util.AppContextHolder;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LendListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LendListFragment extends Fragment {

    private ListView lendListView;

    private List<LibBorrowDTO> libBorrowDTOs;

    public LendListFragment() {
        // Required empty public constructor
    }

    public static LendListFragment newInstance() {
        LendListFragment fragment = new LendListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lend_list, container, false);
        initView(view);
        new LoadDataDefaultTask(new LoadLibStateConfigure()).execute();
        return view;
    }

    private void initView(View view) {
        lendListView = (ListView) view.findViewById(R.id.lend_listview);
    }

    private class LoadLibStateConfigure implements LoadDataConfigure {

        @Override
        public void showData() {
            lendListView.setAdapter(new MyLendAdapter(AppContextHolder.getAppContext(), libBorrowDTOs));
        }

        @Override
        public Result getData(boolean cache) {
            libBorrowDTOs = IpkuServiceFactory.getPersonService(cache).getLibBorrowInfo();
            if (libBorrowDTOs == null) {
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
    }

}
