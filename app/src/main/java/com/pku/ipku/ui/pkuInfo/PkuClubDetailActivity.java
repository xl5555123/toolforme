package com.pku.ipku.ui.pkuInfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

import com.pku.ipku.R;
import com.pku.ipku.model.pkuInfo.dto.PkuClubDTO;

public class PkuClubDetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("DEBUG", "============PkuClubDetailActivity start");
        setContentView(R.layout.activity_pku_club_detail);
        Intent intent = getIntent();
        //获取社团活动对象
        PkuClubDTO pkuClubDTO = (PkuClubDTO)intent.getSerializableExtra(PkuClubFragment.CLUB_ACTIVITY_KEY);
        TextView subject = (TextView)findViewById(R.id.club_detail_subject);
        subject.setText(pkuClubDTO.getSubject());
    }

}
