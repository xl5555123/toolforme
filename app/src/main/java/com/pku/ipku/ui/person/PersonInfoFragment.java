package com.pku.ipku.ui.person;
/**
 * Author :@tangxiaoqing
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pku.ipku.R;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.person.dto.StuInfoDTO;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;

public class PersonInfoFragment extends Fragment {
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

    public PersonInfoFragment() {
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
        View view = inflater.inflate(R.layout.fragment_person_info, container, false);
        initView(view);
        loadDataDefaultTask.execute();
        return view;
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

    public void initView(View view){
        this.userFace = (ImageView)view.findViewById(R.id.user_face);
        this.userName = (TextView)view.findViewById(R.id.user_name);
        this.userSex = (TextView)view.findViewById(R.id.user_sex);
        this.userRace = (TextView)view.findViewById(R.id.user_race);
        this.userNativePlace = (TextView)view.findViewById(R.id.user_nativePlace);
        this.userSupervisor = (TextView)view.findViewById(R.id.user_supervisor);
        this.userPoliticalStatus = (TextView)view.findViewById(R.id.user_politicalStatus);
        this.userType = (TextView)view.findViewById(R.id.user_stuType);
        this.userCredentials = (TextView)view.findViewById(R.id.user_credentials);
        this.userCredentialsId = (TextView)view.findViewById(R.id.user_credentialsId);
        this.userBirthday = (TextView)view.findViewById(R.id.user_birthday);
        this.userEnterSchoolDate = (TextView)view.findViewById(R.id.user_enterSchoolDate);
        this.userExamId = (TextView)view.findViewById(R.id.user_stuExamId);
        this.userStuId = (TextView)view.findViewById(R.id.user_stuId);
        this.userDepartment = (TextView)view.findViewById(R.id.user_department);
        this.userMajor = (TextView)view.findViewById(R.id.user_major);
        this.userResearchArea = (TextView)view.findViewById(R.id.user_researchArea);

    }
}
