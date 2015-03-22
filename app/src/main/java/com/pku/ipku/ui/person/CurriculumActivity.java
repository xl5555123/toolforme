package com.pku.ipku.ui.person;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.pku.ipku.R;
import com.pku.ipku.model.person.dto.CurriculumDTO;

import java.util.ArrayList;
import java.util.List;

public class CurriculumActivity extends FragmentActivity {


    private static int TOTAL_TAP = 7;
    private int todayInWeek;
    private String weeks[] = {"周一","周二","周三","周四","周五","周六","周日"};
    public static List<ArrayList<CurriculumDTO>> coursesForWeek = new ArrayList<ArrayList<CurriculumDTO>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_curriculum);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", "课程表");
        super.onCreate(savedInstanceState);
        todayInWeek = getIntent().getIntExtra("todayInWeek", 0);
//        Bundle bundle = getIntent().getBundleExtra("curiculum");
//        for(int i = 0; i < 7; i++){
//            ArrayList<CurriculumDTO> tmp = (ArrayList<CurriculumDTO>) bundle.getParcelableArrayList(i+"").get(0);
//            coursesForWeek.add(tmp);
//        }
        coursesForWeek = CurriculumListFragment.coursesForWeek;
        initView();
    }
    public void initView() {

        getActionBar().setTitle("课程表");
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        PagerTabStrip mPagerTabStrip=(PagerTabStrip) findViewById(R.id.curriculum_pts);
        //设置导航条的颜色
        mPagerTabStrip.setTabIndicatorColorResource(android.R.color.white);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.curriculum_vp);
        ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();//ViewPager中显示的数据
        ArrayList<String> titleList = new ArrayList<String>();// 标题数据

        //添加数据
        for (int i = 0; i < TOTAL_TAP; i++) {
            CurriculumForWeekFragment mViewPagerFragment = new CurriculumForWeekFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("day", i);// 这是周课程表里面显示的
            mViewPagerFragment.setArguments(bundle);// 设置参数
            titleList.add(weeks[i]);
            fragmentList.add(mViewPagerFragment);
        }
        mViewPager.setAdapter(new MyPagerFragmentAdapter(
                getSupportFragmentManager(), fragmentList, titleList));
        //mViewPager.setCurrentItem(todayInWeek);

    }


    //适配器
    private class MyPagerFragmentAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList;
        private List<String> titleList;

        public MyPagerFragmentAdapter(FragmentManager fm,
                                      List<Fragment> fragmentList, List<String> titleList) {
            super(fm);
            this.fragmentList = fragmentList;
            this.titleList = titleList;
        }

        // ViewPage中显示的内容
        @Override
        public Fragment getItem(int arg0) {
            // TODO Auto-generated method stub
            if(fragmentList == null || fragmentList.size() == 0)
                return null;
            Fragment tmp = fragmentList.get(arg0);
            ((CurriculumForWeekFragment)tmp).setDayInWeek(arg0);
            return tmp;
        }

        // Title中显示的内容
        @Override
        public CharSequence getPageTitle(int position) {
            // TODO Auto-generated method stub
            return (titleList.size() > position) ? titleList.get(position) : "";
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return fragmentList == null ? 0 : fragmentList.size();
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

}
