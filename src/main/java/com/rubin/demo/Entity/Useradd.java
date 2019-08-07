package com.rubin.demo.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Useradd {
    private Integer add_id;
    private String add_name;
    private String add_cate;
    private String add_img;
    private String add_loc;
    private String add_detail;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date add_date;

    private String add_stat;

    public Integer getAdd_id() {
        return add_id;
    }

    public void setAdd_id(Integer add_id) {
        this.add_id = add_id;
    }

    public String getAdd_name() {
        return add_name;
    }

    public void setAdd_name(String add_name) {
        this.add_name = add_name;
    }

    public String getAdd_cate() {
        return add_cate;
    }

    public void setAdd_cate(String add_cate) {
        this.add_cate = add_cate;
    }

    public String getAdd_img() {
        return add_img;
    }

    public void setAdd_img(String add_img) {
        this.add_img = add_img;
    }

    public String getAdd_loc() {
        return add_loc;
    }

    public void setAdd_loc(String add_loc) {
        this.add_loc = add_loc;
    }

    public String getAdd_detail() {
        return add_detail;
    }

    public void setAdd_detail(String add_detail) {
        this.add_detail = add_detail;
    }

    public Date getAdd_date() {
        return add_date;
    }

    public void setAdd_date(Date add_date) {
        this.add_date = add_date;
    }



    public String getAdd_stat() {
        return add_stat;
    }

    public void setAdd_stat(String add_stat) {
        this.add_stat = add_stat;
    }
}

