package com.jsu.action.chen;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Controller;

import com.jsu.dao.qu.UserDao;
import com.jsu.entity.User;
import com.jsu.util.CreateIdUtil;

@Controller
public class UserAction {
	@Resource
	private UserDao userDao;
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
	private int status;
	private String jsondata;
	
	private int year;
	private String month;
	private String day;
	private String birthday;
	
	private String [] hobbys;
	
	public String finduser(){
		//User user=userDao.findUserById("10094");
		User user=userDao.findUserById(id);
		JSONArray json=JSONArray.fromObject(user);
		JSONObject obj=new JSONObject();
		if(user!=null){
			 obj.put("code", 200);
        	 obj.put("status", 1);
        	 obj.put("results", json);
       	 }else{
        	 obj.put("code", 400);
           	 obj.put("status", 0);
           	 obj.put("results", json);
        	 }
		 jsondata=obj.toString();
		 System.out.println(jsondata);
		return "success";
	}
	
		//显示用户信息
		public String showUserInfor(){
			//假设用户id是
			id = "10094";
			//根据用户userid查询用户信息
			User user = userDao.findUserById(id);
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			String date = df.format(new Date());
			
			String []arr = date.split("-");
			
			year = Integer.parseInt(arr[0]) - user.getAge();
			month = arr[1];
			day = arr[2];
			System.out.println("year:"+year+",month:"+month+",day:"+day);
			System.out.println("hobby:"+user.getHobby());
			String []arr2 = user.getHobby().split(",");
			hobbys = new String[4];
			int i=0;
			for(;i<arr2.length;i++){
				System.out.println(arr2[i]);
				hobbys[i] = arr2[i];
			}
			for(;i<4;i++){
				hobbys[i]="";
			}
			
			System.out.println("输出一下用户信息");
			System.out.println(user);
			
			JSONObject obj=new JSONObject();
			JSONObject usertemp=JSONObject.fromObject(user);
			//JSONObject habby=JSONObject.fromObject(hobbys);
	    	obj.put("code", 200);
	   	    obj.put("status", 1);
	   	    obj.put("user", usertemp);
	   	    obj.put("habby",user.getHobby());
	   	    obj.put("year", year);
	   	    obj.put("month", month);
	   	    obj.put("day", day);
	   	    obj.put("msg", "用户信息显示成功");
	   	    jsondata=obj.toString();
	   	    return "success";
		}
		
		//修改用户信息
		public String updateUserInfor(){
			//HttpServletRequest request = ServletActionContext.getRequest();
			System.out.println("更新用户信息了");
			/*String userid = request.getParameter("userid");
			String img = request.getParameter("img");
			String username = request.getParameter("username");
			String tel = request.getParameter("tel");
			String radio = request.getParameter("radio");
			String birthday =request.getParameter("birthday");
			String hobby = request.getParameter("hobby");*/
			System.out.println("userid:"+id+",img:"+image+",username:"+name+",tel:"+tel+",radio:"+sex+",birthday:"+birthday+",hobby:"+hobby);
			/*id="10092";
			tel="15274437616";*/
			User user=userDao.findUserById(id);
		
			if("".equals(birthday)||birthday==null){
				birthday="2001-01-01";
				System.out.println("brithday:"+birthday);
			}
			//根据birthday算出年龄
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			String date = df.format(new Date());
			System.out.println("今天日期："+date);
			System.out.println("出生日期："+birthday);
			
			String[] str1 = date.split("-");
			String[] str2 = birthday.split("-");
			System.out.println("str1:"+str1[0]);
			System.out.println("str2:"+str2[0]);
			System.out.println("----"+(Integer.parseInt(str1[0])-Integer.parseInt(str2[0])));
			//userDao.updateUserInforByUserid(id,image, tel, name, sex, Integer.parseInt(str1[0])-Integer.parseInt(str2[0]), hobby);
			user.setImage(image);
			user.setTel(tel);
			user.setName(name);
			user.setSex(sex);
			user.setAge(Integer.parseInt(str1[0])-Integer.parseInt(str2[0]));
			user.setHobby(hobby);
			userDao.updateUser(user);
			
			
			
			JSONObject o = new JSONObject();
			o.put("code", 200);
			o.put("status", 1);
			o.put("msg", "用户信息保存成功！");
			jsondata = o.toString();		
			System.out.println(jsondata);
			
			return "success";
		}
	
	
	
	
	public String resetPwd(){
		id="10091";
		//email="241472671@qq.com";
		
		User user = userDao.findUserById(id);
		String pwd=CreateIdUtil.getId();
		pwd=pwd.substring(0, 5);
		System.out.println("重置后的密码："+pwd);
		user.setPwd(pwd);
		userDao.updateUser(user);
		System.out.println("重置后的密码："+userDao.findUserById(id));
		SendEmail(user.getEmail(),user.getPwd());
		JSONObject obj=new JSONObject();
    	obj.put("code", 200);
   	    obj.put("status", 1);
   	    obj.put("msg", "重置密码成功");
   	    jsondata=obj.toString();
   	    return "success";
	}
	
	private void SendEmail(String email,String pwd) {
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
			send.setFrom(email, "ANGEL");
			send.setAuthentication("2414726781@qq.com", "utasocknpehqdibh"); // 第一个参数是发送者的QQEamil
																				//  
																				// 第二个参数是刚刚获取的授权码
			send.setSubject("小渣渣特给您送上重置密码："+pwd);// Eamil的标题  第一个参数是我写的判断上下午，删掉即可
			//send.setMsg("您的验证码为："+captcha);// Eamil的内容
			send.send();// 发送
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
    public String getJsondata() {
		return jsondata;
	}

	public void setJsondata(String jsondata) {
		this.jsondata = jsondata;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String[] getHobbys() {
		return hobbys;
	}

	public void setHobbys(String[] hobbys) {
		this.hobbys = hobbys;
	}

	

}
