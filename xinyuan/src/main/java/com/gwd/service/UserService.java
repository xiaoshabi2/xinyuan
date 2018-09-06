package com.gwd.service;

import com.gwd.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;


public interface UserService {

    boolean login(String phone, String password, HttpServletResponse response) throws UnsupportedEncodingException; // 用户登录
    Integer isLogin(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException; // 用户是否登录 已登陆返回用户id 否则返回null
    void out(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException; // 用户退出登录
    List<User> randList(); // 随机返回用户
    User getUserByPhone(String phone);
    User getById(Integer id);
}
