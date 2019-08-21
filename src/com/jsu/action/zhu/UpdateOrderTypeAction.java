package com.jsu.action.zhu;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.jsu.dao.liu.GiftParamDao;
import com.jsu.dao.qu.OrderDao;
import com.jsu.dao.qu.RefundDao;
import com.jsu.dao.qu.UserDao;
import com.jsu.entity.GiftParameter;
import com.jsu.entity.Order;
import com.jsu.entity.Refund;
import com.jsu.entity.User;

@Controller
public class UpdateOrderTypeAction {
	private String oid;
	private String account;
	private int type;
	private String uid;
	private String rid;
	private String gpid;
	@Resource
	private OrderDao dao;
	@Resource
	private RefundDao rdao;
	@Resource(name="userDao")
	private UserDao udao;
	@Resource(name="giftParamDao")
	private GiftParamDao gpdao;	
	private String jsonData;
	public String cancelOrder(){
		
		System.out.println("前台传过来的退款id:"+rid);
		System.out.println("开始退款订单");
		//测试
		/*rid="57fe9975db97";
		gpid="5fcd3bf52609";
		oid="143d8170eb90";
		account="201908138d5019e5b84a143d8170eb90";
		uid="8d5019e5b84a";*/
		
		//根据oid查找gpid列表和num列表和money列表
		Order order=dao.findOrderById(oid);
		String []gpids=order.getgPId().split(",");
		String []nums=order.getNum().split(",");
		String []moneys=order.getMoney().split(",");
		System.out.println("列表遍历完了");
		
		int  num = 0;
		double money = 0;
		for(int i=0;i<gpids.length;i++){
			if(gpid.equals(gpids[i])){
				num=Integer.parseInt(nums[i]);
				money=Double.parseDouble(moneys[i]);
				System.out.println("列表下标："+i);
				break;				
			}
		}
		
		//根据oid查找用户相关信息,并确定退款金额
		User user=udao.findUserById(uid);
		System.out.println("是你错了吗？");
		double upurse=Double.parseDouble(user.getPurse());
		double rmoney=num*money;
		System.out.println("最后应退款金额："+rmoney);
		
		//订单状态为2，表示已经在申请退款状态
		if(order.getType()==2){
			
			//改order状态为3，表示确认退款
			dao.updateType(oid, account, 3);
			
			//确认后把货款退给客户
			user.setPurse((upurse+rmoney)+"");
			//商家扣掉对应的金额
			User superUser=udao.findUserById("0d2acf280422");
			superUser.setPurse(Double.parseDouble(superUser.getPurse())-rmoney+"");
			udao.updateUser(user);
			udao.updateUser(superUser);
			
			//货物退还给商家库存
			GiftParameter gp=gpdao.findGParameterById(gpid);
			gp.setReseve(gp.getReseve()+num);
			gpdao.updateGParam(gp);
			
			//查看order表状态
			order=dao.findOrderById(oid);
			System.out.println("修改后order的状态："+order.getTime());
			
		}
		
		JSONObject obj=new JSONObject();
    	obj.put("code", 200);
   	    obj.put("status", 1);
   	    obj.put("msg", "订单类型修改成功！");
   	    obj.put("refundStatus", order.getType());
   	    jsonData=obj.toString();
   	    System.out.println(obj.toString());
   	    return "success";
	}
	
	
	
	
	//确认收货
	public String comfirmGift(){
		/*oid="232405e256ac";
		account="201908120d2acf28bf53232405e256ac";*/
	
		System.out.println("开始确认收货");
		System.out.println("把type改为0");
		System.out.println("确认订单的oid:"+oid);
		System.out.println("确认订单的用户id:"+uid);
		dao.updateType(oid, account, 0);
		
		JSONObject obj=new JSONObject();
    	obj.put("code", 200);
   	    obj.put("status", 1);
   	    obj.put("msg", "确认收货！");
   	    jsonData=obj.toString();
   	    System.out.println(obj.toString());
   	    return "success";
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public OrderDao getDao() {
		return dao;
	}

	public void setDao(OrderDao dao) {
		this.dao = dao;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public RefundDao getRdao() {
		return rdao;
	}

	public void setRdao(RefundDao rdao) {
		this.rdao = rdao;
	}

	public UserDao getUdao() {
		return udao;
	}

	public void setUdao(UserDao udao) {
		this.udao = udao;
	}

	public GiftParamDao getGpdao() {
		return gpdao;
	}

	public void setGpdao(GiftParamDao gpdao) {
		this.gpdao = gpdao;
	}

	public String getGpid() {
		return gpid;
	}

	public void setGpid(String gpid) {
		this.gpid = gpid;
	}
	
	
}
