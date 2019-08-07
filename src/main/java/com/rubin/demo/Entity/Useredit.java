package com.rubin.demo.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Useredit {
    private String eid;
    private String e_rid;
    private String ename;
    private String ecate;
    private String eimg;
    private String eloc;
    private String edetail;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date edate;

    private String e_stat;

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getE_rid() {
        return e_rid;
    }

    public void setE_rid(String e_rid) {
        this.e_rid = e_rid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEcate() {
        return ecate;
    }

    public void setEcate(String ecate) {
        this.ecate = ecate;
    }

    public String getEimg() {
        return eimg;
    }

    public void setEimg(String eimg) {
        this.eimg = eimg;
    }

    public String getEloc() {
        return eloc;
    }

    public void setEloc(String eloc) {
        this.eloc = eloc;
    }

    public String getEdetail() {
        return edetail;
    }

    public void setEdetail(String edetail) {
        this.edetail = edetail;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }


    public String getE_stat() {
        return e_stat;
    }

    public void setE_stat(String e_stat) {
        this.e_stat = e_stat;
    }
}

