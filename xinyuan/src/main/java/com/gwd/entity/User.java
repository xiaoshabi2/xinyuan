package com.gwd.entity;


/*用户表user（id,username,avatar,gender,qq,wechat,phone,password,college,open,create_time,token)*/

import java.io.Serializable;

public class User implements Serializable {



	private Integer id;
	private String username;
	private String avatar;
	private String gender;
	private String qq;
	private String wechat;
	private String phone;
	private String password;
	private String college;
	private boolean open;
	private String createTime;
	private String token;

	public User() {
	}

	public User(String username, String phone, String password) {
		this.username = username;
		this.phone = phone;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", avatar='" + avatar + '\'' +
				", gender='" + gender + '\'' +
				", qq='" + qq + '\'' +
				", wechat='" + wechat + '\'' +
				", phone='" + phone + '\'' +
				", password='" + password + '\'' +
				", college='" + college + '\'' +
				", open=" + open +
				", createTime=" + createTime +
				", token='" + token + '\'' +
				'}';
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
