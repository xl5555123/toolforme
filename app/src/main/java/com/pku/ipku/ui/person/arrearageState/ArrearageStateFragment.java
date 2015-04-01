package com.pku.ipku.ui.person.arrearageState;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pku.ipku.R;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.person.dto.ArrearageStateDTO;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.task.Result;
import com.pku.ipku.util.AppContextHolder;
import com.pku.ipku.util.UIHelper;

public class ArrearageStateFragment extends Fragment {

    private TextView libraryFeeTextView;
    private TextView netBalanceTextView;
    private TextView schhoolCardTextView;

    private ArrearageStateDTO arrearageStateDTO;

    // TODO: Rename and change types and number of parameters
    public static ArrearageStateFragment newInstance() {
        ArrearageStateFragment fragment = new ArrearageStateFragment();

        return fragment;
    }

    public ArrearageStateFragment() {
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
        View view = inflater.inflate(R.layout.fragment_arrearage_state, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        libraryFeeTextView = (TextView) view.findViewById(R.id.library_fee);
        netBalanceTextView = (TextView) view.findViewById(R.id.net_balance);
        schhoolCardTextView = (TextView) view.findViewById(R.id.campus_card_balance);
    }

    private void initData() {
        new LoadDataDefaultTask(new LoadDataConfigure() {
            @Override
            public void showData() {
                libraryFeeTextView.setText(Double.toString(arrearageStateDTO.getLibraryFee()));
                schhoolCardTextView.setText(Double.toString(arrearageStateDTO.getSchoolCardBalance()));
                netBalanceTextView.setText(Double.toString(arrearageStateDTO.getNetBalance()));
            }

            @Override
            public Result getData(boolean cache) {
                try {
                    int studentId = Integer.decode(AppContextHolder.getAppContext().getCurrentUser().getUsername());
                    arrearageStateDTO = IpkuServiceFactory.getPersonService(cache).getArrearageState(studentId);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new Result(Result.NET_ERROR);
                }
                if (arrearageStateDTO == null) {
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
        }).execute();
    }

}
