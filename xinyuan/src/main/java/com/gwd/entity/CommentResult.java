package com.gwd.entity;

public class CommentResult {
    private Integer id;
    private Integer userId;
    private String username;
    private Integer wishId;
    private String content;
    private String createTime;


    @Override
    public String toString() {
        return "CommentResult{" +
                "id=" + id +
                ", userId=" + userId +
                ", username='" + username + '\'' +
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
