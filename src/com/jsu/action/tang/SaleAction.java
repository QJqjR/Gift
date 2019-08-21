package com.jsu.action.tang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.jsu.dao.tang.SaleDao;
import com.jsu.entity.Gift;
import com.jsu.entity.GiftParameter;
import com.jsu.entity.Order;
import com.jsu.entity.Refund;
import com.jsu.entity.ShoppingCar;
import com.jsu.entity.User;
import com.jsu.util.StasisAgeUtil;

@Controller
public class SaleAction {
	@Resource(name = "saleDao")
	private SaleDao dao;

	// 前十销售榜
	private List<Gift> giftList;
	// 礼品列表
	private List<Order> orderList;
	// 礼品统计数组
	private int[] tentop = new int[10];
	// 暂存订单表giftid和o_num
	private Map<String, Integer> tentopMaps;
	private String[] giftid;
	private String[] nums;
	private int limit;
	private int offset;

	// 月销售
	private Map<String, Integer> jsonSale;
	private JSONObject obj;

	// 销售前十
	public String tenTopGiftList() {
		boolean flag;
		tentopMaps = new HashMap<String, Integer>();
		giftList = new ArrayList<Gift>();
		System.out.println("来躲猫猫了");

		orderList = dao.findTenTop();

		for (Order order : orderList) {
			System.out.println("giftid:" + order.getgId() + ",num:"
					+ order.getNum());
			giftid = order.getgId().split(",");
			nums = order.getNum().split(",");
			for (int i = 0; i < giftid.length; i++) {
				flag = tentopMaps.containsKey(giftid[i]);
				System.out.println("来看看有没有重复的key:" + flag + ",那么值是："
						+ giftid[i]);
				if (flag) {
					tentopMaps.put(giftid[i], Integer.parseInt(nums[i])
							+ tentopMaps.get(giftid[i]));
				} else {
					tentopMaps.put(giftid[i], Integer.parseInt(nums[i]));
				}
			}
		}

		System.out.println("-----遍历完了吧，别墨迹，赶紧显示-------");
		for (Entry<String, Integer> top : tentopMaps.entrySet()) {
			System.out.println("key:" + top.getKey() + ",value:"
					+ top.getValue());
		}
		System.out.println("来了来了，大家来排序了----------");
		// 将map.entrySet()转换成list
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
				tentopMaps.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			// 降序排序
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		System.out.println("-------------");
		// 封面图片，和标题
		int i = 0;
		for (Map.Entry<String, Integer> mapping : list) {
			if (i == 10)
				break;
			System.out.println(mapping.getKey() + ":" + mapping.getValue());
			giftList.add(dao.findGiftByGiftid(mapping.getKey()));
			i++;
		}
		System.out.println("giftLis:" + giftList.size());
		JSONArray json = JSONArray.fromObject(tentopMaps);
		obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("results", json.toString());
		return "success";
	}

	// 月销售
	public String monthSale() {
		System.out.println("月销售来了--------");
		boolean flag;
		jsonSale = new TreeMap<String, Integer>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String toDay = df.format(new Date());
		System.out.println("今天日期：" + toDay);

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date m = c.getTime();
		String monDay = df.format(m);
		System.out.println("过去一个月：" + monDay);

		orderList = dao.findMonSale(toDay, monDay);

		for (Order order : orderList) {
			nums = order.getNum().split(",");
			for (int i = 0; i < nums.length; i++) {
				flag = jsonSale.containsKey(order.getTime());
				if (flag) {
					System.out.println("有一样的日期");
					jsonSale.put(order.getTime(), jsonSale.get(order.getTime())
							+ Integer.parseInt(nums[i]));
				} else {
					System.out.println("没有一样的日期");
					jsonSale.put(order.getTime(), Integer.parseInt(nums[i]));
				}
			}

		}
		System.out.println("显示 ----------");
		for (Entry<String, Integer> top : jsonSale.entrySet()) {
			System.out.println("key:" + top.getKey() + ",value:"
					+ top.getValue());
		}
		JSONArray json = JSONArray.fromObject(jsonSale);
		obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("results", json.toString());
		obj.put("toDay", toDay);
		obj.put("monDay", monDay);

		return "success";
	}

