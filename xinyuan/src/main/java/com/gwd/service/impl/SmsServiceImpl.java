package com.gwd.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.gwd.Util.DateUtil;
import com.gwd.Util.SmsUtil;
import com.gwd.dao.SmsDao;
import com.gwd.entity.Sms;
import com.gwd.service.SmsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.gwd.Util.NetworkUtil.GetIpAddress;
import static com.gwd.Util.VerCodeUtil.GetRandomString;

@Service("smsService")
public class SmsServiceImpl implements SmsService {
    @Resource
    private SmsDao smsDao;

    @Override
    public void sendSms(String phoneNum,String msg, HttpServletRequest request) throws ClientException {
        Sms sms = new Sms(msg,DateUtil.format.format(new Date()),GetRandomString(6),phoneNum,GetIpAddress(request));
        SmsUtil.SendVerificationCode(phoneNum,sms.getVerCode(),msg);
        System.out.println(sms);
        smsDao.insert(sms);
    }
}
