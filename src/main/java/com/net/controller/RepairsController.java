package com.net.controller;

import com.net.entity.Repairs;
import com.net.entity.Users;
import com.net.mapper.RepairsMapper;
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
@RequestMapping("repairs")
public class RepairsController {

    @Autowired
    PageBean page;
    @Autowired
    RepairsMapper repairsMapper;

    @RequestMapping("/list")
    public String selRepairs(String cause, String article, String begin_date, String end_date,String state,
                            String pageIndex, Map<String,Object> map, HttpSession session){
        try{
            Users admin =  (Users) session.getAttribute("user_power");
            map.put("users",admin);
            String user_id="";
            if(admin.getRole().equals("2")){
                user_id=admin.getId().toString();
            }
            map.put("cause",cause);
            map.put("article",article);
            map.put("state",state);
            map.put("user_id",user_id);
            map.put("begin_date", begin_date);
            map.put("end_date", end_date);
            int count = repairsMapper.selAllRepairs(map).size();
            page=page.getPageBean(pageIndex, count);
            map.put("begin", page.getStartRow());
            map.put("last", page.getEndRow());
            List<Repairs> repairsList =repairsMapper.selAllRepairs(map);
            map.put("repairsList", repairsList);
            map.put("pageBean", page);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "repairs/repairs-list";
    }

    @RequestMapping("/listpart")
    @ResponseBody
    public Object listpart(String article,String pageIndex, HttpSession session){
        Map<String,Object> map=new HashMap<>();
        try{
            map.put("article",article);
            int count = repairsMapper.selAllRepairs(map).size();
            page=page.getPageBean(pageIndex, count);
            map.put("begin", page.getStartRow());
            map.put("last", page.getEndRow());
            List<Repairs> repairsList =repairsMapper.selAllRepairs(map);
            map.put("repairsList", repairsList);
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
    @RequestMapping("/addRepairsPage")
    public String addRepairsPage(){
        return "repairs/repairs-add";
    }

    /**
     * 添加
     * @param repairs
     * @return
     */
    @RequestMapping("/addRepairs")
    @ResponseBody
    public Object addRepairs(Repairs repairs,HttpSession session){
        Map<String,Object> map=new HashMap<>();
        try{
            Users admin =  (Users) session.getAttribute("user_power");
            repairs.setUser_id(admin.getId());
            int res=repairsMapper.addRepairs(repairs);
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


    /**
     * 跳转编辑页面并回显本条数据
     * @param id
     * @return
     */
    @RequestMapping("/updRepairsPage")
    public String updRepairsPage(Map<String,Object> map,int id){
        Repairs no = repairsMapper.selRepairsId(id);
        System.out.println(no+"---------------------------------------------");
        map.put("repairs",no);
        return "repairs/repairs-edit";
    }

    /**
     * 编辑
     * @param repairs
     * @return
     */
    @RequestMapping("/updRepairs")
    @ResponseBody
    public Object updRepairs( Repairs repairs){
        Map<String,Object> map=new HashMap<>();
        try{
            int res=repairsMapper.updRepairs(repairs);
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

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delRepairs")
    @ResponseBody
    public Object delRepairs( int id){
        Map<String,Object> map=new HashMap<>();
        try{
            int res=repairsMapper.delRepairs(id);
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
