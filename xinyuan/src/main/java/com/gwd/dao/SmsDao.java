package com.gwd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gwd.entity.Sms;
import org.apache.ibatis.annotations.Param;

public interface SmsDao extends BaseMapper<Sms> {
    Sms getByPhoneAndVercode(@Param("phone") String phone,@Param("vercode") String vercode);
    void updateUseById(Integer id);
}
