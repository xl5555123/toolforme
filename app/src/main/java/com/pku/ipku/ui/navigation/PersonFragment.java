package com.pku.ipku.ui.navigation;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.common.collect.Lists;
import com.pku.ipku.R;
import com.pku.ipku.adapter.navigation.PersonNavigationAdapter;
import com.pku.ipku.model.person.navigation.RegisterInPersonPage;
import com.pku.ipku.ui.person.PersonInfoActivity;
import com.pku.ipku.ui.person.ScoreActivity;
import com.pku.ipku.ui.person.arrearageState.ArrearageStateActivity;
import com.pku.ipku.ui.person.freeClassRoom.FreeClassroomActivity;
import com.pku.ipku.ui.person.library.LibraryActivity;
import com.pku.ipku.ui.person.queryClass.QueryClassActivity;
import com.pku.ipku.ui.pkuInfo.FindAJobActivity;
import com.pku.ipku.ui.pkuInfo.PkuLectureActivity;
import com.pku.ipku.ui.pkuInfo.PkuNoticeActivity;
import com.pku.ipku.ui.util.ImageFragment;

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
            add(new LibraryActivity());
            add(new ArrearageStateActivity());
            add(new FreeClassroomActivity());
            add(new QueryClassActivity());
            add(new ScoreActivity());
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

    private ViewPager autoScrollViewPager;

    private int position;

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
        position = 0;
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
        List<Integer> imageIds = Lists.newArrayList();
        imageIds.add(R.drawable.banner_1);
        imageIds.add(R.drawable.banner_2);
        imageIds.add(R.drawable.banner_3);
        imageIds.add(R.drawable.banner_4);
        autoScrollViewPager = (ViewPager) view.findViewById(R.id.banner_pager);
        autoScrollViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return ImageFragment.newInstance(R.drawable.banner_1);
                    case 1:
                        return ImageFragment.newInstance(R.drawable.banner_2);
                    case 2:
                        return ImageFragment.newInstance(R.drawable.banner_3);
                    default:
                        return ImageFragment.newInstance(R.drawable.banner_4);
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
    }

    private void initAutoScroll() {
        final Handler scrollHandller = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                position++;
                if (position > 3) {
                    position = 0;
                }
                autoScrollViewPager.setCurrentItem(position);
                scrollHandller.postDelayed(this, 5000);
            }
        };
        scrollHandller.postDelayed(runnable, 5000);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        initView(view);
        initAutoScroll();
        return view;
    }


}
