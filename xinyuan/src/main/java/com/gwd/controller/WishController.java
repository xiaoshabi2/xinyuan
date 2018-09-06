package com.gwd.controller;

import com.gwd.entity.ResponseData;
import com.gwd.service.UserService;
import com.gwd.service.WishService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@CrossOrigin
@RestController
@RequestMapping(value = "/wish", method = RequestMethod.POST)
public class WishController {

    private static final String POOL = "pool";
    private static final String DAY_RANK = "day";
    private static final String MONTH_RANK = "month";
    private static final String ALL_RANK = "all";
    private static final String REAL_RANK = "real";

    private static final String ANONYMOUS = "anonymous";
    private static final String OPEN = "open";


    @Resource
    private WishService wishService;



    @Resource
    private UserService userService;

    @RequestMapping("/get/{remark}/{page}")
    public ResponseData getWish(@PathVariable("remark") String remark, @PathVariable("page") Integer page, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ResponseData responseData = new ResponseData();
        Integer userId = userService.isLogin(request, response);
        if (userId == null) {
            responseData.setStatusNoLogin();
            return responseData;
        } else {
            if (POOL.equals(remark)) {
                responseData.setData(wishService.getWishPool(page));
            } else if (ALL_RANK.equals(remark)) {
                responseData.setData(wishService.getWishAll(page));
            } else if (REAL_RANK.equals(remark)) {
                responseData.setData(wishService.getRealWish(page));
            }
        }
        return responseData;
    }


    @RequestMapping("/get/{remark}/{page}/{date}")
    public ResponseData getWishByDate(@PathVariable("remark") String remark, @PathVariable("page") Integer page, @PathVariable("date") String date, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ResponseData responseData = new ResponseData();
        Integer userId = userService.isLogin(request, response);
        if (userId == null) {
            responseData.setStatusNoLogin();
            return responseData;
        } else {
            if (DAY_RANK.equals(remark)) {
                responseData.setData(wishService.getWishDay(page,date));
            } else if (MONTH_RANK.equals(remark)) {
                responseData.setData(wishService.getWishMonth(page,date));
            }
            return responseData;
        }
    }



   /*
        表单用 form-data 提交方式
   * */
    @Transactional
    @RequestMapping("/add")
    public ResponseData addWish(@RequestParam(value = "content") String content,
                                @RequestParam(value = "image", required = false) MultipartFile image,
                                @RequestParam(value = "anonymous") boolean anonymous,
                                @RequestParam(value = "open") boolean open,
                                HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        Integer userId = userService.isLogin(request, response);
        if (userId == null) {
            responseData.setStatusNoLogin();
            return responseData;
        } else {
            wishService.addWish(userId, content, image, anonymous, open);
        }
        return responseData;
    }




    @Transactional
    @RequestMapping("/update")
    public ResponseData update(Integer wishId,boolean anonymous ,boolean open,HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        Integer userId = userService.isLogin(request, response);
        if (userId == null) {
            responseData.setStatusNoLogin();
            return responseData;
        } else {
            wishService.updateAnonymousAndOpen(wishId,userId,anonymous,open);
        }
        return responseData;
    }



    @Transactional
    @RequestMapping("/delete/{wishId}")
    public ResponseData delete(@PathVariable("wishId")Integer wishId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        Integer userId = userService.isLogin(request, response);
        if (userId == null) {
            responseData.setStatusNoLogin();
            return responseData;
        } else {
            wishService.deleteWish(wishId,userId);
        }
        return responseData;
    }


    @RequestMapping("/user/{userId}/{page}")
    public ResponseData getWishId(@PathVariable("userId")Integer userId,@PathVariable("page")Integer page, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        if (userService.isLogin(request, response) == null) {
            responseData.setStatusNoLogin();
            return responseData;
        } else {
            responseData.setData(wishService.getUserAllWish(userId,page));
        }
        return responseData;
    }



}
