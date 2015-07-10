package com.xingliang.toolforme.ui.person.arrearageState;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xingliang.toolforme.R;
import com.xingliang.toolforme.api.factory.IpkuServiceFactory;
import com.xingliang.toolforme.model.account.User;
import com.xingliang.toolforme.model.person.dto.ArrearageStateDTO;
import com.xingliang.toolforme.util.task.LoadDataConfigure;
import com.xingliang.toolforme.util.task.LoadDataDefaultTask;
import com.xingliang.toolforme.util.task.Result;
import com.xingliang.toolforme.util.AppContextHolder;

public class ArrearageStateFragment extends Fragment {

    private TextView netBalanceTextView;
    private TextView schhoolCardTextView;

    private ArrearageStateDTO arrearageStateDTO;

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
        netBalanceTextView = (TextView) view.findViewById(R.id.net_balance);
        schhoolCardTextView = (TextView) view.findViewById(R.id.campus_card_balance);
    }

    private void initData() {
        new LoadDataDefaultTask(new LoadDataConfigure() {
            @Override
            public void showData() {
                schhoolCardTextView.setText(Double.toString(arrearageStateDTO.getSchoolCardBalance()));
                netBalanceTextView.setText(Double.toString(arrearageStateDTO.getNetBalance()));
            }

            @Override
            public Result getData(boolean cache) {
                try {
                    User currentUser = AppContextHolder.getAppContext().getCurrentUser();
                    arrearageStateDTO = IpkuServiceFactory.getPersonService(cache).getArrearageState(currentUser.getUsername());
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
