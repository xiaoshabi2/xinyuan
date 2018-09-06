package com.gwd.entity;


public class Sms {
    private Integer id;
    private String msg;
    private String createTime;
    private String verCode;
    private String phone;
    private String ip;
    private boolean hasUse;

    public Sms() {
    }

    public Sms(String msg, String createTime, String verCode, String phone, String ip) {
        this.msg = msg;
        this.createTime = createTime;
        this.verCode = verCode;
        this.phone = phone;
        this.ip = ip;
        this.hasUse = false;
    }


    @Override
    public String toString() {
        return "Sms{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", createTime='" + createTime + '\'' +
                ", verCode='" + verCode + '\'' +
                ", phone='" + phone + '\'' +
                ", ip='" + ip + '\'' +
                ", hasUse=" + hasUse +
                '}';
    }

    public boolean isHasUse() {
        return hasUse;
    }

    public void setHasUse(boolean hasUse) {
        this.hasUse = hasUse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
