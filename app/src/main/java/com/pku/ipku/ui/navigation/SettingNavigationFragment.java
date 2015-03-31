package com.pku.ipku.ui.navigation;


import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pku.ipku.R;
import com.pku.ipku.ui.account.LoginActivity;
import com.pku.ipku.ui.account.NetworkHelperActivity;
import com.pku.ipku.ui.person.CurriculumListFragment;
import com.pku.ipku.ui.setting.BeijingRailway;
import com.pku.ipku.ui.setting.PhoneActivity;
import com.pku.ipku.ui.setting.PkuMap;
import com.pku.ipku.ui.util.WebViewActivity;
import com.pku.ipku.util.AppContextHolder;
import com.pku.ipku.util.UIHelper;

import org.json.JSONArray;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingNavigationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingNavigationFragment extends Fragment {

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
        view.findViewById(R.id.log_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("注销登录?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AppContextHolder.getAppContext().deleteCurrentUser();
                                //这里不把这个设置成新的JSONArray的话，由于fragment还没有释放，用的还是原来那个，所有会导致没变，其他方法？
                                CurriculumListFragment.courses = new JSONArray();
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                if (intent != null) {
                                    startActivity(intent);
                                    getActivity().finish();
                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        }).create().show();
            }
        });
        view.findViewById(R.id.network).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NetworkHelperActivity.class);
                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
        view.findViewById(R.id.phone_usual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PhoneActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.pku_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PkuMap.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.beijing_railway).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BeijingRailway.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.pku_calendar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url", "http://www.pku.edu.cn/about/xl/xl(2014-2015).jsp");
                intent.putExtra("title", "校历");
                startActivity(intent);
            }
        });
    }


}
