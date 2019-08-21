package com.jsu.action.chen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.jsu.dao.liu.GiftDao;
import com.jsu.dao.tang.CollectDao;
import com.jsu.entity.Collect;
import com.jsu.entity.Gift;


@Controller
public class FindCollectAction {
	@Resource
	private CollectDao collectDao;
	@Resource(name="giftDao")
	private GiftDao gdao;
    private String uid;
    private String gid;
    private String time;
    private int pages;
    private int page;
    private int pagesize;
    private String jsondata;
    
    public String findcollect(){
    	/*uid="10095";
    	pagesize=20;*/
    	pages=collectDao.findpages(pagesize, uid);
    	if(page>pages){
    		page=pages;
    	}else if(page<1){
    		page=1;
    	}
    	
    	ArrayList<String> gifts=new ArrayList<String>();
    	List<Collect> list=collectDao.findcollects(page, pagesize, uid);
    	System.out.println("收藏列表长度："+list.size());
    	
    	for(int i=0;i<list.size();i++){
    		gifts.add(list.get(i).getgId());
    		System.out.println(list.get(i).getgId());
    	}

    	System.out.println(gifts.size());
    	
    	ArrayList<Gift> gift=new ArrayList<Gift>();
    	for(int i=0;i<gifts.size();i++){
    		gift.add(gdao.findGiftById(gifts.get(i)));
    		System.out.println(gifts.get(i));
    	}
    	
    	JSONArray json=JSONArray.fromObject(gift);
    	JSONObject obj=new JSONObject();
    	if(list.size()>0){
    		obj.put("code", 200);
    		obj.put("status", 1);
    		obj.put("pages", pages);
    		obj.put("results", json);
    	}else{
    		obj.put("code", 400);
    		obj.put("status", 0);
    		obj.put("pages", pages);
    		obj.put("results", json);
    	}
    	jsondata=obj.toString();
    	return "success";
    }
    
	public CollectDao getCollectDao() {
		return collectDao;
	}
	public void setCollectDao(CollectDao collectDao) {
		this.collectDao = collectDao;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public String getJsondata() {
		return jsondata;
	}
	public void setJsondata(String jsondata) {
		this.jsondata = jsondata;
	}
    
}
