package com.gwd.controller;

import com.gwd.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@RequestMapping
@Controller
public class PageController {

    @Resource
    private UserService userService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Integer id = userService.isLogin(request,response);
        if(id == null){
           return "sign_in.html";
        }
        return "index.html";
    }


    // 用户登录
    @Transactional
    @RequestMapping("/login")
    public ModelAndView login(String phone, String password, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView modelAndView = new ModelAndView();
//        System.out.println("phone:"+phone);
//        System.out.println("password:"+password);
        boolean isLogin = userService.login(phone, password, response);
        if (isLogin == false) {
            modelAndView.setViewName("sign_in");
            modelAndView.addObject("msg","账号或密码错误");
            return modelAndView;
        }
        modelAndView.setViewName("index");
        return modelAndView;
    }


}
