package com.jsu.entity;

import org.springframework.stereotype.Repository;

@Repository
public class User {
	private String id;
	private String tel;
	private String email;
	private String name;
	private String pwd;
	private String image;
	private String sex;
	private int age;
	private String hobby;
	private String createTime;
	private	int status;
	private String purse;
	
	public User() {
		super();
	}

	public User(String id, String tel, String email, String name, String pwd,
			String image, String sex, int age, String hobby, String createTime,
			int status, String purse) {
		super();
		this.id = id;
		this.tel = tel;
		this.email = email;
		this.name = name;
		this.pwd = pwd;
		this.image = image;
		this.sex = sex;
		this.age = age;
		this.hobby = hobby;
		this.createTime = createTime;
		this.status = status;
		this.purse = purse;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getPurse() {
		return purse;
	}
	public void setPurse(String purse) {
		this.purse = purse;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", tel=" + tel + ", email=" + email
				+ ", name=" + name + ", pwd=" + pwd + ", image=" + image
				+ ", sex=" + sex + ", age=" + age + ", hobby=" + hobby
				+ ", createTime=" + createTime + ", status=" + status + "]";
	}
	
	

}
