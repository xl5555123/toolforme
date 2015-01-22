package com.pku.ipku.ui.networkHelp;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.pku.ipku.R;
import com.pku.ipku.model.PkuGuide;
import com.pku.ipku.util.UIHelper;

public class PkuNetworkHelpFragment extends Fragment {

    SlidingFragmentActivity slidingFragmentActivity;

    public PkuNetworkHelpFragment() {
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
        initActionBar();
        slidingFragmentActivity = (SlidingFragmentActivity) getActivity();
        slidingFragmentActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        return inflater.inflate(R.layout.fragment_pku_guide, container, false);
    }

    private void initActionBar() {
        ActionBar actionBar = getActivity().getActionBar();
        UIHelper.setUpActionBarWithNoNavigation(actionBar, new PkuGuide().getChineseName());
    }

}
