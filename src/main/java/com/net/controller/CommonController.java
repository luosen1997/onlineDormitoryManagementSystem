package com.net.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.net.entity.Users;

import javax.servlet.http.HttpSession;

/**
 * 公共请求
 */
@Controller
public class CommonController {
	
	@RequestMapping("/404")
	public String errorRedirect(){
		return "error";
	}

    //跳转登录页面
    @RequestMapping("/")
    public String toLoin(){
        return "login";
    }

    //跳转登录页面
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    //index页
    @RequestMapping("/index")
    public String index(HttpSession session, Model model){
    	Users admin =  (Users) session.getAttribute("user_power");
        if(admin == null){
            return "redirect:/";
        }
        model.addAttribute("admin",admin);
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome(HttpSession session, Model model){
    	Users admin =  (Users) session.getAttribute("user_power");
        if(admin == null){
            return "redirect:/";
        }
        model.addAttribute("admin",admin);
        return "welcome";
    }
    

    
}
