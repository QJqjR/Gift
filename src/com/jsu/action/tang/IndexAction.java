package com.jsu.action.tang;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.jsu.dao.tang.IndexDao;
import com.jsu.entity.Collect;
import com.jsu.entity.Gift;
import com.jsu.entity.User;
import com.jsu.util.ArrayListOnly;

@Controller
public class IndexAction {
	@Resource(name = "indexDao")
	private IndexDao dao;

	// 用户登录userid
	private String userid;
	// 用户登录的爱好
	private String[] hobby;
	private int limit;
	private int offset;
	private JSONObject obj;

	// 存储主页上的礼品Gift
	private List<Gift> giftList = new ArrayList<Gift>();
	private List<Gift> list = new ArrayList<Gift>();
	private List<Gift> gift = new ArrayList<Gift>();

	// 跳转主页前读取数据
	public String indexAction() {
		/*HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();*/

		/*userid = "0d2acf28bf53";*/

		System.out.println("假设传过来的userid:" + userid);

//		if (userid.equals("")) {
//
//			System.out.println("当前用户为空，未登录或爱好没有填写");
//
//			
//
//		} else {
//
//			System.out.println("当前用户已登录");
//			// 根据userid读取当前用户全部信息
//			User user = dao.findByUserId(userid);
//			System.out.println("hobby:"+user.getHobby());
//			if ("".equals(user.getHobby())|| user.getHobby() == null) {
//				System.out.println("当前用户已登录了，但是爱好选项没有填写，所以应默认前24条数据");
//
//				giftList = dao.findByIndex();
//			} else {
//				System.out.println("当前用户已登录，显示与爱好相匹配的24条数据");
//				hobby = user.getHobby().split(",");
//				System.out.println("遍历hobby");
//				for (String h : hobby)
//					System.out.println(h);
//				System.out.println("---------------------------");
//				for (String h : hobby) {
//					list = dao.findByUserHobby(h);
//					System.out.println("IndexAction大小：" + list.size());
//
//					for (Gift g : list)
//						gift.add(g);
//				}
//				System.out.println("遍历gift");
//				for (Gift g : gift)
//					System.out.println(g.toString());
//				// giftList去重
//				gift = ArrayListOnly.arrayListOnly(gift);
//				System.out.println("去重后的输出-------");
//				for (Gift g : gift)
//					System.out.println(g.toString());
//				if (gift.size() >= 24) {
//					System.out.println("giftList大于24个，该减少了");
//					giftList.clear();
//					for (int i = 0; i < 24; i++)
//						giftList.add(gift.get(i));
//				} else {
//					giftList.clear();
//					System.out.println("giftList小于24个，该增加");
//					list = dao.findByUserHobbyNum(24 - gift.size());
//					System.out.println("lisst大小:" + list.size());
//					for (Gift g : gift)
//						giftList.add(g);
//					
//					for (Gift g : list)
//						gift.add(g);
//					
//					giftList = gift;
//					giftList = ArrayListOnly.arrayListOnly(giftList);
//					System.out.println("小小输出一下");
//					System.out.println("***giftList大小："+giftList.size());
//					//for (Gift g : gift)
//					//	System.out.println(g);
//				}
//			}
//		}
//		if(offset == 0){
//			offset = 0;
//		}
//		if(limit == 0 ){
//			limit = 20;
//		}
		System.out.println(offset+"\t"+limit);
		giftList = dao.findByIndex(offset,limit);
		
		System.out.println("giftList大小："+giftList.size());

		
		String str[] ;
//		for (Gift g : giftList){
//			str = g.getImage().split(",");
//			g.setImage(str[0]);
//		}
		System.out.println("------------遍历giftList-------------");
//		for (Gift g : giftList){
//			System.out.println(g);
//		}	
		
		/*session.setAttribute("giftList", giftList);
		System.out.println("giftList大小："+giftList.size());*/
		
		
		System.out.println("当前用户查询成功了");
		
		
		
		
		JSONArray json = JSONArray.fromObject(giftList);
		//JSONArray jsonCollect = JSONArray.fromObject(collectList);
		obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("results", json.toString());
		//obj.put("collectFalg", jsonCollect);
		System.out.println(json.toString());
		
		return "success";
	}

	public IndexDao getDao() {
		return dao;
	}

	public void setDao(IndexDao dao) {
		this.dao = dao;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public List<Gift> getGiftList() {
		return giftList;
	}

	public void setGiftList(List<Gift> giftList) {
		this.giftList = giftList;
	}

	public String[] getHobby() {
		return hobby;
	}

	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

	public List<Gift> getList() {
		return list;
	}

	public void setList(List<Gift> list) {
		this.list = list;
	}

	public List<Gift> getGift() {
		return gift;
	}

	public void setGift(List<Gift> gift) {
		this.gift = gift;
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
