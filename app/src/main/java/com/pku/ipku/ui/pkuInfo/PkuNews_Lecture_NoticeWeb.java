package com.pku.ipku.ui.pkuInfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.pku.ipku.R;

/**
 * Created by jiatong on 2015/1/16.
 */
public class PkuNews_Lecture_NoticeWeb extends Fragment {
    private WebView webview;
    public PkuNews_Lecture_NoticeWeb() {
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
        View view =  inflater.inflate(R.layout.fragment_news_lecture_notice_web, container, false);
        webview = (WebView)view.findViewById(R.id.webview_news);
        webview.loadUrl("www.baidu.com");
        return view;
    }
}
