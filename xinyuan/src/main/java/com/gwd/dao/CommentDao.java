package com.gwd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gwd.entity.Comment;
import com.gwd.entity.CommentResult;

import java.util.List;

public interface CommentDao extends BaseMapper<Comment> {
    List<CommentResult> getAll(Integer wishId);
}
