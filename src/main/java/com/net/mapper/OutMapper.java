package com.net.mapper;


import com.net.entity.Out;

import java.util.List;
import java.util.Map;

public interface OutMapper {

    List<Out> selAllOut(Map<String, Object> map);
    int addOut(Out out);

    int delOut(int id);


}
