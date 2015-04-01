package com.pku.ipku.ui.person.arrearageState;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pku.ipku.R;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.person.dto.ArrearageStateDTO;
import com.pku.ipku.model.person.navigation.RegisterInPersonPage;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.task.Result;
import com.pku.ipku.ui.util.BaseActivityIncludingFooterNavigation;
import com.pku.ipku.util.AppContextHolder;
import com.pku.ipku.util.UIHelper;

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
        findViewById(R.id.alipay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage("使用支付宝来充值吧~");
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri url = Uri.parse("http://m.alipay.com/appIndex.htm");
                intent.setData(url);
                startActivity(intent);
            }
        });
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

    @Override
    public int getPageDrawableId() {
        return R.drawable.new_fee;
    }

    @Override
    public String getPageTitle() {
        return "欠费信息";
    }

    @Override
    public int getPageBackgroundId() {
        return R.drawable.navigation_border_purple;
    }

    @Override
    public Class attachedClassType() {
        return ArrearageStateActivity.class;
    }
}
