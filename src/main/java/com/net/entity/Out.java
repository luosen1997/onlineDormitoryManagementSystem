package com.net.entity;

public class Out {
    private int  id;
    private String name;//用户名

    private String dormname;//宿舍名称


    private String dorm_towername;//宿舍楼名称
    private String create_time;//时间

    public Out() {
    }

    public Out(int id, String name, String dormname, String dorm_towername, String create_time) {
        this.id = id;
        this.name = name;
        this.dormname = dormname;
        this.dorm_towername = dorm_towername;
        this.create_time = create_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getDormname() {
        return dormname;
    }

    public void setDormname(String dormname) {
        this.dormname = dormname;
    }


    public String getDorm_towername() {
        return dorm_towername;
    }

    public void setDorm_towername(String dorm_towername) {
        this.dorm_towername = dorm_towername;
    }
}