	// 根据用户年龄划分阶段，0-20，20-35，36-50，50-寿终
	// 在每个年龄阶段进行统计，来自tb_order表，统计g_id和o_num，得出靠前的个礼品
	// 由此得到，年龄有四个阶段，每个阶段又有靠前的三个礼品
	public String statisAge() {
		System.out.println("年龄阶段有统计了");
		List<User> userList = dao.findAllUser();
		System.out.println("用户数量：" + userList.size());
		String[] age = new String[] { "0~20", "21~30", "31~40", "41~50",
				"51~60", ">60" };
		List<TreeMap<String, Integer>> listAll = new StasisAgeUtil().util(
				userList, dao);
		System.out.println("listAll大小：" + listAll.size());
		for (int i = 0; i < listAll.size(); i++) {
			TreeMap<String, Integer> treeMap = listAll.get(i);
			System.out.println("----------遍历treeMap------------");
			System.out.println(age[i]);
			for (Entry<String, Integer> treee : treeMap.entrySet()) {
				System.out.println("key:" + treee.getKey() + ",value:"
						+ treee.getValue());
			}
		}
		obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		for(int i = 0; i < listAll.size(); i++){
			JSONArray json = JSONArray.fromObject(listAll.get(i));			
			obj.put("results"+i, json.toString());
		}		
		return "success";
	}
	//显示订单列表, oid,用户id ,礼品标题,支付宝交易账号,交易时间,礼品具体参数,颜色,交易金额,交易状态,交易备注,交易数
	public String showOrderList(){
		
		
		
		System.out.println("显示订单列表");
		List<ShoppingCar> scList = new ArrayList<ShoppingCar>();
		Gift gift;
		GiftParameter gp;
		System.out.println(offset+limit);
		orderList = dao.findAllOrder(offset,limit);
		System.out.println("得到所有订单了，开始显示了");
		for(Order order:orderList){
			String[] giftid = order.getgId().split(",");
			String[] gpid = order.getgPId().split(",");
			String[] num = order.getNum().split(",");
			/*String[] msg=null;
			if(order.getMsg()==null||"".equals(order.getMsg())){
				msg=null;
			}else{*/
			String[] msg = order.getMsg().split(",");	
			/*}*/
			System.out.println("*********************************");

			for(int i=0;i<giftid.length;i++){
				gift = dao.findGiftByGiftid(giftid[i]);
				gp = dao.findGiftParameterByGpid(gpid[i]);
				System.out.println(gift);
				System.out.println(gp);
					System.out.println("msg大小："+msg.length);
					System.out.println("msg大小："+giftid.length);
				if(i<msg.length){
					System.out.println("有备注:"+msg[i]);
					System.out.println("订单号："+order.getoId());
					System.out.println("giftid:"+gift.getId()+",gpid:"+gp.getId());
					scList.add(new ShoppingCar(order.getoId(),order.getuId(),gift.getTitle(),order.getAccount(),order.getTime(),gp.getTitle(),gp.getType(),Double.parseDouble(order.getMoney()),order.getType(),msg[i],Integer.parseInt(num[i])));	
				}else {
					System.out.println("信息为空");
					scList.add(new ShoppingCar(order.getoId(),order.getuId(),gift.getTitle(),order.getAccount(),order.getTime(),gp.getTitle(),gp.getType(),Double.parseDouble(order.getMoney()),order.getType(),"",Integer.parseInt(num[i])));
				}

			}
		}
		System.out.println("数据遍历完毕！！！！！！！");
		for(ShoppingCar sc:scList)
			System.out.println(sc);
		
		JSONArray json = JSONArray.fromObject(scList);
		System.out.println(json);
		obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("results", json.toString());
		
		
		return "success";
	}
	//显示退款列表,退款id，订单号，交易账号，用户账号，礼品标题，礼品参数，礼品类型，发起退款时间，退款金额，退款原因，退款理由，退款照片
	public String showRefundList(){
		List<Refund> refundList = new ArrayList<Refund>();
		Refund refund ;
		Gift gift;
		GiftParameter gp;
		
		System.out.println("------先是查找订单状态为2的数据--------");
		orderList = dao.findOrderByStatus(2);
		System.out.println("orderList:"+orderList.size());
		for(Order order:orderList){
			
			
			refund = dao.findRefundByRid(order.getoId());
			gift = dao.findGiftByGiftid(refund.getGid());
			gp = dao.findGiftParameterByGpid(refund.getGpid());
			String []images = refund.getImage().split(",");
			System.out.println("---------------");
			System.out.println("gid:"+refund.getGid());
			System.out.println("gpid:"+refund.getGpid());
			
			
			System.out.println(gift);
			System.out.println(gp);
			System.out.println(refund);
			if(refund!=null){
				System.out.println("此订单的退款有记录");
				refundList.add(new Refund(refund.getId(),refund.getOid(),refund.getGid(),refund.getGpid(),refund.getAccount(),refund.getUid(),gift.getTitle(),gp.getTitle(),gp.getType(),refund.getTime(),refund.getMoney(),refund.getCause(),refund.getDesc(),images[0]));
			}
		}
		System.out.println("输出refundList大小："+refundList.size());

		JSONArray json = JSONArray.fromObject(refundList);
		obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("results", json.toString());
		return "success";
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

	public int[] getTentop() {
		return tentop;
	}

	public void setTentop(int[] tentop) {
		this.tentop = tentop;
	}

	public SaleDao getDao() {
		return dao;
	}

	public void setDao(SaleDao dao) {
		this.dao = dao;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	

}
