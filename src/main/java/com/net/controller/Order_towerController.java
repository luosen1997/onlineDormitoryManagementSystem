package com.net.controller;

import com.net.entity.Order_tower;
import com.net.entity.Users;
import com.net.mapper.Order_towerMapper;
import com.net.mapper.UsersMapper;
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
@RequestMapping("order_tower")
public class Order_towerController {

    @Autowired
    PageBean page;
    @Autowired
    Order_towerMapper order_towerMapper;
    @Autowired
    UsersMapper usersMapper;

    @RequestMapping("/list")
    public String selOrder_tower(String name, String number, String begin_date, String end_date,
                            String pageIndex, Map<String,Object> map, HttpSession session){
        try{
            Users admin =  (Users) session.getAttribute("user_power");
            map.put("users", admin);
            map.put("name",name);
            map.put("number",number);
            map.put("begin_date", begin_date);
            map.put("end_date", end_date);
            int count = order_towerMapper.selAllOrder_tower(map).size();
            page=page.getPageBean(pageIndex, count);
            map.put("begin", page.getStartRow());
            map.put("last", page.getEndRow());
            List<Order_tower> order_towerList =order_towerMapper.selAllOrder_tower(map);
            map.put("order_towerList", order_towerList);
            map.put("pageBean", page);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("访问崩溃");
        }

        return "dorm_tower/order_tower-list";
    }

    @RequestMapping("/listpart")
    @ResponseBody
    public Object listpart(String title,String pageIndex, HttpSession session){
        Map<String,Object> map=new HashMap<>();
        try{
            map.put("title",title);
            int count = order_towerMapper.selAllOrder_tower(map).size();
            page=page.getPageBean(pageIndex, count);
            map.put("begin", page.getStartRow());
            map.put("last", page.getEndRow());
            List<Order_tower> order_towerList =order_towerMapper.selAllOrder_tower(map);
            map.put("order_towerList", order_towerList);
            map.put("pageBean", page);
            map.put(RETINFO.CODE,RETINFO.SUCCESS_CODE);
            map.put(RETINFO.MSG,RETINFO.SUCCESS_MSG);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
        map.put(RETINFO.MSG,RETINFO.FAIL_MSG);
        return map;
    }

    /**
     * 跳转添加页面
     * @return
     */
    @RequestMapping("/addOrder_towerPage")
    public String addOrder_towerPage(Map<String,Object> map){
        List<Users> usersList= usersMapper.selAll();
        map.put("usersList",usersList);
        return "dorm_tower/order_tower-add";
    }

    /**
     * 添加
     * @param order_tower
     * @return
     */
    @RequestMapping("/addOrder_tower")
    @ResponseBody
    public Object addOrder_tower(Order_tower order_tower){
        Map<String,Object> map=new HashMap<>();
        try{
            Order_tower ot=order_towerMapper.selOrder_towerUserId(order_tower.getUserId());
            if(ot!=null) {
                map.put(RETINFO.CODE, RETINFO.FAIL_CODE);
                map.put(RETINFO.MSG, "该宿管已经分配宿舍楼！");
                return map;
            }else{
                List<Order_tower> o1 = order_towerMapper.selOrder_towerByName(order_tower.getName());
                if (o1.size()>0) {
                    map.put(RETINFO.CODE, RETINFO.FAIL_CODE);
                    map.put(RETINFO.MSG, "名称相同！");
                    return map;
                } else {
                    List<Order_tower> o2 = order_towerMapper.selOrder_towerByNumber(order_tower.getNumber());
                    if (o2.size()>0) {
                        map.put(RETINFO.CODE, RETINFO.FAIL_CODE);
                        map.put(RETINFO.MSG, "宿舍号相同！");
                        return map;
                    } else {
                        int res = order_towerMapper.addOrder_tower(order_tower);
                        if (res > 0) {
                            map.put(RETINFO.CODE, RETINFO.SUCCESS_CODE);
                            map.put(RETINFO.MSG, RETINFO.SUCCESS_MSG);
                            return map;
                        }
                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
        map.put(RETINFO.MSG,RETINFO.FAIL_MSG);
        return map;
    }


    /**
     * 跳转编辑页面并回显本条数据
     * @param id
     * @return
     */
    @RequestMapping("/updOrder_towerPage")
    public String updOrder_towerPage(Map<String,Object> map,int id){
        Order_tower no = order_towerMapper.selOrder_towerId(id);
        map.put("order_tower",no);
        return "dorm_tower/order_tower-edit";
    }

    /**
     * 编辑
     * @param order_tower
     * @return
     */
    @RequestMapping("/updOrder_tower")
    @ResponseBody
    public Object updOrder_tower( Order_tower order_tower){
        Map<String,Object> map=new HashMap<>();
        try{
            List<Order_tower> list1=order_towerMapper.selOrder_towerByName(order_tower.getName());
            if(list1.size()>1) {
                map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
                map.put(RETINFO.MSG,"名称重复！");
                return map;
            }else{
                List<Order_tower> list2=order_towerMapper.selOrder_towerByNumber(order_tower.getNumber());
                if(list2.size()>1){
                    map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
                    map.put(RETINFO.MSG,"宿舍号重复！");
                    return map;
                }else{
                    int res=order_towerMapper.updOrder_tower(order_tower);
                    if (res>0){
                        map.put(RETINFO.CODE,RETINFO.SUCCESS_CODE);
                        map.put(RETINFO.MSG,RETINFO.SUCCESS_MSG);
                        return map;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
        map.put(RETINFO.MSG,RETINFO.FAIL_MSG);
        return map;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delOrder_tower")
    @ResponseBody
    public Object delOrder_tower( int id){
        Map<String,Object> map=new HashMap<>();
        try{
            int res=order_towerMapper.delOrder_tower(id);
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
