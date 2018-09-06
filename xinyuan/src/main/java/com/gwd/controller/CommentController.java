package com.gwd.controller;

import com.gwd.dao.WishDao;
import com.gwd.entity.ResponseData;
import com.gwd.service.CommentService;
import com.gwd.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@CrossOrigin
@RestController
@RequestMapping(value = "/comment",method = RequestMethod.POST)
public class CommentController {
    @Resource
    private CommentService commentService;

    @Resource
    private WishDao wishDao;

    @Resource
    private UserService userService;

    @RequestMapping("/add")
    public ResponseData add(Integer wishId,String content,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ResponseData responseData = new ResponseData();
        Integer userId = userService.isLogin(request,response);
        if(userId == null){
            responseData.setStatusNoLogin();
            return responseData;
        }else {
   //         System.out.println(wishId + content);
            if(wishId != null && content != null){
                if(wishDao.selectById(wishId)!=null){
                    commentService.add(userId,wishId,content);
                }else {
                    responseData.setStatusOther("该心愿不存在");
                    return responseData;
                }
            }else {
                responseData.setStatusOther("评论内容不能为空");
                return responseData;
            }
        }
        return responseData;
    }


    @RequestMapping("/get/{wishId}/{page}")
    public ResponseData get(@PathVariable("wishId") Integer wishId,@PathVariable("page") Integer page,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ResponseData responseData = new ResponseData();
        Integer userId = userService.isLogin(request,response);
        if(userId == null){
            responseData.setStatusNoLogin();
            return responseData;
        }else {
            responseData.setData(commentService.getList(wishId,page));
        }
        return responseData;
    }



}
