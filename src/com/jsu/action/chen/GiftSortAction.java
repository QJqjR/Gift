package com.jsu.action.chen;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;


import com.jsu.dao.liu.GiftDao;
import com.jsu.entity.Gift;
import com.jsu.util.CreateIdUtil;

@Controller
public class GiftSortAction {
	@Resource
    private GiftDao giftDao;

	private String id;
    private String title;
    //价格排序
    private int psort;//1降序，0升序；
    private int pagesize;
    private String jsondata;
    private int pages;
    private List<String> list;
    private int page;
	
    
	public String findgift(){

		System.out.println("执行中。。。。。"+title);
		pages=giftDao.findpages(pagesize, title);
		if(page>pages){
    		page=pages;
    	}else if(page<1){
    		page=1;
    	}
		List<Gift> list=giftDao.findGift(page, pagesize, title);
		System.out.println("查询列表长度："+list.size());
		while(list.size()<=0){
			if(title.length()==0){
				break;
			}
            title=title.substring(0, title.length()-1);
            pages=giftDao.findpages(pagesize, title);
			list=giftDao.findGift(page, pagesize, title);
			
		}
		System.out.println("执行中。。。。。"+list.size());
		JSONArray json=JSONArray.fromObject(list);
		JSONObject obj=new JSONObject();
		if (title.length()!=0) {
			obj.put("code", 200);
			obj.put("status", 1);
			obj.put("pages", pages);
			obj.put("results", json);
		} else {
			obj.put("code", 400);
			obj.put("status", 0);
			obj.put("pages", pages);
			obj.put("results", json);
		}
		jsondata=obj.toString();
		System.out.println(jsondata);
		return "success";		
	}
	
	
	public String giftSort(){
		System.out.println("前端的psort:"+psort);
		List<Gift> gift=giftDao.giftSort(psort);
		if(pagesize==0){
			pagesize=20;
		}
		pages=giftDao.findpages(pagesize, title);
		JSONArray json=JSONArray.fromObject(gift);
		JSONObject obj=new JSONObject();
			obj.put("code", 200);
			obj.put("status", 1);
			obj.put("pages", pages);
			obj.put("results", json);
			jsondata=obj.toString();
			System.out.println(jsondata);
		return "success";
	}

	public String sortByKwd(){
		/*page=1;
		pagesize=5;
		title="女友";
		psort=1;*/
		List<Gift> gift=giftDao.sortByPwd(page, pagesize, title, psort);
		pages=giftDao.findpages(pagesize, title);
		JSONArray json=JSONArray.fromObject(gift);
		JSONObject obj=new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("pages", pages);
		obj.put("results", json);
		jsondata=obj.toString();
			System.out.println(jsondata);
		return "success";
	}
	
	public GiftDao getGiftDao() {
		return giftDao;
	}


	public void setGiftDao(GiftDao giftDao) {
		this.giftDao = giftDao;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getPsort() {
		return psort;
	}


	public void setPsort(int psort) {
		this.psort = psort;
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


	public int getPages() {
		return pages;
	}


	public void setPages(int pages) {
		this.pages = pages;
	}


	public List<String> getList() {
		return list;
	}


	public void setList(List<String> list) {
		this.list = list;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}
	
	/*public String SortByKeyword(){
		return "success";
	}*/
	
	
	
	
}