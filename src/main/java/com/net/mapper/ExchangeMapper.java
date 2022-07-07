package com.net.mapper;

import com.net.entity.Exchange;

import java.util.List;
import java.util.Map;

public interface ExchangeMapper {
    List<Exchange> selExchange(Map<String, Object> map);
    int delExchange(int id);
    int updExchange(Exchange exchange);
    int addExchange(Exchange exchange);
    int delExchangeByUsId(int usId);
    int delExchangeByOtherId(int otherId);
}
