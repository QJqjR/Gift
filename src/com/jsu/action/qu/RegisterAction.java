package com.jsu.action.qu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import com.jsu.dao.qu.UserDao;
import com.jsu.entity.User;
import com.jsu.util.CreateIdUtil;

@Controller
public class RegisterAction {
	@Resource(name = "userDao")
	private UserDao dao;
	private User user;
	private String email;
	private String pwd;
	private String createTime;
	private int status;
	private String image;
	private String captcha;//后台生成的验证码
	private String validate;//前端提交的验证码
	private String temp;
	private String jsonData;

	//用户注册
	public String register() {
		System.out.println("用户开始注册");
		user = new User();
		String id = CreateIdUtil.getId();
		System.out.println("id\t"+id);
		System.out.println("前端验证码："+validate);
		
		image="http://b-ssl.duitang.com/uploads/blog/201409/06/20140906090442_uHBFr.jpeg";
		
		user.setId(id);
		user.setEmail(email);
		user.setPwd(pwd);
		user.setImage(image);
		user.setAge(0);
		user.setPurse("10000");
		System.out.println("hello email:"+email);
		
		//获取当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		createTime=df.format(new Date());
		System.out.println(createTime);
		user.setCreateTime(createTime);
		user.setStatus(0);//0表示注册后首次登陆，1表示并非第一次登陆
		System.out.println(user);
		
		//用户名去重
		if(dao.findEmail(email)){
			System.out.println("这个茅坑已经被占了哟——————");
			
			System.out.println("开始发送JSON数据");
			JSONObject obj = new JSONObject();
			obj.put("code", 400);
			obj.put("status", 0);
			obj.put("msg", "用户已存在");
			jsonData = obj.toString();
			System.out.println(obj.toString());
			return "repeat";
			
		}

		System.out.println("--------------------------------------------");
		System.out.println("第三方验证码："+temp);
		//判断验证码
		if(validate.equals(temp)){
			dao.addUser(user);
			//给前端发送JSON数据
			System.out.println("开始发送JSON数据");
			JSONObject obj = new JSONObject();
			obj.put("code", 200);
			obj.put("status", 1);
			obj.put("id", id);
			obj.put("msg", "注册成功");
			jsonData = obj.toString();
			System.out.println(obj.toString());
			System.out.println("用户注册成功啦啦啦啦啦啦啦");
			return "success";
		}else{
			//给前端发送JSON数据
			System.out.println("开始发送JSON数据");
			JSONObject obj = new JSONObject();
			obj.put("code", 400);
			obj.put("status", 0);
			obj.put("msg", "验证码错误！！");
			jsonData = obj.toString();
			System.out.println(obj.toString());
			return "error";
		}	
	}
	
	
	//发送验证码
	public String createCaptcha(){
		String captcha;
		String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder sb=new StringBuilder(4);
		for(int i=0;i<4;i++)
		{
		     char ch=str.charAt(new Random().nextInt(str.length()));
		     sb.append(ch);
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println(sb.toString()); 
		captcha=sb.toString();
		//第三方转存验证码
		temp=sb.toString();
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.setAttribute("captcha", captcha);
		System.out.println("放进session域咯："+session.getAttribute("captcha"));
		System.out.println("验证码为："+captcha);
		System.out.println("第三方验证码："+temp);
		System.out.println("email\t"+email);
		System.out.println("email\t"+request.getParameter("email"));
		//给用户发送验证码
		SendEmail(email, captcha);	
		
		//开始发送JSON数据
		System.out.println("开始发送JSON数据");
		JSONObject obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("msg", "验证码发送成功");
		jsonData = obj.toString();
		System.out.println(obj.toString());
		return "createCaptchasuccess";
		
		}
		
		private void SendEmail(String email,String captcha) {
			HtmlEmail send = new HtmlEmail();
			System.out.println("上面来的Email:"+email);
			try {
				send.setHostName("smtp.qq.com");
				send.setSmtpPort(465);// 端口号
				send.setSSLOnConnect(true);// 开启SSL加密
				send.setCharset("utf-8");
				send.addTo(email);// 接收者的QQEamil
				send.setFrom("2414726781@qq.com", "ANGEL");// 第一个参数是发送者的QQEamil  
															// 第二个参数是发送者QQ昵称
				System.out.println("打个断点--------------");
				send.setFrom(email, "ANGEL");
				System.out.println("打个断点--------------ttt");
				send.setAuthentication("2414726781@qq.com", "utasocknpehqdibh"); // 第一个参数是发送者的QQEamil
																					//  
																					// 第二个参数是刚刚获取的授权码
				send.setSubject("小渣渣特给您送上验证码：");// Eamil的标题  第一个参数是我写的判断上下午，删掉即可
				//send.setMsg("您的验证码为："+captcha);// Eamil的内容
				send.send();// 发送
			} catch (EmailException e) {
				e.printStackTrace();
			}
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

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
}
