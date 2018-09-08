package com.gwd.controller;

import com.gwd.dao.SmsDao;
import com.gwd.dao.UserDao;
import com.gwd.entity.ResponseData;
import com.gwd.entity.Sms;
import com.gwd.entity.User;
import com.gwd.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@RequestMapping
@Controller
public class PageController {

    @Resource
    private UserDao userDao;

    @Resource
    private SmsDao smsDao;

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

    @RequestMapping("/sign_up")
    public String signUp(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        return "sign_up.html";
    }

    @RequestMapping("/sign_in")
    public String signin(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        return "sign_in.html";
    }


    @RequestMapping("/wish")
    public String wishId(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        System.out.println("sss");
        return "moment-detail";
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




    // 用户退出登录
    @RequestMapping(value = "/out",method = RequestMethod.GET)
    public ModelAndView out(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        userService.out(request, response);
        return new ModelAndView("sign_in");
    }


}
