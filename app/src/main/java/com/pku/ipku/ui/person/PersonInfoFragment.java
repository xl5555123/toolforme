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
import com.pku.ipku.dto.StuInfoDTO;

import org.w3c.dom.Text;

public class PersonInfoFragment extends Fragment {
    StuInfoDTO stuInfo = new StuInfoDTO("孙悟空","男","汉","花果山水帘洞","唐僧","群众","双证","身份证","111233222333322234","1111-11-11","2222-2-22","21325352154321523","2341231234",
            "大唐佛学院","降妖除魔","大惊刚经");
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
        setView(view);
        return view;
    }

    public void setView(View view){
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
        userDepartment.setText(stuInfo.getDepartment());
        userMajor.setText(stuInfo.getMajor());
        userResearchArea.setText(stuInfo.getResearchArea());

    }
}
