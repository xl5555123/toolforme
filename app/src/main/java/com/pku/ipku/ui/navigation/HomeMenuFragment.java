package com.pku.ipku.ui.navigation;


import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.pku.ipku.R;
import com.pku.ipku.adapter.HomeMenuAdapter;
import com.pku.ipku.model.Person;
import com.pku.ipku.model.PkuGuide;
import com.pku.ipku.model.PkuMap;
import com.pku.ipku.model.PkuNews;
import com.pku.ipku.model.StudyHelper;
import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.util.AppContextHolder;
import com.pku.ipku.util.BitmapManager;

import java.util.ArrayList;
import java.util.List;

public class HomeMenuFragment extends SherlockListFragment {

    private ListView menuListView;

    private BitmapManager bmpManager;

    private ImageView userFace;

    private TextView userName;

    //目录顺序
    public final static List<Fragmentable> menuItemList = new ArrayList<Fragmentable>() {
        {
            add(new Person());
            add(new PkuGuide());
            add(new PkuMap());
            add(new PkuNews());
            add(new StudyHelper());
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_menu, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        this.userFace = (ImageView)view.findViewById(R.id.user_face);
        this.userName = (TextView)view.findViewById(R.id.user_name);
        this.bmpManager = new BitmapManager(BitmapFactory.decodeResource(AppContextHolder.getAppContext().getResources(),
                R.drawable.widget_dface_loading));
        userName.setText("邢亮");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        HomeMenuAdapter homeMenuAdapter = new HomeMenuAdapter(getActivity().getApplicationContext(), menuItemList,
                R.layout.menu_item);
        setListAdapter(homeMenuAdapter);
    }

    private void selectItemChangingColor(int position, ListView lv) {
        for (int i =0; i < lv.getChildCount();i++) {
            TextView menuText = (TextView)lv.getChildAt(i).findViewById(R.id.menu_text);
            ImageView image = (ImageView)lv.getChildAt(i).findViewById(R.id.menu_icon);
            menuText.setTextColor(Color.argb(150, 255, 255, 255));
            image.setAlpha(150);
        }
        TextView menuText = (TextView)lv.getChildAt(position).findViewById(R.id.menu_text);
        ImageView image = (ImageView)lv.getChildAt(position).findViewById(R.id.menu_icon);
        menuText.setTextColor(Color.argb(255, 255, 255, 255));
        image.setAlpha(255);
    }

    @Override
    public void onListItemClick(ListView lv, View v, int position, long id) {
        Fragment newContent = null;
        Home homeActivity = (Home) getActivity();
        selectItemChangingColor(position, lv);
        newContent = menuItemList.get(position).getAttachedFragment(null);
        if (newContent != null) {
            switchFragment(newContent);
            homeActivity.invalidateOptionsMenu();
        }
    }

    private void switchFragment(Fragment fragment) {
        if (getActivity() == null)
            return;

        if (getActivity() instanceof Home) {
            Home homeActivity= (Home) getActivity();
            homeActivity.switchContent(fragment);
        }
    }

}
