package com.gwd.dao;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gwd.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao extends BaseMapper<User> {
    User getUserByPhoneAndPassword(@Param("phone") String phone, @Param("password") String password); // 通过手机号和密码查询用户
    void updateTokenById(@Param("id") Integer id, @Param("token") String token); // 更新用户token
    Integer getUserByToken(@Param("token") String token); // 通过token用户id
    List<User> getRand(); //  随机返回用户
    User getUserByPhone(String phone); // 通过手机号获取用户
    void updateUserPasswordByPhone(@Param("phone") String phone,@Param("password") String password); // 通过手机号修改用户密码
    void updateAvatarById(@Param("id") Integer id,@Param("avatar") String avatar); // 通过id更新头像
    String getUserName(Integer id); // 获取用户头像

}

