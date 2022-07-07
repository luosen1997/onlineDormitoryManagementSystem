package com.net.entity;

import java.util.Date;

public class Users {
    private Integer id;

    private String name;

    private String pwd;

    private String role;//角色

    private String nickname;

    private String tel;

    private String email;

    private Integer sex;

    private Date create_time;

    private Integer fid;

    private String academy;//院校

    private String class_grade;//班级

    private String birthplace;//生源地

    private Integer smoke;//1:吸烟，2：不吸烟

    private String photo;//照片

    private String dormname;//宿舍名称
    private Integer dormId;//宿舍id
    private String dormnumber;//宿舍号
    private String dorm_towernumber;//宿舍楼号
    private Integer dorm_towerId;//宿舍楼id
    private String dorm_towername;//宿舍楼名称
    private Integer dorm_userId;//关联表id
    private String usId;
    private String otherId;
    private String agree;

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }

    public String getUsId() {
        return usId;
    }

    public void setUsId(String usId) {
        this.usId = usId;
    }

    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }

    public Integer getDorm_userId() {
        return dorm_userId;
    }

    public void setDorm_userId(Integer dorm_userId) {
        this.dorm_userId = dorm_userId;
    }

    public String getDormname() {
        return dormname;
    }

    public void setDormname(String dormname) {
        this.dormname = dormname;
    }

    public Integer getDormId() {
        return dormId;
    }

    public void setDormId(Integer dormId) {
        this.dormId = dormId;
    }

    public String getDormnumber() {
        return dormnumber;
    }

    public void setDormnumber(String dormnumber) {
        this.dormnumber = dormnumber;
    }

    public String getDorm_towernumber() {
        return dorm_towernumber;
    }

    public void setDorm_towernumber(String dorm_towernumber) {
        this.dorm_towernumber = dorm_towernumber;
    }

    public Integer getDorm_towerId() {
        return dorm_towerId;
    }

    public void setDorm_towerId(Integer dorm_towerId) {
        this.dorm_towerId = dorm_towerId;
    }

    public String getDorm_towername() {
        return dorm_towername;
    }

    public void setDorm_towername(String dorm_towername) {
        this.dorm_towername = dorm_towername;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getClass_grade() {
        return class_grade;
    }

    public void setClass_grade(String class_grade) {
        this.class_grade = class_grade;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public Integer getSmoke() {
        return smoke;
    }

    public void setSmoke(Integer smoke) {
        this.smoke = smoke;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}