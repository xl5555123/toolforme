package com.pku.ipku.model.pkuInfo.dto;

/**
 * Created by XingLiang on 2015/1/26.
 */
public class PkuPublicInfo {

    private String title;
    private String link;
    private String pubDate;

    public PkuPublicInfo() {

    }

    public PkuPublicInfo(String title, String link, String pubDate) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
    }

    public static PkuPublicInfo mock() {
        return new PkuPublicInfo("央行宣布对称降息0.25个百分点", "http://finance.sina.com.cn/china/20150228/175421614803.shtml", "2015年02月28日 17:54");
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}
