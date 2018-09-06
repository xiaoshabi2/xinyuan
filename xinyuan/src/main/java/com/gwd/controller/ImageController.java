package com.gwd.controller;

import com.gwd.Util.MyFileUtil;
import com.gwd.entity.User;
import com.gwd.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@CrossOrigin
@RequestMapping
@Controller
public class ImageController {

    @Resource
    private UserService userService;


    @RequestMapping(value = "/img/wish/{fileName}",method = RequestMethod.GET)
    public void downloadWishImage(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        MyFileUtil.outImage(fileName,response,MyFileUtil.PATH_NUMBER_WISH);
        //System.out.println("ss");
    }

    @RequestMapping(value = "/img/user/{userId}",method = RequestMethod.GET)
    public void downloadUserImage(@PathVariable Integer userId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = userService.getById(userId);
        MyFileUtil.outImage(user.getAvatar(),response,MyFileUtil.PATH_NUMBER_AVATAR);
    }

}
