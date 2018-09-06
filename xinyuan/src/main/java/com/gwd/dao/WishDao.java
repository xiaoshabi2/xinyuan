package com.gwd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gwd.entity.Wish;
import org.apache.ibatis.annotations.Param;

public interface WishDao extends BaseMapper<Wish> {

    void addLikeNum(Integer id);
    void subLikeNum(Integer id);
    void addCommentNum(Integer id);
    void updateAnonymousAndOpen(@Param("id") Integer id,@Param("userId")Integer userId,@Param("anonymous") boolean anonymous,@Param("open") boolean open);
    void deleteWish(@Param("id") Integer id,@Param("userId")Integer userId);
}
