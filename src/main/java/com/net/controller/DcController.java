package com.net.controller;

import com.net.entity.Users;
import com.net.mapper.UsersMapper;
import com.net.utils.FileUtil;
import com.net.utils.RETINFO;
import com.net.utils.RandomUtil;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


import java.io.File;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class DcController {

    @Autowired
    UsersMapper usersMapper;

    @RequestMapping("/download")
    @ResponseBody
    public Object download(Integer status,String begin_date,String end_date,
                           HttpSession session){
        Map<String,Object> map = new HashMap<>();
        try{
            Users users =  (Users) session.getAttribute("user_power");
            map.put("b_uid",users.getId());
            map.put("status",1);
            List<Users> list=usersMapper.finduserxuesheng();
            //下载xls
            String realPath = ResourceBundle.getBundle("application").getString("app.upload.dir");
            String fileName = System.currentTimeMillis() + RandomUtil.getRandom(4) + ".xls";


            String[] title = {"ID","账号","密码","名称","手机号","邮箱","性别","院校","班级","籍贯"};
            // 创建Excel文件
            File file = new File(realPath +"//"+ fileName);
            if (!file.exists()) {
                FileUtil.createDir(realPath);
            }
            //写入表格
            try {
                file.createNewFile();
                // 创建工作簿
                WritableWorkbook workbook = Workbook.createWorkbook(file);
                // 创建sheet
                WritableSheet sheet = workbook.createSheet("sheet1", 0);
                Label label = null;
                // 第一行设置列名
                for (int i = 0; i < title.length; i++) {
                    label = new Label(i, 0, title[i]);
                    sheet.addCell(label);
                }

                // 追加数据
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                for (int i = 0; i < list.size(); i++) {
                    Users u = list.get(i);
                    label = new Label(0, i + 1, u.getId()+"");// ID
                    sheet.addCell(label);

                    label = new Label(1, i + 1, u.getName());// 账号
                    sheet.addCell(label);

                    label = new Label(2, i + 1, u.getPwd());// 密码
                    sheet.addCell(label);

                    label = new Label(3, i + 1,u.getNickname());// 名称
                    sheet.addCell(label);

                    label = new Label(4, i + 1,u.getTel());// 手机号
                    sheet.addCell(label);

                    label = new Label(5, i + 1,u.getEmail());// 邮件
                    sheet.addCell(label);

                    String sex="";
                    if(u.getSex()==1){
                        sex="男";
                    }else{
                        sex="女";
                    }
                    label = new Label(6, i + 1,sex);// 性别
                    sheet.addCell(label);

                    label = new Label(7, i + 1,u.getAcademy());// 院系
                    sheet.addCell(label);


                    label = new Label(8, i + 1,u.getClass_grade());// 班级
                    sheet.addCell(label);


                    label = new Label(9, i + 1,u.getBirthplace());// 籍贯
                    sheet.addCell(label);




                }
                // 写入数据
                workbook.write();
                workbook.close();
                String url=ResourceBundle.getBundle("application").getString("app.upload.server")+":"+
                        ResourceBundle.getBundle("application").getString("server.port")+
                        ResourceBundle.getBundle("application").getString("server.servlet.context-path")+
                        "/files/"+fileName;
                map.put(RETINFO.CODE, RETINFO.SUCCESS_CODE);
                map.put(RETINFO.MSG,RETINFO.SUCCESS_MSG);
                map.put(RETINFO.DATA,url);
                return map;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
                map.put(RETINFO.MSG,RETINFO.FAIL_MSG);
                return map;
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
            map.put(RETINFO.MSG,RETINFO.FAIL_MSG);
            return map;
        }
    }









    private String format(Date date) {
        // TODO Auto-generated method stub
        return null;
    }



}
