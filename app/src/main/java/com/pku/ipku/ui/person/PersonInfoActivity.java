package com.pku.ipku.ui.person;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pku.ipku.R;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.person.dto.StuInfoDTO;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.ui.util.BaseActivityIncludingFooterNavigation;

/**
 * Created by XingLiang on 2015/2/5.
 */
public class PersonInfoActivity extends BaseActivityIncludingFooterNavigation {

    private StuInfoDTO stuInfo ;
    private ImageView userFace;
    private TextView userName;
    private TextView userSex;
    private TextView userRace;
    private TextView userNativePlace;
    private TextView userSupervisor;
    private TextView userPoliticalStatus;
    private TextView userType;
    private TextView userCredentials;
    private TextView userCredentialsId;
    private TextView userBirthday;
    private TextView userEnterSchoolDate;
    private TextView userExamId;
    private TextView userStuId;
    private TextView userDepartment;
    private TextView userMajor;
    private TextView userResearchArea;

    LoadDataDefaultTask loadDataDefaultTask = new LoadDataDefaultTask(new LoadStuInfoConfigure());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_person_info);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", "个人信息");
        super.onCreate(savedInstanceState);
        initView();
        loadDataDefaultTask.execute();
    }

    private class LoadStuInfoConfigure implements LoadDataConfigure {
        @Override
        public void showData(){
            userFace.setScaleType(ImageView.ScaleType.FIT_XY);
            userName.setText(stuInfo.getName());
            userSex.setText(stuInfo.getSex());
            userRace.setText(stuInfo.getRace());
            userNativePlace.setText(stuInfo.getNativePlace());
            userSupervisor.setText(stuInfo.getSupervisor());
            userPoliticalStatus.setText(stuInfo.getPoliticalStatus());
            userType.setText(stuInfo.getStuType());
            userCredentials.setText(stuInfo.getCredentials());
            userCredentialsId.setText(stuInfo.getCredentialsId());
            userBirthday.setText(stuInfo.getBirthday());
            userEnterSchoolDate.setText(stuInfo.getEnterSchoolDate());
            userExamId.setText(stuInfo.getStuExamId());
            userStuId.setText(stuInfo.getStuId());
            //userStuId.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG );
            userDepartment.setText(stuInfo.getDepartment());
            userMajor.setText(stuInfo.getMajor());
            userResearchArea.setText(stuInfo.getResearchArea());
        }
        @Override
        public boolean getData(boolean cache){
            stuInfo = IpkuServiceFactory.getPersonService(cache).getStuInfo();
            if(stuInfo == null)
                return false;
            return true;
        }

        @Override
        public void showWaiting(){

        }

        @Override
        public void stopWaiting(){

        }
    }

    public void initView(){
        this.userFace = (ImageView)findViewById(R.id.user_face);
        this.userName = (TextView)findViewById(R.id.user_name);
        this.userSex = (TextView)findViewById(R.id.user_sex);
        this.userRace = (TextView)findViewById(R.id.user_race);
        this.userNativePlace = (TextView)findViewById(R.id.user_nativePlace);
        this.userSupervisor = (TextView)findViewById(R.id.user_supervisor);
        this.userPoliticalStatus = (TextView)findViewById(R.id.user_politicalStatus);
        this.userType = (TextView)findViewById(R.id.user_stuType);
        this.userCredentials = (TextView)findViewById(R.id.user_credentials);
        this.userCredentialsId = (TextView)findViewById(R.id.user_credentialsId);
        this.userBirthday = (TextView)findViewById(R.id.user_birthday);
        this.userEnterSchoolDate = (TextView)findViewById(R.id.user_enterSchoolDate);
        this.userExamId = (TextView)findViewById(R.id.user_stuExamId);
        this.userStuId = (TextView)findViewById(R.id.user_stuId);
        this.userDepartment = (TextView)findViewById(R.id.user_department);
        this.userMajor = (TextView)findViewById(R.id.user_major);
        this.userResearchArea = (TextView)findViewById(R.id.user_researchArea);

    }
}
