package com.net.controller;


import com.net.entity.Dorm_usersId;
import com.net.entity.Exchange;
import com.net.entity.UserIdusId;
import com.net.entity.Users;
import com.net.mapper.DormMapper;
import com.net.mapper.ExchangeMapper;
import com.net.utils.PageBean;
import com.net.utils.RETINFO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("exchange")
public class ExchangeController {

    @Autowired
    ExchangeMapper exchangeMapper;
    @Autowired
    PageBean page;
    @Autowired
    DormMapper dormMapper;
    /**
     * 	调换管理
     * @param pageIndex  当前页码
     * @return 用户列表页码
     */
    @RequestMapping("/list")
    public String exchangeList(String pageIndex, String usId,
                           String begin_date, String end_date, Map<String,Object> map, HttpSession session){
        Users admin =  (Users) session.getAttribute("user_power");
        if(admin.getRole().equals("2")){
            usId=admin.getId().toString();
        } else if(admin.getRole().equals("0")){
            usId="";
        }
        map.put("users", admin);
        map.put("usId",usId);
        map.put("begin_date", begin_date);
        map.put("end_date", end_date);
        int count = exchangeMapper.selExchange(map).size();
        page=page.getPageBean(pageIndex, count);
        map.put("begin", page.getStartRow());
        map.put("last", page.getEndRow());
        List<Exchange> exchange = exchangeMapper.selExchange(map);
        map.put("list",exchange);
        map.put("pageBean", page);
        return "exchange/exchange-list";
    }

    /**
     * 是否同意,是否审核通过
     * @param exchange
     * @return
     */
    @RequestMapping("/updExchange")
    @ResponseBody
    public Object updExchange(Exchange exchange){
        Map<String,Object> map=new HashMap<>();
        try{
            if(exchange.getState()!=null && !exchange.getState().equals("")){
                if(exchange.getState()==1){
                    System.out.println("--------------------------=================================================================================================");
                    Dorm_usersId usId=dormMapper.findDorm_usersIdByUId(exchange.getUsId());
                    Dorm_usersId usersId=dormMapper.findDorm_usersIdByUId(exchange.getOtherId());
                    UserIdusId userIdusId=new UserIdusId();
                    UserIdusId userIdusIds=new UserIdusId();
                    userIdusId.setUsersId(usersId.getId());
                    userIdusId.setUsId(usId.getUsersId());
                    int ress=dormMapper.updDorm_usersIdByUId(userIdusId);
                    userIdusIds.setUsersId(usId.getId());
                    userIdusIds.setUsId(usersId.getUsersId());
                    int resss=dormMapper.updDorm_usersIdByUId(userIdusIds);
                }
            }
            int res=exchangeMapper.updExchange(exchange);
            if (res>0){
                map.put(RETINFO.CODE,RETINFO.SUCCESS_CODE);
                map.put(RETINFO.MSG,RETINFO.SUCCESS_MSG);
                return map;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
        map.put(RETINFO.MSG,RETINFO.FAIL_MSG);
        return map;
    }


}
