package com.pku.ipku.ui.navigation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pku.ipku.R;
import com.pku.ipku.ui.person.CurriculumListFragment;
import com.pku.ipku.ui.person.arrearageState.ArrearageStateActivity;
import com.pku.ipku.ui.person.arrearageState.ArrearageStateFragment;
import com.pku.ipku.ui.person.library.LibraryActivity;
import com.pku.ipku.ui.pkuInfo.PkuLectureFragment;

public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static HomeFragment fragment;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {

        if(fragment == null)
            fragment = new  HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        getFragmentManager().beginTransaction().replace(R.id.lecture_fragment, PkuLectureFragment.newInstance()).commit();
        getFragmentManager().beginTransaction().replace(R.id.remain_fragment, ArrearageStateFragment.newInstance()).commit();
        getFragmentManager().beginTransaction().replace(R.id.library_fragment, CurriculumListFragment.newInstance()).commit();
        view.findViewById(R.id.remain_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArrearageStateActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.library_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LibraryActivity.class);
                startActivity(intent);
            }
        });
    }
}
