package com.gwd.entity;

/*评论表content(id,user_id,wish_id,content,create_time)*/
public class Comment {
    private Integer id;
    private Integer userId;
    private Integer wishId;
    private String content;
    private String createTime;

    public Comment(Integer userId, Integer wishId, String content, String createTime) {
        this.userId = userId;
        this.wishId = wishId;
        this.content = content;
        this.createTime = createTime;
    }

    public Comment() {
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", userId=" + userId +
                ", wishId=" + wishId +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getWishId() {
        return wishId;
    }

    public void setWishId(Integer wishId) {
        this.wishId = wishId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
