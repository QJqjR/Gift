package com.jsu.action.qu;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.jsu.dao.qu.UserDao;
import com.jsu.entity.User;

@Controller
public class PersonalAction {
	@Resource(name="userDao")
	private UserDao dao;
	private String email;
	private String image;
	private String id;
	private User user;
	
	//个人中心展示信息
	public String showMsg(){
		System.out.println("准备向个人中心进发……");
		user=showUserMsg();
		System.out.println("个人信息查找成功");
		return "success";
	}
	
	//关于“我”的信息
	public User showUserMsg(){
		user=dao.findUserByEmail("2414726781@qq.com");
		System.out.println("成功截获一个伊妹儿："+user.getEmail());
		System.out.println("相貌描述："+user.getImage());
		return user;
	}
	
	//关于购物车的信息
	//public ShoppingCar showSCMsg()
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public UserDao getDao() {
		return dao;
	}
	public void setDao(UserDao dao) {
		this.dao = dao;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
