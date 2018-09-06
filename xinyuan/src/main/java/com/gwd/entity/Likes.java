package com.gwd.entity;

/*点赞表like(id,user_id,wish_id,create_time,status)*/

public class Likes {
    private Integer id;
    private Integer userId;
    private Integer wishId;
    private String createTime;
    private boolean state;

    public Likes() {
    }

    public Likes(Integer userId, Integer wishId, String createTime, boolean state) {
        this.userId = userId;
        this.wishId = wishId;
        this.createTime = createTime;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", userId=" + userId +
                ", wishId=" + wishId +
                ", createTime='" + createTime + '\'' +
                ", state=" + state +
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
