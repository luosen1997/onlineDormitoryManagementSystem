package com.net.controller;

import com.net.entity.Out;
import com.net.entity.Users;
import com.net.mapper.OutMapper;
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
@RequestMapping("out")
@Controller
public class OutController {


    @Autowired
    PageBean page;
    @Autowired
    OutMapper outMapper;

    @RequestMapping("/list")
    public String selNotice(String useranme, String dormname,String dorm_towername, String begin_date, String end_date,
                            String pageIndex, Map<String,Object> map, HttpSession session){
        try{
            Users admin =  (Users) session.getAttribute("user_power");
            map.put("user", admin);
            map.put("useranme",useranme);
            map.put("dormname",dormname);
            map.put("dorm_towername",dorm_towername);
            map.put("begin_date", begin_date);
            map.put("end_date", end_date);
            int count = outMapper.selAllOut(map).size();
            page=page.getPageBean(pageIndex, count);
            map.put("begin", page.getStartRow());
            map.put("last", page.getEndRow());
            List<Out> outList =outMapper.selAllOut(map);
            map.put("outList", outList);
            map.put("pageBean", page);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "out/out-list";
    }


    /**
     * 添加
     * @param out
     * @return
     */
  /*  @RequestMapping("/addOut")
    @ResponseBody
    public Object addOut(Out out){
        Map<String,Object> map=new HashMap<>();
        try{
            int res=outMapper.addOut(out);
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
    }*/

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delout")
    @ResponseBody
    public Object delOut( int id){
        Map<String,Object> map=new HashMap<>();
        try{
            int res=outMapper.delOut(id);
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
