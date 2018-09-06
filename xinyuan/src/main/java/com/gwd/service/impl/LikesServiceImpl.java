package com.gwd.service.impl;


import com.gwd.Util.DateUtil;
import com.gwd.dao.LikesDao;
import com.gwd.dao.WishDao;
import com.gwd.entity.Likes;
import com.gwd.entity.Wish;
import com.gwd.service.LikesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service("likesService")
public class LikesServiceImpl implements LikesService {

    @Resource
    private LikesDao likesDao;

    @Resource
    private WishDao wishDao;


    @Transactional
    @Override
    public void addOrSub(Integer userId, Integer wishId) {
        Likes oldLike = likesDao.getByUserIdAndWishId(userId,wishId);
        if(oldLike == null){
            String createTime = DateUtil.format.format(new Date());
            Likes newLike = new Likes(userId,wishId,createTime,true);
           // System.out.println(newLike);
            likesDao.insert(newLike);
            wishDao.addLikeNum(wishId);
        }else {
            if(oldLike.isState()){
                wishDao.subLikeNum(wishId);
                likesDao.updateStatusByUserIdAndWishId(userId,wishId,false);
            }else {
                wishDao.addLikeNum(wishId);
                likesDao.updateStatusByUserIdAndWishId(userId,wishId,true);
            }

        }
    }

    @Override
    public boolean isLike(Integer userId, Integer wishId) {
        Likes likes = likesDao.getByUserIdAndWishId(userId,wishId);
        if(likes == null){
            return false;
        }else {
            if(likes.isState()){
                return true;
            } else{
                return false;
            }
        }
    }

}
