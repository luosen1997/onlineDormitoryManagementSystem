package com.net.entity;

public class Dorm_usersId {
    private Integer id;
    private Integer dormId;
    private Integer usersId;
    private String dorm_towerId;

    public String getDorm_towerId() {
        return dorm_towerId;
    }

    public void setDorm_towerId(String dorm_towerId) {
        this.dorm_towerId = dorm_towerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDormId() {
        return dormId;
    }

    public void setDormId(Integer dormId) {
        this.dormId = dormId;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }
}
