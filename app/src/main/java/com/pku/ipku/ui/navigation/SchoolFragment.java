package com.pku.ipku.ui.navigation;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.navigation.SchoolNavigationAdapter;
import com.pku.ipku.model.pkuInfo.PkuInfoType;
import com.pku.ipku.ui.pkuInfo.PkuPublicInfoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SchoolFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SchoolFragment extends Fragment {

    private final static List<PkuInfoType> pkuInfoTypeList = new ArrayList<PkuInfoType>() {
        {
            add(new PkuInfoType(PkuInfoType.PKU_NEWS));
            add(new PkuInfoType(PkuInfoType.PKU_NOTICES));
            add(new PkuInfoType(PkuInfoType.PKU_LECTURES));
            add(new PkuInfoType(PkuInfoType.PKU_CAREER));
        }
    };

    private Activity parentActivity;

    private GridView icon;

    public SchoolFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SchoolFragment newInstance() {
        SchoolFragment fragment = new SchoolFragment();
        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //   mParam1 = getArguments().getString(ARG_PARAM1);
            //     mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_school, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        parentActivity = getActivity();
        icon = (GridView) view.findViewById(R.id.icon);
        icon.setAdapter(new SchoolNavigationAdapter(parentActivity, pkuInfoTypeList));
        icon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PkuInfoType pkuInfoType = (PkuInfoType) view.getTag();
                if (pkuInfoType != null) {
                    Intent intent = new Intent(parentActivity, PkuPublicInfoActivity.class);
                    intent.putExtra("type", pkuInfoType.getType());
                    startActivity(intent);
                }
            }
        });
        /*
        view.findViewById(R.id.school_news_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parentActivity, PkuPublicInfoActivity.class);
                intent.putExtra("type", PkuInfoType.PKU_NEWS);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.school_notification_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parentActivity, PkuPublicInfoActivity.class);
                intent.putExtra("type", PkuInfoType.PKU_NOTICES);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.school_job_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parentActivity, PkuPublicInfoActivity.class);
                intent.putExtra("type", PkuInfoType.PKU_CAREER);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.school_lecture_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parentActivity, PkuPublicInfoActivity.class);
                intent.putExtra("type", PkuInfoType.PKU_LECTURES);
                startActivity(intent);
            }
        });
         */
    }


}
