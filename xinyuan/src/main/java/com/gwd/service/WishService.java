package com.gwd.service;


import com.gwd.entity.Wish;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public interface WishService {

    List<Wish> getWishPool(Integer page);

    List<Wish> getWishDay(Integer page,String date);

    List<Wish> getWishMonth(Integer page,String date);

    List<Wish> getWishAll(Integer page);

    List<Wish> getRealWish(Integer page);

    void addWish(Integer userId,String content,MultipartFile image, boolean anonymous,boolean open);

    void updateAnonymousAndOpen(@Param("id") Integer id,@Param("userId")Integer userId,@Param("anonymous") boolean anonymous,@Param("open") boolean open);

    void deleteWish(@Param("id") Integer id,@Param("userId")Integer userId);

    List<Wish> getUserAllWish(Integer userId,Integer page);
}
