package com.net.entity;

public class Dorm {
    private Integer id;
    private String name;
    private String number;
    private String create_time;
    private Integer people_count;
    private Integer dorm_towerId;
    private String score;
    private String dorm_towername;

    public String getDorm_towername() {
        return dorm_towername;
    }

    public void setDorm_towername(String dorm_towername) {
        this.dorm_towername = dorm_towername;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Integer getPeople_count() {
        return people_count;
    }

    public void setPeople_count(Integer people_count) {
        this.people_count = people_count;
    }

    public Integer getDorm_towerId() {
        return dorm_towerId;
    }

    public void setDorm_towerId(Integer dorm_towerId) {
        this.dorm_towerId = dorm_towerId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
