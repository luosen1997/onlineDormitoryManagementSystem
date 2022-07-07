package com.net.controller;

import com.net.entity.Transfer;
import com.net.entity.Users;
import com.net.mapper.DormMapper;
import com.net.mapper.Order_towerMapper;
import com.net.mapper.UsersMapper;
import com.net.utils.PageBean;
import com.net.utils.RETINFO;
import com.net.utils.UploadFileNameDefineUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 	用户业务
 */
@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    UsersMapper usersMapper;
    @Autowired
    PageBean page;
    @Autowired
    DormMapper dormMapper;
    @Autowired
    Order_towerMapper order_towerMapper;

    /**
     * 	用户列表
     * @param pageIndex  当前页码
     * @param like       关键字
     * @param begin_date 开始时间
     * @param end_date   结束时间
     * @return 用户列表页码
     */
    @RequestMapping("/list")
    public String userList(String pageIndex,String like,String role,String sex,
                           String begin_date,String end_date,Map<String,Object> map,HttpSession session){
    	Users admin =  (Users) session.getAttribute("user_power");
    	map.put("users", admin);
    	map.put("like",like);
        map.put("role",role);
        map.put("sex",sex);
        map.put("smoke",sex);
        map.put("begin_date", begin_date);
        map.put("end_date", end_date);
        int count = usersMapper.selAllFind(map).size();
        page=page.getPageBean(pageIndex,count);
        map.put("begin", page.getStartRow());
        map.put("last", page.getEndRow());
        List<Users> users = usersMapper.selAllFind(map);
        map.put("list",users);
        map.put("pageBean", page);
        return "users/user-list";
    }


    /**
     * 	接口-登录
     * @param session
     * @param user	用户信息 name=&pwd=&role=
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Object Login(HttpSession session,Users user){
        Map<String,Object> map = new HashMap<>();
        try {
            if(user != null ) {
                //登录
                Users u = usersMapper.login(user);
                if(u != null){
                	session.setAttribute("user_power",u);
                	map.put("users",u);
                    map.put(RETINFO.CODE,RETINFO.SUCCESS_CODE);
                    map.put(RETINFO.MSG,RETINFO.SUCCESS_MSG);
                    map.put(RETINFO.DATA,u);
                    return map;
                }else{
                    map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
                    map.put(RETINFO.MSG,"账户或密码错误!");
                    return map;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
        map.put(RETINFO.MSG,RETINFO.FAIL_MSG);
        return map;
    }
    
    @RequestMapping("/login_out")
    public String LoginOutServlet(HttpSession session) throws Exception {
        session.removeAttribute("user_power");
        return "redirect:/";
    }
    
    //跳转添加界面
    @RequestMapping("/toadd")
    public String toAdd(Map<String,Object> map,String role){
        map.put("roles",role);
        return "users/user-add";
    }


    //添加
    @RequestMapping("/add")
    @ResponseBody
    public Object add(Users transfer) throws  Exception{
        Map<String,Object> map = new HashMap<>();
        Users uu = usersMapper.findUserByName(transfer.getName());
        if(uu != null) {
            map.put(RETINFO.CODE, RETINFO.FAIL_CODE);
            map.put(RETINFO.MSG, "账户已存在!");
            return map;
        }
        int res = usersMapper.insertSelective(transfer);
        if(res > 0){
            map.put(RETINFO.CODE,RETINFO.SUCCESS_CODE);
            map.put(RETINFO.MSG,RETINFO.SUCCESS_MSG);
            return map;
        }
        map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
        map.put(RETINFO.MSG,RETINFO.FAIL_MSG);
        return map;
    }

	//跳转编辑界面
    @RequestMapping("/toedit")
    public String toEdit(Map<String,Object> map,int id,HttpSession session){
    	Users users = usersMapper.selectByPrimaryKey(id);
        Users admin =  (Users) session.getAttribute("user_power");
        List<Users> list =usersMapper.findteacher();
        if(users.getRole().equals("1")){
            map.put("list",list);
        }
        map.put("users",users);
        map.put("userss",admin);
        return "users/user-edit";
    }

    //编辑
    @RequestMapping("/edit")
    @ResponseBody
    public Object update(Users u){
        Map<String,Object> map = new HashMap<>();
        try {
            int res = usersMapper.updateByPrimaryKeySelective(u);
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

    //删除
    @RequestMapping("/del")
    @ResponseBody
    public Object del(int id) throws  Exception{
        Map<String,Object> map = new HashMap<>();
        int res = usersMapper.deleteByPrimaryKey(id);
        if(res > 0){
            map.put(RETINFO.CODE,RETINFO.SUCCESS_CODE);
            map.put(RETINFO.MSG,RETINFO.SUCCESS_MSG);
            return map;
        }
        map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
        map.put(RETINFO.MSG,RETINFO.FAIL_MSG);
        return map;
    }


    //上传文件
    @PostMapping("/upload")
    @ResponseBody
    public Object upload(MultipartFile file, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            String server = "",port="";
            System.out.println("文件长度: " + file.getSize() + "文件类型: "
                    + file.getContentType() + "文件名称: " + file.getName()
                    + "文件原名: " + file.getOriginalFilename());
            //重新定义文件的名称
            String filename = UploadFileNameDefineUtil.DefineUploadFileName(file.getOriginalFilename());
            //取得文件的后缀
            String suffix = filename.substring(filename.lastIndexOf("."));
            //判断是不是图片
            if(".jpg".equals(suffix) || ".png".equals(suffix) || ".gif".equals(suffix) || ".bmp".equals(suffix) || ".jpeg".equals(suffix)  || ".pdf".equals(suffix)){
                //获取application.properties中的上传路径
                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
                Properties props = new Properties();
                try {
                    props.load(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String fileUrl = props.getProperty("app.upload.dir");
                server = props.getProperty("app.upload.server");
                port = props.getProperty("server.port");
                //将文件存储到upload/bar目录下
                File newFile = new File(fileUrl+"\\"+filename);
                try {
                    file.transferTo(newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                    map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
                    map.put(RETINFO.MSG,RETINFO.FAIL_MSG);
                    return map;
                }

            }else{
                map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
                map.put(RETINFO.MSG,"请选择正确的格式文件！");
                return map;
            }
            String file_photo = server+":"+port+request.getContextPath()+"/files/"+filename;
            map.put(RETINFO.CODE,RETINFO.SUCCESS_CODE);
            map.put(RETINFO.MSG,RETINFO.SUCCESS_MSG);
            map.put(RETINFO.DATA,file_photo);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put(RETINFO.CODE,RETINFO.FAIL_CODE);
        map.put(RETINFO.MSG,RETINFO.FAIL_MSG);
        return map;
    }
   
}
