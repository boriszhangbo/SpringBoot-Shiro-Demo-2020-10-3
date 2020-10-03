package com.fyy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  @ClassName: MyController   
 *  @Description: 
 *  @Author: fu yuanyuan
 *  @CreateDate: 2020/10/2 19:43
 *  @UpdateUser:    
 *  @UpdateDate: 
 *  @UpdateRemark:    
 *  @Version: 1.0   
 */
@Controller
public class MyController {

    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("msg","hello,shiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username,String password,Model model){
        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();

        // 封装用户登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            // 执行登录
            subject.login(token);
            // 登录成功跳转到首页
            return "index";
        }catch (UnknownAccountException e){ // 用户名不存在
            model.addAttribute("msg","用户名错误");
            // 登录失败跳转到登录页
            return "login";
        }catch (IncorrectCredentialsException e){ // 用户名不存在
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }

    @RequestMapping("/noauth")
    @ResponseBody           // 返回字符串
    public String unauthorized(){
        return "未经授权无法访问此页面";
    }
}