package com.hgm.haritagenelmudurlugu.Model;

public class DergiArsivModel {
    private String value1;
    private String name;
    private String value2;
    private String id;


    public DergiArsivModel(String value1, String name, String value2,String id) {
        this.value1 = value1;
        this.name = name;
        this.value2 = value2;
        this.id=id;

    }

    public DergiArsivModel() {

    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
