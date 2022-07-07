package com.net.mapper;


import com.net.entity.Sign;

import java.util.List;
import java.util.Map;

public interface SignMapper {
    List<Sign> selAllSign(Map<String, Object> map);

    int addSign(Sign notice);

/*    int updSign(Sign notice);*/

    int delSign(Integer id);
    int delSignByUsersId(Integer usersId);

    Sign selSignId(Integer id);

    Sign selSignUsersId(Integer usersId);
}
