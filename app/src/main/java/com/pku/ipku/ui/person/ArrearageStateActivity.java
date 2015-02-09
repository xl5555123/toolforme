package com.pku.ipku.ui.person;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import com.pku.ipku.R;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.person.dto.ArrearageStateDTO;
import com.pku.ipku.model.person.navigation.RegisterInPersonPage;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.ui.util.BaseActivityIncludingFooterNavigation;

public class ArrearageStateActivity extends BaseActivityIncludingFooterNavigation implements RegisterInPersonPage {

    private TextView libraryFeeTextView;
    private TextView netBalanceTextView;
    private TextView schhoolCardTextView;

    private ArrearageStateDTO arrearageStateDTO;

    public ArrearageStateActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_arrearage_state);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", "欠费信息");
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        libraryFeeTextView = (TextView) findViewById(R.id.library_fee);
        netBalanceTextView = (TextView) findViewById(R.id.net_balance);
        schhoolCardTextView = (TextView) findViewById(R.id.campus_card_balance);
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

    @Override
    public int getPageDrawableId() {
        return R.drawable.user_money;
    }

    @Override
    public String getPageTitle() {
        return "欠费信息";
    }

    @Override
    public Class attachedClassType() {
        return ArrearageStateActivity.class;
    }
}
