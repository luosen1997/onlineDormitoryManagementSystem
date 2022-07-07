package com.net.controller;


import com.net.entity.Dorm;
import com.net.entity.Order_tower;
import com.net.entity.Users;
import com.net.mapper.DormMapper;
import com.net.mapper.Order_towerMapper;
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
@RequestMapping("dorm")
public class DormController {

    @Autowired
    PageBean page;
    @Autowired
    DormMapper dormMapper;
    @Autowired
    Order_towerMapper order_towerMapper;

    @RequestMapping("/list")
    public String selOrder_tower(String name, String number, String begin_date, String end_date,String dorm_towerId,
                                 String pageIndex, Map<String,Object> map, HttpSession session){
        try{
            Users admin =  (Users) session.getAttribute("user_power");
            map.put("users", admin);
            map.put("name",name);
            map.put("number",number);
            map.put("dorm_towerId",dorm_towerId);
            map.put("begin_date", begin_date);
            map.put("end_date", end_date);
            int count = dormMapper.selAllDorm(map).size();
            page=page.getPageBean(pageIndex, count);
            map.put("begin", page.getStartRow());
            map.put("last", page.getEndRow());
            List<Dorm> dormList =dormMapper.selAllDorm(map);
            List<Order_tower> order_towerList=order_towerMapper.findOrder_tower();
            map.put("order_towerList",order_towerList);
            map.put("dormList", dormList);
            map.put("pageBean", page);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "dorm/dorm-list";
    }

    @RequestMapping("/listpart")
    @ResponseBody
    public Object listpart(String title,String pageIndex, HttpSession session){
        Map<String,Object> map=new HashMap<>();
        try{
            map.put("title",title);
            int count = dormMapper.selAllDorm(map).size();
            page=page.getPageBean(pageIndex, count);
            map.put("begin", page.getStartRow());
            map.put("last", page.getEndRow());
            List<Dorm> dormList =dormMapper.selAllDorm(map);
            List<Order_tower> order_towerList=order_towerMapper.findOrder_tower();
            map.put("order_towerList",order_towerList);
            map.put("dormList", dormList);
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
    @RequestMapping("/addDormPage")
    public String addDormPage(Map<String,Object> map){
        List<Order_tower> order_towerList=order_towerMapper.findOrder_tower();
        map.put("order_towerList",order_towerList);
        return "dorm/dorm-add";
    }

    /**
     * 添加
     * @param dorm
     * @return
     */
    @RequestMapping("/addDorm")
    @ResponseBody
    public Object addDorm(Dorm dorm){
        Map<String,Object> map=new HashMap<>();
        try{
            List<Dorm> o1=dormMapper.selDormByName(dorm.getName());
            if(o1.size()>0) {
                map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
                map.put(RETINFO.MSG,"名称相同！");
                return map;
            }else{
                List<Dorm> o2=dormMapper.selDormByNumber(dorm.getNumber());
                if(o2.size()>0){
                    map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
                    map.put(RETINFO.MSG,"宿舍号相同！");
                    return map;
                }else{
                    int res = dormMapper.addDorm(dorm);
                    if (res > 0) {
                        map.put(RETINFO.CODE, RETINFO.SUCCESS_CODE);
                        map.put(RETINFO.MSG, RETINFO.SUCCESS_MSG);
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
     * 跳转编辑页面并回显本条数据
     * @param id
     * @return
     */
    @RequestMapping("/updDormPage")
    public String updDormPage(Map<String,Object> map,int id){
        Dorm no = dormMapper.selDormId(id);
        List<Order_tower> order_towerList=order_towerMapper.findOrder_tower();
        map.put("order_towerList",order_towerList);
        map.put("dorm",no);
        return "dorm/dorm-edit";
    }

    /**
     * 编辑
     * @param dorm
     * @return
     */
    @RequestMapping("/updDorm")
    @ResponseBody
    public Object updDorm(Dorm dorm){
        Map<String,Object> map=new HashMap<>();
        try{
            List<Dorm> list1=dormMapper.selDormByName(dorm.getName());
            if(list1.size()>1) {
                map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
                map.put(RETINFO.MSG,"名称重复！");
                return map;
            }else{
                List<Dorm> list2=dormMapper.selDormByNumber(dorm.getNumber());
                if(list2.size()>1){
                    map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
                    map.put(RETINFO.MSG,"宿舍号重复！");
                    return map;
                }else{
                    int res=dormMapper.updDorm(dorm);
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
    @RequestMapping("/delDorm")
    @ResponseBody
    public Object delDorm( int id){
        Map<String,Object> map=new HashMap<>();
        try{
            int res=dormMapper.delDorm(id);
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
