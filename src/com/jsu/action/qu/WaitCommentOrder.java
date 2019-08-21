package com.jsu.action.qu;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.jsu.dao.qu.OrderDao;
import com.jsu.entity.Order;
@Controller
public class WaitCommentOrder {
	@Resource
	private OrderDao dao;
	private String id;
	private String jsonData;
	private int type;
	private List<Order> orders;
	
	public String showWaitCommOrder(){
		System.out.println("开始查询所有待评价剁手记录");
		//orders=dao.findOrderByType("b8c2d31ec34a",0);
		orders=dao.findOrderByType(id,type);
		 for(Order order : orders) {
			System.out.println(order.getTime());
		 }
			System.out.println("待评价剁手记录条数："+orders.size());
			
			//给前端发送JSON数据   
			System.out.println("开始发送JSON数据");
			JSONObject obj = new JSONObject();
			obj.put("code", 200);
			obj.put("status", 1);
			obj.put("orders", orders);
			obj.put("msg", "所有待评价剁手记录查询成功");
			jsonData = obj.toString();
			System.out.println(obj.toString());
			return "success";				
	}
	
	
	
	
	public OrderDao getDao() {
		return dao;
	}
	public void setDao(OrderDao dao) {
		this.dao = dao;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	

}
