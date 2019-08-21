package com.jsu.action.qu;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.jsu.dao.qu.UserDao;
import com.jsu.entity.User;

@Controller
public class LoginAction {
	@Resource(name="userDao")
	private UserDao dao;
	private String email;
	private String pwd;
	private String jsonData;
	private User user;
	
	public String login(){
		System.out.println("您尊贵的用户要开始登陆了哟！");
		boolean flag=false;
		boolean flag2=false;
		System.out.println(email);
		System.out.println(pwd);
		flag=dao.findEmail(email);
		//flag=dao.findEmail("2414726781@qq.com");
		System.out.println("我的标志："+flag);
		if(flag){
			System.out.println("该用户存在，继续查找密码是否匹配……");
			//flag2=dao.findEmailAndPwd("2414726781@qq.com", "123456");
			flag2=dao.findEmailAndPwd(email, pwd);
			user=dao.findUserByEmail(email);
			//user=dao.findUserByEmail("2414726781@qq.com");
			System.out.println("第二个标致flag2："+flag2);
			if(flag2){
			System.out.println("登录成功！！");
			
			//给前端发送JSON数据   
			System.out.println("开始发送JSON数据");
			JSONObject obj = new JSONObject();
			obj.put("code", 200);
			obj.put("status", 1);
			obj.put("user", user);
			obj.put("msg", "登录成功");
			jsonData = obj.toString();
			System.out.println(obj.toString());
			return "success";

			}else{
				System.out.println("密码错误");
				
				//给前端发送JSON数据
				System.out.println("开始发送JSON数据");
				JSONObject obj = new JSONObject();
				obj.put("code", 400);
				obj.put("status", 0);
				obj.put("msg", "密码错误");
				jsonData = obj.toString();
				System.out.println(obj.toString());
				return "pwderror";
			}
		}else{
			System.out.println("该用户还没有注册哟！");
			
			//给前端发送JSON数据
			System.out.println("开始发送JSON数据");
			JSONObject obj = new JSONObject();
			obj.put("code", 400);
			obj.put("status", 0);
			obj.put("msg", "用户未注册");
			jsonData = obj.toString();
			System.out.println(obj.toString());
			return "error";
		}
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public UserDao getDao() {
		return dao;
	}
	public void setDao(UserDao dao) {
		this.dao = dao;
	}
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	

}
