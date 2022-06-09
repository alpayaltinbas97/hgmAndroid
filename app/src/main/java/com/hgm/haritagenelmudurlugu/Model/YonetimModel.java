package com.hgm.haritagenelmudurlugu.Model;

public class YonetimModel {
    private String gorev;
    private String rutbe;
    private String ad;

    public YonetimModel(String gorev,String rutbe, String ad) {
        this.gorev = gorev;
        this.rutbe = rutbe;
        this.ad = ad;

    }

    public YonetimModel() {

    }

    public String getGorev() {
        return gorev;
    }

    public void setGorev(String gorev) {
        this.gorev = gorev;
    }

    public String getRutbe() {
        return rutbe;
    }

    public void setRutbe(String rutbe) {
        this.rutbe = rutbe;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }
}
