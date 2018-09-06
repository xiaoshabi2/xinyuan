package com.gwd.service;

import com.aliyuncs.exceptions.ClientException;

import javax.servlet.http.HttpServletRequest;

public interface SmsService {
    void sendSms(String phoneNum, String msg, HttpServletRequest request) throws ClientException;
}
