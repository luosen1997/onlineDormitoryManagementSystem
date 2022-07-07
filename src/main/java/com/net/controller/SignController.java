package com.net.controller;


import com.net.entity.Sign;
import com.net.entity.Users;
import com.net.mapper.SignMapper;
import com.net.utils.PageBean;
import com.net.utils.RETINFO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("sign")
public class SignController {

    @Autowired
    PageBean page;
    @Autowired
    SignMapper signMapper;

    @RequestMapping("/list")
    public String selSign( String begin_date,
                            String pageIndex, Map<String,Object> map, HttpSession session){
        try{
            Users users=(Users)session.getAttribute("user_power");
            if(users.getRole().equals("2")) {
                map.put("usersId", users.getId());
            }else {
                map.put("usersId", "");
            }
            map.put("begin_date", begin_date);
            int count = signMapper.selAllSign(map).size();
            page=page.getPageBean(pageIndex, count);
            map.put("begin", page.getStartRow());
            map.put("last", page.getEndRow());
            List<Sign> signList =signMapper.selAllSign(map);
            map.put("users",users);
            map.put("signList", signList);
            map.put("pageBean", page);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "sign/sign-list";
    }



    /**
     * 跳转添加页面
     * @return
     */
  /*  @RequestMapping("/addSignPage")
    public String addNoticePage(){
        return "notice/notice-add";
    }*/

    /**
     * 添加
     * @param sign
     * @return
     */
    @RequestMapping("/addSign")
    @ResponseBody
    public Object addSign(Sign sign, HttpSession session){
        Map<String,Object> map=new HashMap<>();
        Users users=(Users)session.getAttribute("user_power");
        try{
            sign.setUsersId(users.getId());
            Date date=new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String create_time=format.format(date);
            Sign si=signMapper.selSignUsersId(sign.getUsersId());
            if(si!=null) {
                if (create_time.equals(si.getCreate_time().substring(0, 10))) {
                    map.put(RETINFO.CODE, RETINFO.FAIL_CODE);
                    map.put(RETINFO.MSG, "今日已签到");
                    return map;
                } else {

                    int res = signMapper.addSign(sign);
                    if (res > 0) {
                        map.put(RETINFO.CODE, RETINFO.SUCCESS_CODE);
                        map.put(RETINFO.MSG, RETINFO.SUCCESS_MSG);
                        return map;
                    }
                }
            }else{
                int res = signMapper.addSign(sign);
                if (res > 0) {
                    map.put(RETINFO.CODE, RETINFO.SUCCESS_CODE);
                    map.put(RETINFO.MSG, RETINFO.SUCCESS_MSG);
                    return map;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
        map.put(RETINFO.MSG,"签到错误！");
        return map;
    }


    /**
     * 跳转编辑页面并回显本条数据
     * @param id
     * @return
     */
    @RequestMapping("/updSignPage")
    public String updSignPage(Map<String,Object> map,int id){
        Sign no = signMapper.selSignId(id);
        System.out.println(no+"---------------------------------------------");
        map.put("sign",no);
        return "sign/sign-edit";
    }



    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delSign")
    @ResponseBody
    public Object delSign( int id){
        Map<String,Object> map=new HashMap<>();
        try{
            int res=signMapper.delSign(id);
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
     * 通过id查询
     * @param id
     * @return
     */
    @RequestMapping("/selSignById")
    @ResponseBody
    public Object selSignById( int id){
        Map<String,Object> map=new HashMap<>();
        try{
            Sign sign=signMapper.selSignId(id);
            map.put("sign",sign);
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

}
