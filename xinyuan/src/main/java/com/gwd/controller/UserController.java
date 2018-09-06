package com.gwd.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gwd.Util.MyFileUtil;
import com.gwd.dao.SmsDao;
import com.gwd.dao.UserDao;
import com.gwd.entity.ResponseData;
import com.gwd.entity.Sms;
import com.gwd.entity.User;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.gwd.service.UserService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RequestMapping(value = "/user", method = RequestMethod.POST)
@RestController
public class UserController {


    @Resource
    private SmsDao smsDao;

    @Resource
    private UserService userService;

    @Resource
    private UserDao userDao;




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
    public void out(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        userService.out(request, response);
    }


    // 用户信息
   /* @Cacheable(value = "userInfo2")*/
    @RequestMapping("/info")
    public ResponseData info(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ResponseData responseData = new ResponseData();
        Integer id = userService.isLogin(request,response);
        if(id == null){
            System.out.println();
            responseData.setStatusNoLogin();
            return responseData;
        }else {
            responseData.setData(userDao.selectById(id));
        }
        return responseData;
    }

    // 更新用户信息
    @Transactional
    @RequestMapping("/update")
    public ResponseData update(User user,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ResponseData responseData = new ResponseData();
        Integer id = userService.isLogin(request,response);
        if(id == null){
            responseData.setStatusNoLogin();
            return responseData;
        }else {
            user.setId(id);
            System.out.println(user);
            user.setPhone(null);
            user.setPassword(null);
            user.setAvatar(null);
            userDao.updateById(user);
           // responseData.setData(userDao.selectById(id));
        }
        return responseData;
    }



    // 更新用户头像
    @Transactional
    @RequestMapping("/update/avatar")
    public ResponseData updateAvatar(@RequestParam(value = "avatar") MultipartFile avatar,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ResponseData responseData = new ResponseData();
        Integer id = userService.isLogin(request,response);
        if(id == null){
            responseData.setStatusNoLogin();
            return responseData;
        }else {
            try {
                String userAvatar = MyFileUtil.InImage(avatar,MyFileUtil.PATH_NUMBER_AVATAR);
                System.out.println(userAvatar);
                userDao.updateAvatarById(id,userAvatar);
                responseData.setStatusOther("上传成功");
            } catch (IOException e) {
                e.printStackTrace();
                responseData.setStatusOther("上传头像错误");
                return responseData;
            }
            // responseData.setData(userDao.selectById(id));
        }
        return responseData;
    }




    @Transactional
    @RequestMapping("/update/password")
    public ResponseData updatePassword(String oldPassword,String newPassword,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ResponseData responseData = new ResponseData();
        System.out.println(oldPassword + " " + newPassword );
        Integer id = userService.isLogin(request,response);
        if(id == null){
            responseData.setStatusNoLogin();
            return responseData;
        }else {
            User user = userDao.selectById(id);
            if(!user.getPassword().equals(oldPassword)){
                System.out.println(user);
                responseData.setStatusOther("原密码错误");
                return responseData;
            }else {
                user.setPassword(newPassword);
                userDao.updateById(user);
                responseData.setMsg("修改密码成功");
                return responseData;
            }
        }

    }


    @RequestMapping("/cookies")
    public Cookie[] cookies(HttpServletRequest request) {
        return request.getCookies();
    }




    // 随机获取用户
    @Transactional
    @RequestMapping("/rand")
    public ResponseData rand(User user,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ResponseData responseData = new ResponseData();
        responseData.setData(userService.randList());
        return responseData;
    }


    @Transactional
    @RequestMapping("/update/sms/password")
    public ResponseData updateSmsPassword(String phone,String password,String vercode,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ResponseData responseData = new ResponseData();
        Sms sms = smsDao.getByPhoneAndVercode(phone,vercode);
        if(sms == null){
            responseData.setStatusOther("验证码错误");
            return responseData;
        }else {
            userDao.updateUserPasswordByPhone(phone,password);
            smsDao.updateUseById(sms.getId());
            responseData.setMsg("修改密码成功");
        }
        return responseData;
    }

    @Transactional
    @RequestMapping("/register")
    public ResponseData register(String username,String phone,String password,String vercode,HttpServletRequest request, HttpServletResponse response) {
        ResponseData responseData = new ResponseData();
        Sms sms = smsDao.getByPhoneAndVercode(phone,vercode);
        if(sms == null){
            responseData.setStatusOther("验证码错误");
            return responseData;
        }else {
            User user = new User(username,phone,password);
            userDao.insert(user);
            smsDao.updateUseById(sms.getId());
            responseData.setMsg("注册成功");
        }
        return responseData;
    }


}  