package com.net.mapper;

import com.net.entity.Repairs;

import java.util.List;
import java.util.Map;

public interface RepairsMapper {


    List<Repairs> selAllRepairs(Map<String, Object> map);

    int addRepairs(Repairs repairs);

    int updRepairs(Repairs repairs);

    int delRepairs(Integer id);

    Repairs selRepairsId(Integer id);
}
