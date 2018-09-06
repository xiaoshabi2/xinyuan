package com.gwd.service;

public interface LikesService {
    void addOrSub(Integer userId,Integer wishId);
    boolean isLike(Integer userId,Integer wishId);
}
