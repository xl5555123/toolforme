package com.pku.ipku.ui.navigation;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pku.ipku.R;
import com.pku.ipku.ui.person.ArrearageStateActivity;
import com.pku.ipku.ui.person.LibraryActivity;
import com.pku.ipku.ui.person.PersonInfoActivity;
import com.pku.ipku.ui.person.ScholarshipActivity;
import com.pku.ipku.ui.person.ScoreActivity;
import com.pku.ipku.ui.person.freeClassRoom.FreeClassroomActivity;
import com.pku.ipku.ui.person.queryClass.QueryClassActivity;
import com.pku.ipku.util.UIHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Activity parentActivity;

    public static PersonFragment newInstance() {
        PersonFragment fragment = new PersonFragment();
        return fragment;
    }

    public PersonFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private void initView(View view) {
        parentActivity = getActivity();
        view.findViewById(R.id.person_info_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parentActivity, PersonInfoActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.scholarship_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parentActivity, ScholarshipActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.user_grade_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parentActivity, ScoreActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.library_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parentActivity, LibraryActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.user_class_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parentActivity, QueryClassActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.user_classroom_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parentActivity, FreeClassroomActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.user_money_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parentActivity, ArrearageStateActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.user_class_result_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage("尽请期待");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        initView(view);
        return view;
    }


}
