package com.hgm.haritagenelmudurlugu.Model;

public class BilimModel {
    private String unvan;
    private String ad;
    private String kurum;
    private String bransi;

    public BilimModel(String unvan, String ad, String kurum, String bransi) {
        this.unvan = unvan;
        this.ad = ad;
        this.kurum = kurum;
        this.bransi = bransi;
    }

    public BilimModel() {

    }

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getKurum() {
        return kurum;
    }

    public void setKurum(String kurum) {
        this.kurum = kurum;
    }

    public String getBransi() {
        return bransi;
    }

    public void setBransi(String bransi) {
        this.bransi = bransi;
    }
}
