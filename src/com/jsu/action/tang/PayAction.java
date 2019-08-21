package com.jsu.action.tang;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.jsu.dao.qu.OrderDao;
import com.jsu.dao.tang.PayDao;
import com.jsu.entity.Order;

@Controller
public class PayAction {
	@Resource(name="payDao")
	private PayDao dao;
	@Resource(name="orderDao")
	private OrderDao odao;
	private String jsonData;
	private String oid;
	private String payid;
	
	//根据oid更新accountid
	public String updatePayAccount() throws IOException{
		/*HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse reponse = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		String oid = request.getParameter("oid");
		String payid = request.getParameter("payid");*/
		//oid="014";
		//payid="njbjsbi";
		Order order=new Order();
		System.out.println("oid:"+oid+",payid:"+payid);
		System.out.println("开始更新");
		dao.updatePayIdByOid(oid, payid);
		System.out.println("更新完");
		order=odao.findOrderById(oid);
		//给前端发送JSON数据
		System.out.println("开始发送JSON数据");
		JSONObject obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("msg", "更新成功！！");
		obj.put("order", order);
		jsonData = obj.toString();
		System.out.println(obj.toString());
		return "success";
		//reponse.getWriter().write(1);
		//return null;
	}

	public PayDao getDao() {
		return dao;
	}

	public void setDao(PayDao dao) {
		this.dao = dao;
	}

	public OrderDao getOdao() {
		return odao;
	}

	public void setOdao(OrderDao odao) {
		this.odao = odao;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getPayid() {
		return payid;
	}

	public void setPayid(String payid) {
		this.payid = payid;
	}
	

}
