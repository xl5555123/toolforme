package com.pku.ipku.model.pkuInfo;

import android.support.v4.app.Fragment;

import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.ui.pkuInfo.PkuJobFragment;
import com.pku.ipku.ui.pkuInfo.PkuPublicInfoFragment;

/**
 * Created by XingLiang on 2014/12/16.
 */
public class PkuJob implements Fragmentable {
    @Override
    public Fragment getAttachedFragment(String[] args) {
        return PkuPublicInfoFragment.newInstance(PkuInfoType.PKU_CAREER);
    }

    @Override
    public Integer getMenuIcon() {
        return null;
    }

    @Override
    public String getType() {
        return "pku_job";
    }

    @Override
    public String getChineseName() {
        return "招聘信息";
    }
}
