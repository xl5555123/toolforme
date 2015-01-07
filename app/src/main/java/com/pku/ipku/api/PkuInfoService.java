package com.pku.ipku.api;

import com.pku.ipku.model.pkuInfo.dto.PkuLectureDTO;

import java.util.List;

/**
 * Created by XingLiang on 2015/1/6.
 */
public interface PkuInfoService {

    public List<PkuLectureDTO> getPkuLectures();
}
