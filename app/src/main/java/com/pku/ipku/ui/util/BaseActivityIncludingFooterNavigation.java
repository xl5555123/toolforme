package com.pku.ipku.ui.util;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pku.ipku.R;

/**
 * Created by XingLiang on 2015/2/5.
 */
public class BaseActivityIncludingFooterNavigation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageButton back = (ImageButton)findViewById(R.id.back);
        if (back != null) {
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        String title = savedInstanceState.getString("title");
        TextView titleTextView = (TextView) findViewById(R.id.title);
        if (title != null && titleTextView != null) {
            titleTextView.setText(title);
        }
    }
}
