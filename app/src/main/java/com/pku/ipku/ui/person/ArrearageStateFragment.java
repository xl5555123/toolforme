package com.pku.ipku.ui.person;

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

public class ArrearageStateFragment extends Fragment {

    private TextView libraryFeeTextView;
    private TextView netBalanceTextView;
    private TextView schhoolCardTextView;

    private ArrearageStateDTO arrearageStateDTO;

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
            public boolean getData(boolean cache) {
                arrearageStateDTO = IpkuServiceFactory.getPersonService(cache).getArrearageState();
                if (arrearageStateDTO == null) {
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
        }).execute();
    }
}
