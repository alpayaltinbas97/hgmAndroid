package com.hgm.haritagenelmudurlugu.Pages;

import com.hgm.haritagenelmudurlugu.Model.YonetimModel;

import java.util.List;

public class YonetimSonuc {
    public List<YonetimModel> getManagement() {
        return dergiyonetimkurulu;
    }

    public void setManagement(List<YonetimModel> dergiyonetimkurulu) {
        this.dergiyonetimkurulu = dergiyonetimkurulu;
    }

    private List<YonetimModel> dergiyonetimkurulu;
}
