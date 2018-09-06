package com.gwd.controller;

import com.aliyuncs.exceptions.ClientException;
import com.gwd.Util.SmsUtil;
import com.gwd.dao.SmsDao;
import com.gwd.entity.ResponseData;
import com.gwd.service.SmsService;
import com.gwd.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.gwd.Util.VerCodeUtil.GetRandomString;


@CrossOrigin
@RestController
@RequestMapping(value = "/sms",method = RequestMethod.POST)
public class SmsController {

    @Resource
    private SmsService smsService;

    @Resource
    private UserService userService;

    @Resource
    private SmsDao smsDao;

    private final int MSG_ONE = 1; // 注册
    private final String MSG_ONE_STR = "注册"; // 注册

    private final int MSG_TWO = 2; // 修改密码
    private final String MSG_TWO_STR = "修改密码"; // 注册

    @RequestMapping("/send/{phone}/{code}")
    public ResponseData send(@PathVariable("phone")String phone,@PathVariable("code")Integer code, HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        if(code == MSG_ONE){
            if(userService.getUserByPhone(phone) == null){
                try {
                    smsService.sendSms(phone,MSG_ONE_STR,request);
                } catch (ClientException e) {
                    responseData.setStatusOther("发送短信错误");
                    e.printStackTrace();
                }
            }else {
                responseData.setStatusOther("该手机号已经注册");
                return responseData;
            }
        }else if(code == MSG_TWO){
            if(userService.getUserByPhone(phone) == null){
                responseData.setStatusOther("没有该用户");
            }else {
                try {
                    smsService.sendSms(phone,MSG_TWO_STR,request);
                } catch (ClientException e) {
                    responseData.setStatusOther("发送短信错误");
                    e.printStackTrace();
                }
            }
        }else {
            responseData.setStatusOther("请求错误");
        }
        return responseData;
    }
}
