package com.jsu.action.qu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.jsu.dao.qu.OrderDao;
import com.jsu.entity.Gift;
import com.jsu.entity.GiftParameter;
import com.jsu.entity.Order;
import com.jsu.entity.ShoppingCar;

@Controller
public class AllOrderAction {
	@Resource(name="orderDao")
	private OrderDao dao;
	private String id;
	private List<Order> orders;
	private String jsonData;
	private List<ShoppingCar> scList;
	private Gift gift;
	private GiftParameter gp;
	
	 
	//展示所有剁手记录
	public String showAllOrder(){
	    List<GiftParameter> gpList ;
		scList = new ArrayList<ShoppingCar>();
		System.out.println("开始查询所有剁手记录");
//		id="10096";
		System.out.println("userid:"+id);
		orders=dao.findAllOrder(id);
//		orders=dao.findAllOrder("0d2acf28bf53");
		//ArrayList<String> gids=new ArrayList<String>();
		ArrayList<String> gpids=new ArrayList<String>();
		String [] gids = null;
		String [] gpid = null;
		String [] nums = null;
		
		 for(Order order : orders) {
			//System.out.println(order.getTime());
			 System.out.println("礼品："+order.getgId());
			// gids.add(order.getgId().split(","));
			 gids=order.getgId().split(",");
			 System.out.println("gid列表："+gids);
			 System.out.println(order.getgPId());
			 System.out.println(order.getgId());
			 gpid = order.getgPId().split(",");
			 System.out.println("-------------------------------------------");
			for(String g:gpid){
				System.out.println("朋友看看"+g);
			}
			 nums = order.getNum().split(",");
			 for(int i=0; i<gids.length; i++){
				 System.out.println("gids[i]:"+gids[i]); 
				 System.out.println("gpid[i]:"+gpid[i]);
				 //通过gid查找礼品
				 gift = dao.findGiftById(gids[i]);
				 gpList = dao.findGiftParameter2ById(gids[i], gpid[i]);
				 System.out.println(gift.toString());
				 gp = gpList.get(0);
				 System.out.println(gp.toString());
				 
				 System.out.println("gpList有多大啊");
				 
				 //订单号,大标题，参数图片，参数名称，参数类型，邮费，数量，，价钱，
				scList.add(new ShoppingCar(order.getoId(),gift.getTitle(),gp.getImage(),gp.getParameter(),gp.getType(),gift.getFare(),
						Integer.parseInt(nums[i]),gp.getPrice(),gift.getId(),gp.getId(),order.getAccount(),order.getTime(),order.getType()));
				 
				 System.out.println(scList.get(i).getgId()+"  "+scList.get(i).getgPid());
			 }
				
			 
			 System.out.println("礼品参数："+order.getgPId());
			 //gpids.add(e);
		 }
		 System.out.println("-----------------真真开始遍历了---------");
		 for(ShoppingCar sc:scList)
			 System.out.println(sc);
		 
		 //System.out.println("gids的长度："+gids.length);
			System.out.println("剁手次数："+orders.size());
			System.out.println("真棒，我没有手了-------------------------");
			
			//给前端发送JSON数据   
			System.out.println("开始发送JSON数据");
			JSONArray list = JSONArray.fromObject(scList);
			JSONObject obj = new JSONObject();
			obj.put("code", 200);
			obj.put("status", 1);
			obj.put("orders", list);
			obj.put("msg", "所有剁手记录查询成功");
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

	public OrderDao getDao() {
		return dao;
	}

	public void setDao(OrderDao dao) {
		this.dao = dao;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}


	
	
}
