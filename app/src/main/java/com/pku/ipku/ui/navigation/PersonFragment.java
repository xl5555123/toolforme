package com.pku.ipku.ui.navigation;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.navigation.PersonNavigationAdapter;
import com.pku.ipku.model.person.navigation.RegisterInPersonPage;
import com.pku.ipku.ui.person.arrearageState.ArrearageStateActivity;
import com.pku.ipku.ui.person.PersonInfoActivity;
import com.pku.ipku.ui.person.ScholarshipActivity;
import com.pku.ipku.ui.person.ScoreActivity;
import com.pku.ipku.ui.person.freeClassRoom.FreeClassroomActivity;
import com.pku.ipku.ui.person.library.LibraryActivity;
import com.pku.ipku.ui.person.queryClass.QueryClassActivity;
import com.pku.ipku.ui.pkuInfo.FindAJobActivity;
import com.pku.ipku.ui.pkuInfo.PkuLectureActivity;
import com.pku.ipku.ui.pkuInfo.PkuNoticeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonFragment extends Fragment {

    private final static List<RegisterInPersonPage> registerInPersonPageList = new ArrayList<RegisterInPersonPage>() {
        {
            add(new PersonInfoActivity());
            add(new ScoreActivity());
            add(new QueryClassActivity());
            add(new FreeClassroomActivity());
            add(new LibraryActivity());
            add(new ArrearageStateActivity());
            add(new FindAJobActivity());
            add(new PkuLectureActivity());
            add(new PkuNoticeActivity());
        }
    };
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Activity parentActivity;

    private GridView navigationGridView;

    public PersonFragment() {
        // Required empty public constructor
    }

    public static PersonFragment newInstance() {
        PersonFragment fragment = new PersonFragment();
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

    private void initView(View view) {
        parentActivity = getActivity();
        navigationGridView = (GridView) view.findViewById(R.id.navigation_grid);
        navigationGridView.setAdapter(new PersonNavigationAdapter(parentActivity, registerInPersonPageList));
        navigationGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RegisterInPersonPage registerInPersonPage = (RegisterInPersonPage) view.getTag();
                if (registerInPersonPage != null) {
                    Intent intent = new Intent(parentActivity, registerInPersonPage.attachedClassType());
                    if (intent != null) {
                        startActivity(intent);
                    }
                }
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
