package com.gwd.entity;


/*心愿表wish(id,user_id,content,image,anonymous,open,create_time)*/
public class Wish {
    private Integer id;
    private Integer userId;
    private String content;
    private String image;
    private boolean anonymous;
    private boolean open;
    private String createTime;
    private Integer likeNum;
    private Integer commentNum;
    private boolean reality;


    public Wish() {
    }

    public Wish(Integer userId, String content, String image, boolean anonymous, boolean open, String createTime, Integer likeNum) {
        this.userId = userId;
        this.content = content;
        this.image = image;
        this.anonymous = anonymous;
        this.open = open;
        this.createTime = createTime;
        this.likeNum = likeNum;
        this.reality = false;
        this.commentNum = 0;
    }


    @Override
    public String toString() {
        return "Wish{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", anonymous=" + anonymous +
                ", open=" + open +
                ", createTime='" + createTime + '\'' +
                ", likeNum=" + likeNum +
                ", commentNum=" + commentNum +
                ", reality=" + reality +
                '}';
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public boolean isReality() {
        return reality;
    }

    public void setReality(boolean reality) {
        this.reality = reality;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
