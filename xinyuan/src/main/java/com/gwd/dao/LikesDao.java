package com.gwd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gwd.entity.Likes;
import org.apache.ibatis.annotations.Param;

public interface LikesDao extends BaseMapper<Likes> {
    Likes getByUserIdAndWishId(@Param("userId")Integer userId,@Param("wishId")Integer wishId);
    void updateStatusByUserIdAndWishId(@Param("userId")Integer userId,@Param("wishId")Integer wishId,@Param("state")boolean state);
}
