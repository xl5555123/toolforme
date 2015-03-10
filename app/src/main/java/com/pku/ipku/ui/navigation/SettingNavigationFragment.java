package com.pku.ipku.ui.navigation;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pku.ipku.R;
import com.pku.ipku.ui.account.AccountManagementActivity;
import com.pku.ipku.util.UIHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingNavigationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingNavigationFragment extends Fragment {

    private View accountManagementButton;
    private View networkNavigationButton;
    private View noticeManagementButton;
    private View aboutIPKUButton;

    public SettingNavigationFragment() {
        // Required empty public constructor
    }

    public static SettingNavigationFragment newInstance() {
        SettingNavigationFragment fragment = new SettingNavigationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting_navigation, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        accountManagementButton = view.findViewById(R.id.account_setting);
        noticeManagementButton = view.findViewById(R.id.notice_management);
        networkNavigationButton = view.findViewById(R.id.network_management);
        aboutIPKUButton = view.findViewById(R.id.about_ipku);
        accountManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage("尽请期待!");
            }
        });
        noticeManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage("尽请期待!");
            }
        });
        networkNavigationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage("尽请期待!");
            }
        });
        aboutIPKUButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage("尽请期待!");
            }
        });
    }


}
