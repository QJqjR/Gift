package com.jsu.action.qu;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.jsu.dao.qu.AddressDao;
import com.jsu.entity.Address;
import com.jsu.util.CreateIdUtil;

@Controller
public class AddAddressAction {
	@Resource(name="addressDao")
	private AddressDao dao;
	private String id;
	private String userid;
	private int type;
	private String name;
	private String tel;
	private String address;
	private Address addre;
	private String jsonData;
	
	public String addAddress(){
		addre=new Address();
		id=CreateIdUtil.getId();
		System.out.println("收货地址id:"+id);
		addre.setId(id);
		addre.setuId(userid);
		System.out.println("前端页面的userid："+userid);
		//addre.setuId("b8c2d31ec34a");
		addre.setType(type);
		System.out.println("前端页面的type:"+type);
		addre.setName(name);
		addre.setTel(tel);
		addre.setAddress(address);
		//addre.setAddress("青青草原羊村");
		dao.AddAddress(addre);
		System.out.println("开始查询");
		addre=dao.findAddressById(id);
		System.out.println("查询完成");
		
		//给前端发送JSON数据
		System.out.println("开始发送JSON数据");
		JSONObject obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("address", addre);
		obj.put("msg", "地址添加成功！！");
		jsonData = obj.toString();
		System.out.println(obj.toString());
		return "success";
	}
	
	
	public String findAllAddress(){
		System.out.println("前端userid："+userid);
		List<Address> address=dao.findAllAddress(userid);
		//给前端发送JSON数据
		System.out.println("开始发送JSON数据");
		JSONObject obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("address",address);
		obj.put("msg", "所有地址查询成功！！");
		jsonData = obj.toString();
		System.out.println(obj.toString());
		return "success";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public AddressDao getDao() {
		return dao;
	}

	public void setDao(AddressDao dao) {
		this.dao = dao;
	}

	public Address getAddre() {
		return addre;
	}

	public void setAddre(Address addre) {
		this.addre = addre;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	

}
