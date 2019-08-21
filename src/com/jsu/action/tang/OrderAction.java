package com.jsu.action.tang;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.jsu.dao.qu.OrderDao;
import com.jsu.entity.Address;
import com.jsu.entity.Gift;
import com.jsu.entity.GiftParameter;
import com.jsu.entity.Order;
import com.jsu.entity.ShoppingCar;
import com.jsu.entity.User;

@Controller
public class OrderAction {
	
	@Resource(name="orderDao")
	private OrderDao dao;
	
	//商品list
	private List<Gift> giftList ;
	private List<GiftParameter> gpList;
	private List<ShoppingCar> scList;
	private String oid;
	private Address address;
	private String jsonData;
	private User user;
	private Order order;
	public String viewOrder(){
	
		//oid="232405e256ac";
		giftList = new ArrayList<Gift>();
		gpList = new ArrayList<GiftParameter>();
		scList = new ArrayList<ShoppingCar>();

		System.out.println("查看订单了,看看oid:"+oid);
		
		order = dao.findOrderById(oid);
		if("".equals(order.getAccount())){
			System.out.println("支付定账号为空");
		} else {
			System.out.println("支付定账号不为空");
			//根据giftid查询礼品,先分割开giftid
			String[] giftid = order.getgId().split(",");
			for(String id:giftid){
				System.out.println(id);
				giftList.add(dao.findGiftById(id));				
			}
			System.out.println("------------------我是分隔线----------------");
			
			//根据gpid查询礼品,先分割开gpid
			String[] gpid = order.getgPId().split(",");
			for(String id:gpid){
				System.out.println(id);
				gpList.add(dao.findGiftParameterById(id));				
			}
			System.out.println("------------------我是分隔线2----------------");
			//根据addressid查询地址
			address = dao.findAddressById(order.getAddressId());
			
			//根据userid查询用户
			user = dao.findUserById(order.getuId());
			//数量
			//String[] nums = order.getNum().split(",");
			
		    System.out.println("遍历gpList");
		    for(GiftParameter gp:gpList)
		    	System.out.println(gp);
		    System.out.println("遍历giftList");
		    for(Gift gp:giftList)
		    	System.out.println(gp);
			
			//查看订单，利用购物车实体类属性，所以在前面new了List<ShoppingCar>
			//需有照片，礼品名称，礼品小标题（参数名称），颜色（），单价，数量，小计，运费，总计
			/*for(int i=0;i<giftList.size();i++){
				double price = gpList.get(i).getPrice()*Integer.parseInt(nums[i]);
				scList.add(new ShoppingCar(gpList.get(i).getImage(),giftList.get(i).getTitle(),gpList.get(i).getTitle(),gpList.get(i).getType(),gpList.get(i).getPrice(),Integer.parseInt(nums[i]),price,giftList.get(i).getFare()));
			}*/
			System.out.println("scList大小："+scList.size());
			
			
			System.out.println("giftList大小："+giftList.size());
			System.out.println("gpList大小："+gpList.size());
			
			
			//request.setAttribute("scList", scList);
		}
		//给前端发送JSON数据
		System.out.println("开始发送JSON数据");
		JSONObject obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("msg", "更新成功！！");
		obj.put("order", order);
		jsonData = obj.toString();
		System.out.println(obj.toString());
		System.out.println("order:"+order);
		
		
		return "success";
	}

	public List<ShoppingCar> getScList() {
		return scList;
	}

	public void setScList(List<ShoppingCar> scList) {
		this.scList = scList;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderDao getDao() {
		return dao;
	}

	public void setDao(OrderDao dao) {
		this.dao = dao;
	}

	public List<Gift> getGiftList() {
		return giftList;
	}

	public void setGiftList(List<Gift> giftList) {
		this.giftList = giftList;
	}

	public List<GiftParameter> getGpList() {
		return gpList;
	}

	public void setGpList(List<GiftParameter> gpList) {
		this.gpList = gpList;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	

}
