package com.pku.portal.model.person.dto;

/**
 * Created by pktxq on 15-1-22.
 */
public class ScoreDTO {

    public ScoreDTO() {

    }

    public ScoreDTO(String xnd, String xq, String kch, String kcmc, String kclb, String xf, String cj) {
        this.xnd = xnd;
        this.xq = xq;
        this.kch = kch;
        this.kcmc = kcmc;
        this.kclb = kclb;
        this.xf = xf;
        this.cj = cj;
    }

    public String getXnd() {
        return xnd;
    }

    public void setXnd(String xnd) {
        this.xnd = xnd;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getKch() {
        return kch;
    }

    public void setKch(String kch) {
        this.kch = kch;
    }

    public String getKcmc() {
        return kcmc;
    }

    public void setKcmc(String kcmc) {
        this.kcmc = kcmc;
    }

    public String getKclb() {
        return kclb;
    }

    public void setKclb(String kclb) {
        this.kclb = kclb;
    }

    public String getXf() {
        return xf;
    }

    public void setXf(String xf) {
        this.xf = xf;
    }

    public String getCj() {
        return cj;
    }

    public void setCj(String cj) {
        this.cj = cj;
    }

    private String xnd;
    private String xq;
    private String kch;
    private String kcmc;
    private String kclb;
    private String xf;
    private String cj;


}
