package com.gwd.service;

import com.gwd.entity.Comment;

import java.util.List;

public interface CommentService {
    void add(Integer userId,Integer wishId,String content);
    List<Comment> getList(Integer wishId, Integer page);
}
