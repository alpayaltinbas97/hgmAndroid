package com.hgm.haritagenelmudurlugu.Pages;

import com.hgm.haritagenelmudurlugu.Model.BilimModel;

import java.util.List;

public class BilimSonuc {
    public List<BilimModel> getScience() {
        return bilimkurulu;
    }

    public void setScience(List<BilimModel> bilimkurulu) {
        this.bilimkurulu = bilimkurulu;
    }

    private List<BilimModel> bilimkurulu;
}
