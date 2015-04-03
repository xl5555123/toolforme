package com.pku.ipku.ui.navigation;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pku.ipku.R;
import com.pku.ipku.ui.person.CurriculumListFragment;
import com.pku.ipku.ui.person.arrearageState.ArrearageStateActivity;
import com.pku.ipku.ui.person.arrearageState.ArrearageStateFragment;
import com.pku.ipku.ui.person.library.LibraryActivity;
import com.pku.ipku.ui.pkuInfo.PkuLectureFragment;
import com.pku.ipku.ui.util.LazyFragment;

public class HomeFragment extends Fragment {

    private FragmentManager fragmentManager;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.homelecture, PkuLectureFragment.newInstance()).commit();
        fragmentManager.beginTransaction().replace(R.id.class_fragment, CurriculumListFragment.newInstance()).commit();
        fragmentManager.beginTransaction().replace(R.id.remain_fragment, ArrearageStateFragment.newInstance()).commit();
        view.findViewById(R.id.remain_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArrearageStateActivity.class);
                startActivity(intent);
            }
        });
    }
}
