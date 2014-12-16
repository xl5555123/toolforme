package com.pku.ipku.ui.pkuInfo;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.pku.ipku.R;
import com.pku.ipku.model.pkuInfo.PkuClub;
import com.pku.ipku.model.pkuInfo.PkuInfo;
import com.pku.ipku.model.pkuInfo.PkuJob;
import com.pku.ipku.model.pkuInfo.PkuLecture;
import com.pku.ipku.model.pkuInfo.PkuNews;
import com.pku.ipku.model.pkuInfo.PkuNotice;
import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.util.DataHandleUtil;
import com.pku.ipku.util.UIHelper;

import java.util.ArrayList;
import java.util.List;

public class PkuInfoNavigationFragment extends Fragment {

    private FragmentActivity activity;

    private final static List<Fragmentable> items = new ArrayList<Fragmentable>() {
        {
            add(new PkuNotice());
            add(new PkuLecture());
            add(new PkuNews());
            add(new PkuClub());
            add(new PkuJob());
        }
    };

    public PkuInfoNavigationFragment() {
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
        View view = inflater.inflate(R.layout.fragment_pku_info, container, false);

        initActionBar();
        return view;
    }

    private void initActionBar() {
        final ActionBar actionBar = getActivity().getActionBar();
        activity = getActivity();
        UIHelper.setUpActionBarWithNavigation(actionBar, new PkuInfo().getChineseName(), activity, items);
    }



}
