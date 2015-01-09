package com.pku.ipku.api.cache;

import com.pku.ipku.api.PkuInfoService;
import com.pku.ipku.api.mock.pkuInfo.MockPkuLectureList;
import com.pku.ipku.model.pkuInfo.dto.PkuLectureDTO;

import java.util.List;

/**
 * Created by XingLiang on 2015/1/6.
 */
public class PkuInfoServiceCacheImpl implements PkuInfoService {
    @Override
    public List<PkuLectureDTO> getPkuLectures() {
        return MockPkuLectureList.get();
    }
}
