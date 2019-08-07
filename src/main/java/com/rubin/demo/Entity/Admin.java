package com.rubin.demo.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Admin {
    private Integer admid;
    private String admname;
    private String admpass;
    @DateTimeFormat(pattern="yyy-MM-dd")
    private Date admdate;

    public Integer getAdmid() {
        return admid;
    }

    public void setAdmid(Integer admid) {
        this.admid = admid;
    }

    public String getAdmname() {
        return admname;
    }

    public void setAdmname(String admname) {
        this.admname = admname;
    }

    public String getAdmpass() {
        return admpass;
    }

    public void setAdmpass(String admpass) {
        this.admpass = admpass;
    }

    public Date getAdmdate() {
        return admdate;
    }

    public void setAdmdate(Date admdate) {
        this.admdate = admdate;
    }
}
