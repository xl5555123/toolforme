package com.xingliang.toolforme.ui.person.arrearageState;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xingliang.toolforme.R;
import com.xingliang.toolforme.api.factory.IpkuServiceFactory;
import com.xingliang.toolforme.model.account.User;
import com.xingliang.toolforme.model.person.dto.ArrearageStateDTO;
import com.xingliang.toolforme.model.navigation.RegisterInPersonPage;
import com.xingliang.toolforme.util.task.LoadDataConfigure;
import com.xingliang.toolforme.util.task.LoadDataDefaultTask;
import com.xingliang.toolforme.util.task.Result;
import com.xingliang.toolforme.ui.util.BaseActivityIncludingFooterNavigation;
import com.xingliang.toolforme.util.AppContextHolder;
import com.xingliang.toolforme.util.UIHelper;

public class ArrearageStateActivity extends BaseActivityIncludingFooterNavigation implements RegisterInPersonPage {

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
        savedInstanceState.putString("title", "我的钱包");
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
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
        findViewById(R.id.compute_alipay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage("网关充值");
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri url = Uri.parse("http://its.pku.edu.cn/payBank.html");
                intent.setData(url);
                startActivity(intent);
            }
        });
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

    @Override
    public int getPageDrawableId() {
        return R.drawable.new_fee;
    }

    @Override
    public String getPageTitle() {
        return "我的钱包";
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
