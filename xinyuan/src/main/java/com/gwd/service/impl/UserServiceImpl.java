package com.gwd.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.interfaces.Claim;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gwd.Util.CookieUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.gwd.dao.UserDao;
import com.gwd.entity.User;
import com.gwd.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static com.gwd.Util.TokenUtil.createToken;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;



    @Transactional
    @Override
    public boolean login(String phone, String password, HttpServletResponse response) throws UnsupportedEncodingException {
        User user = userDao.getUserByPhoneAndPassword(phone, password);
        if (user != null) {
 //           System.out.println(user);
            Integer id = user.getId();
            String token = createToken(id.toString());
//            System.out.println(token);
            CookieUtil.add(response,token);  // 添加cookie
//            Map<String,Claim> claims=VerifyToken(token);
//            System.out.println(claims.get("id").asString());
            userDao.updateTokenById(id,token);
            return true;
        }
        return false;
    }

    @Override
    public Integer isLogin(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String token = CookieUtil.get(request);  // 查找cookie里面的token
        if(token != null){
            Integer id = userDao.getUserByToken(token);
            if(id != null){
                return id;
            }
        }
        return null;
    }



    @Override
    public void out(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        CookieUtil.delete(request,response);  // 删除cookie
    }

    @Override
    public List<User> randList() {
        return userDao.getRand();
    }


    @Override
    public User getUserByPhone(String phone) {
        System.out.println("查询数据库");
        return userDao.getUserByPhone(phone);
    }

    @Override
    public User getById(Integer id) {
        return userDao.selectById(id);
    }


}