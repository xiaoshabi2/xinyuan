package com.gwd.controller;
import com.gwd.dao.LikesDao;
import com.gwd.entity.Likes;
import com.gwd.entity.ResponseData;
import com.gwd.service.LikesService;
import com.gwd.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
@CrossOrigin
@RestController
@RequestMapping(value = "/like",method = RequestMethod.POST)
public class LikesController {

    @Resource
    private LikesService likesService;

    @Resource
    private LikesDao likesDao;

    @Resource
    private UserService userService;


    // 点赞
    @Transactional
    @RequestMapping("/spot/{wishId}")
    public ResponseData addOrSub(@PathVariable("wishId")Integer wishId, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ResponseData responseData = new ResponseData();
        Integer userId = userService.isLogin(request,response);
        if(userId == null){
            responseData.setStatusNoLogin();
            return responseData;
        }else {
            likesService.addOrSub(userId,wishId);
        }
        return responseData;
    }

    // 是否点过赞
    @RequestMapping("/islike/{wishId}")
    public ResponseData isLike(@PathVariable("wishId")Integer wishId, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ResponseData responseData = new ResponseData();
        Integer userId = userService.isLogin(request,response);
        if(userId == null){
            responseData.setStatusNoLogin();
            return responseData;
        }else {
            boolean state = likesService.isLike(userId,wishId);
            responseData.setData(state);
        }
        return responseData;
    }





}
