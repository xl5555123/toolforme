package com.pku.portal.ui.setting;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.pku.portal.R;

public class PkuMap extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pku_map);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", "北大地图");
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.pkumap));
    }
}
