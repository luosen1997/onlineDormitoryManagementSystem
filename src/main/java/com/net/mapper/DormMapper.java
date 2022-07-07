package com.net.mapper;

import com.net.entity.Dorm;
import com.net.entity.Dorm_usersId;
import com.net.entity.UserIdusId;

import java.util.List;
import java.util.Map;

public interface DormMapper {

    List<Dorm> selAllDorm(Map<String, Object> map);

    int addDorm(Dorm dorm);

    int updDorm(Dorm dorm);

    int delDorm(Integer id);

    Dorm selDormId(Integer id);
    List<Dorm> selDormByName(String name);
    List<Dorm> selDormByNumber(String number);
    List<Dorm> findDormByDorm_towerId(int dorm_towerId);

    int insertDorm_usersId(Dorm_usersId dorm_usersId);

    List<Dorm_usersId> findDorm_usersId(Integer dormId);

    int delDorm_usersId(Integer id);
    int delDorm_usersByUsersId(Integer usersId);

    Dorm_usersId findDorm_usersIdByUId(int usersId);
    int updDorm_usersIdByUId(UserIdusId userIdusId);
}
