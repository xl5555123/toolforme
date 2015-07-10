package com.xingliang.toolforme.model;

import com.xingliang.toolforme.model.pkuInfo.dto.PkuPublicInfo;

/**
 * Created by XingLiang on 2015/4/17.
 */
public class PubInfo extends PkuPublicInfo {

    private boolean collected;

    public PubInfo() {
        super();
        collected = false;
    }

    public PubInfo(PkuPublicInfo pkuPublicInfo, boolean collected) {
        setLink(pkuPublicInfo.getLink());
        setTitle(pkuPublicInfo.getTitle());
        setPubDate(pkuPublicInfo.getPubDate());
        this.collected = collected;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }
}
