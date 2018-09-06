package com.gwd.service.impl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gwd.Util.DateUtil;
import com.gwd.dao.CommentDao;
import com.gwd.dao.WishDao;
import com.gwd.entity.Comment;
import com.gwd.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;

    @Resource
    private WishDao wishDao;


    private final int n = 5 ;

    @Transactional
    @Override
    public void add(Integer userId, Integer wishId, String content) {
        String date = DateUtil.format.format(new Date());
        Comment comment = new Comment(userId,wishId,content,date);
        commentDao.insert(comment);
        wishDao.addCommentNum(wishId);
    }

    @Override
    public List<Comment> getList(Integer wishId, Integer page) {
        List<Comment> comments = commentDao.selectPage(new Page<Comment>(page,n),
                new EntityWrapper<Comment>().eq("wish_id",wishId).orderBy("create_time",true)
        );
        return comments;
    }
}
