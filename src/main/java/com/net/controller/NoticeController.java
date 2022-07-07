package com.net.controller;

import com.net.entity.Notice;
import com.net.entity.Users;
import com.net.mapper.NoticeMapper;
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

/**
 * 公告页面
 */
@Controller
@RequestMapping("notice")
public class NoticeController {

    @Autowired
    PageBean page;
    @Autowired
    NoticeMapper noticeMapper;

    @RequestMapping("/list")
    public String selNotice(String title, String content, String begin_date, String end_date,
                            String pageIndex, Map<String,Object> map, HttpSession session){
        try{
            Users admin =  (Users) session.getAttribute("user_power");
            map.put("user", admin);
            map.put("title",title);
            map.put("content",content);
            map.put("begin_date", begin_date);
            map.put("end_date", end_date);
            int count = noticeMapper.selAllNotice(map).size();
            page=page.getPageBean(pageIndex, count);
            map.put("begin", page.getStartRow());
            map.put("last", page.getEndRow());
            List<Notice> noticeList =noticeMapper.selAllNotice(map);
            map.put("noticeList", noticeList);
            map.put("pageBean", page);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "notice/notice-list";
    }

    @RequestMapping("/listpart")
    @ResponseBody
    public Object listpart(String title,String pageIndex, HttpSession session){
        Map<String,Object> map=new HashMap<>();
        try{
            map.put("title",title);
            int count = noticeMapper.selAllNotice(map).size();
            page=page.getPageBean(pageIndex, count);
            map.put("begin", page.getStartRow());
            map.put("last", page.getEndRow());
            List<Notice> noticeList =noticeMapper.selAllNotice(map);
            map.put("noticeList", noticeList);
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
    @RequestMapping("/addNoticePage")
    public String addNoticePage(){
        return "notice/notice-add";
    }

    /**
     * 添加
     * @param notice
     * @return
     */
    @RequestMapping("/addNotice")
    @ResponseBody
    public Object addNotice(Notice notice){
        Map<String,Object> map=new HashMap<>();
        try{
            int res=noticeMapper.addNotice(notice);
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
    @RequestMapping("/updNoticePage")
    public String updNoticePage(Map<String,Object> map,int id){
        Notice no = noticeMapper.selNoticeId(id);
        System.out.println(no+"---------------------------------------------");
        map.put("notice",no);
        return "notice/notice-edit";
    }

    /**
     * 编辑
     * @param notice
     * @return
     */
    @RequestMapping("/updNotice")
    @ResponseBody
    public Object updNotice( Notice notice){
        Map<String,Object> map=new HashMap<>();
        try{
            int res=noticeMapper.updNotice(notice);
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
    @RequestMapping("/delNotice")
    @ResponseBody
    public Object delNotice( int id){
        Map<String,Object> map=new HashMap<>();
        try{
            int res=noticeMapper.delNotice(id);
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


    //获取前台公告最新5条数据
    @RequestMapping("/selNoticeByFive")
    @ResponseBody
    public Object selNoticeByFive(){
        Map<String,Object> map = new HashMap<>();
        try{
            List<Notice> typeList = noticeMapper.selNoticeByFive();
            map.put(RETINFO.CODE,RETINFO.SUCCESS_CODE);
            map.put(RETINFO.MSG,RETINFO.SUCCESS_MSG);
            map.put("noticelist",typeList);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
        map.put(RETINFO.MSG,RETINFO.FAIL_MSG);
        return map;
    }
}
