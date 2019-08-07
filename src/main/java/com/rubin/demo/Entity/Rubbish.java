package com.rubin.demo.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Rubbish {
    private Integer rubid;
    private String rubname;
    private String rubcate;
    private String rubimg;
    private String rubloc;
    private String rubdetail;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date adddate;

    public Integer getRubid() {
        return rubid;
    }

    public void setRubid(Integer rubid) {
        this.rubid = rubid;
    }

    public String getRubname() {
        return rubname;
    }

    public void setRubname(String rubname) {
        this.rubname = rubname;
    }

    public String getRubcate() {
        return rubcate;
    }

    public void setRubcate(String rubcate) {
        this.rubcate = rubcate;
    }

    public String getRubimg() {
        return rubimg;
    }

    public void setRubimg(String rubimg) {
        this.rubimg = rubimg;
    }

    public String getRubloc() {
        return rubloc;
    }

    public void setRubloc(String rubloc) {
        this.rubloc = rubloc;
    }

    public String getRubdetail() {
        return rubdetail;
    }

    public void setRubdetail(String rubdetail) {
        this.rubdetail = rubdetail;
    }

    public Date getAdddate() {
        return adddate;
    }

    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }
}
