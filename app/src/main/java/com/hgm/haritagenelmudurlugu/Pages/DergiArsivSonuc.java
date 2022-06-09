package com.hgm.haritagenelmudurlugu.Pages;

import com.hgm.haritagenelmudurlugu.Model.DergiArsivModel;
import com.hgm.haritagenelmudurlugu.Model.MakaleModel;

import java.util.List;

public class DergiArsivSonuc {
    public List<DergiArsivModel> getArchives() {
        return languages;
    }

    public void setArchives(List<DergiArsivModel> languages) {
        this.languages = languages;
    }

    private List<DergiArsivModel> languages;
}
