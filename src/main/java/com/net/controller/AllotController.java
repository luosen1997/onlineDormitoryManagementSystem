package com.net.controller;

import com.net.entity.*;
import com.net.mapper.*;
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
@RequestMapping("allot")
public class AllotController {
    @Autowired
    UsersMapper usersMapper;
    @Autowired
    PageBean page;
    @Autowired
    Order_towerMapper order_towerMapper;
    @Autowired
    DormMapper dormMapper;
    @Autowired
    ExchangeMapper exchangeMapper;

    @Autowired
    OutMapper outMapper;
    @Autowired
    SignMapper signMapper;


    /**
     * 	学生列表
     * @param pageIndex  当前页码
     * @param like       关键字
     * @return 用户列表页码
     */
    @RequestMapping("/studentlist")
    public String userList(String pageIndex, String like, String sex,String dormname,String dorm_towername,String smoke,String dormId,
                           String begin_date, String end_date, Map<String,Object> map, HttpSession session){
        Users admin =  (Users) session.getAttribute("user_power");
        map.put("users", admin);
        map.put("like",like);
        map.put("role",2);
        map.put("sex",sex);
        map.put("dormname",dormname);
        map.put("dorm_towername",dorm_towername);
        map.put("smoke",smoke);
        map.put("dormId",dormId);
        System.out.println(dormname+"===================================");
        map.put("begin_date", begin_date);
        map.put("end_date", end_date);
        int count = usersMapper.selAllFind(map).size();
        page=page.getPageBean(pageIndex, count);
        map.put("begin", page.getStartRow());
        map.put("last", page.getEndRow());
        List<Users> users = usersMapper.selAllFind(map);
        map.put("list",users);
        map.put("pageBean", page);
        return "student/student-list";
    }

    /**
     * 跳转修改页面
     * @param map
     * @return
     */
    @RequestMapping("/toddedit")
    public String toddedit(Map<String,Object> map,Integer usersId){
        List<Order_tower> order_towerList=order_towerMapper.findOrder_tower();
        map.put("usersId",usersId);
        map.put("order_towerList",order_towerList);
        return "student/student-edit";
    }

    /**
     * 给学生分配宿舍
     * @param dorm_usersId
     * @return
     */
    @RequestMapping("/editinsert")
    @ResponseBody
    public Object insert(Dorm_usersId dorm_usersId){
        Map<String,Object> map=new HashMap<>();
        List<Dorm_usersId> list=dormMapper.findDorm_usersId(dorm_usersId.getDormId());
        Users u=usersMapper.selectByPrimaryKey(dorm_usersId.getUsersId());
        Dorm dorm=dormMapper.selDormId(dorm_usersId.getDormId());
        if(u.getSmoke()!=null && !u.getSmoke().equals("")) {
            if ( list != null && list.size() >= dorm.getPeople_count()) {
                map.put(RETINFO.CODE, RETINFO.FAIL_CODE);
                map.put(RETINFO.MSG, "该宿舍人员已满！");
                return map;
            } else {
                int res = dormMapper.insertDorm_usersId(dorm_usersId);
                if (res > 0) {
                    map.put(RETINFO.CODE, RETINFO.SUCCESS_CODE);
                    map.put(RETINFO.MSG, RETINFO.SUCCESS_MSG);
                    return map;
                }
            }
        }else{
            map.put(RETINFO.CODE, RETINFO.FAIL_CODE);
            map.put(RETINFO.MSG, "该学生信息不全！");
            return map;
        }
        map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
        map.put(RETINFO.MSG,RETINFO.FAIL_MSG);
        return map;
    }

    /**
     * 通过宿舍楼id查询宿舍
     * @param id
     * @return
     */
    @RequestMapping("/findDorm")
    @ResponseBody
    public Object FindDorm(int id){
        Map<String,Object> map=new HashMap<>();
        System.out.println(id+"===================================");
        List<Dorm> dormList=dormMapper.findDormByDorm_towerId(id);
        if(dormList!=null && dormList.size()>0) {
            map.put("dormList", dormList);
            map.put(RETINFO.CODE,RETINFO.SUCCESS_CODE);
            map.put(RETINFO.MSG,RETINFO.SUCCESS_MSG);
            return map;
        }else{
            map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
            map.put(RETINFO.MSG,"该宿舍楼没有分配宿舍！！");
            return map;
        }

    }


    /**
     * 取消学生宿舍
     * @param id
     * @return
     */
    @RequestMapping("/delDorm_usersId")
    @ResponseBody
    public Object delDorm_usersId(int id){
        Map<String,Object> map=new HashMap<>();
        int res=dormMapper.delDorm_usersId(id);
        if(res>0){
            map.put(RETINFO.CODE,RETINFO.SUCCESS_CODE);
            map.put(RETINFO.MSG,RETINFO.SUCCESS_MSG);
            return map;
        }
        map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
        map.put(RETINFO.MSG,RETINFO.FAIL_MSG);
        return map;
    }

    /**
     * 调换宿舍
     * @param
     * @return
     */
    @RequestMapping("/changingdormitory")
    @ResponseBody
    public Object Changingdormitory(Exchange exchange,HttpSession session){
        Map<String,Object> map=new HashMap<>();
        Map<String,Object> map1=new HashMap<>();
        Users admin =  (Users) session.getAttribute("user_power");
        map.put("role",2);
        map.put("id",admin.getId());
        map1.put("role",2);
        map1.put("id",exchange.getOtherId());
        List<Users> users = usersMapper.selAllFind(map);
        List<Users> userss = usersMapper.selAllFind(map);
        if(users.size()>0 && userss.size()>0) {
            for (Users u : users) {
                for(Users uu:userss){
                if (u.getDormId() != null && uu.getDormId() != null) {
                    exchange.setUsId(admin.getId());
                    int res = exchangeMapper.addExchange(exchange);
                    if (res > 0) {
                        map.put(RETINFO.CODE, RETINFO.SUCCESS_CODE);
                        map.put(RETINFO.MSG, RETINFO.SUCCESS_MSG);
                        return map;
                    }
                } else {
                    map.put(RETINFO.CODE, RETINFO.FAIL_CODE);
                    map.put(RETINFO.MSG, "还未分配宿舍");
                    return map;
                }
                }
            }
        }else{
            map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
            map.put(RETINFO.MSG,"您不是学生");
            return map;
        }
        map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
        map.put(RETINFO.MSG,RETINFO.FAIL_MSG);
        return map;
    }
    /**
     * 删除
     * 迁出学生 删除关联数据
     * @param id
     * @return
     */
    @RequestMapping("/delOut")
    @ResponseBody
    public Object delOut( int id){
        Map<String,Object> map=new HashMap<>();
        try{
            map.put("id",id);
                   List<Users> list= usersMapper.selAllFind(map);
                    for(Users u :list){
                     Out out =   new Out();
                     out.setName(u.getName());
                     out.setDormname(u.getDormname());
                     out.setDorm_towername(u.getDorm_towername());
                        System.out.println(u.getDorm_towername());
                     outMapper.addOut(out);
        }
            int res=usersMapper.deleteByPrimaryKey(id);

            if (res>0){
                dormMapper.delDorm_usersByUsersId(id);
                signMapper.delSignByUsersId(id);
                exchangeMapper.delExchangeByOtherId(id);
                exchangeMapper.delExchangeByUsId(id);
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
