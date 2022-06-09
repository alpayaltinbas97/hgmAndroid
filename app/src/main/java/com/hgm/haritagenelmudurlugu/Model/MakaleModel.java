package com.hgm.haritagenelmudurlugu.Model;

public class MakaleModel {
    private String value1;
    private String year;
    private String issue;
    private String name;
    private String id;
    private String value2;




    public MakaleModel(String value1,String year,String issue, String name, String id, String value2) {
        this.value1 = value1;
        this.value2 = value2;
        this.year = year;
        this.issue = issue;
        this.name = name;
        this.id = id;
    }

    public MakaleModel() {

    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
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

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }
}
