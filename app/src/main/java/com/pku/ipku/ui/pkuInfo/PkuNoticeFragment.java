package com.pku.ipku.ui.pkuInfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pku.ipku.R;

public class PkuNoticeFragment extends Fragment {
    private ListView listView;
    public PkuNoticeFragment() {
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

        View view =  inflater.inflate(R.layout.fragment_pku_notice, container, false);
        listView = (ListView)view.findViewById(R.id.notice_list);
        return view;
    }
}
