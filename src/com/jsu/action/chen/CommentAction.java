package com.jsu.action.chen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.jsu.dao.chen.CommentDao;
import com.jsu.dao.liu.GiftDao;
import com.jsu.dao.liu.GiftParamDao;
import com.jsu.dao.qu.UserDao;
import com.jsu.entity.Comment;
import com.jsu.entity.Gift;
import com.jsu.entity.User;
import com.jsu.util.CreateIdUtil;

@Controller
public class CommentAction {
	@Resource
	private Comment comment;
	@Resource
	private CommentDao commentDao;
	@Resource(name="userDao")
	private UserDao udao;
	@Resource(name="giftDao")
	private GiftDao gdao;
	private String id;
	private String gid;
	private String uid;
	private String detail;
	private String redetail;
	private String time;
	private String image;
	private int rate;//数字越大，等级越高
	private int page;
	private int pages;
	private int pagesize;
	private List<Comment> list;
	private String jsondata;
    private int choice;
    private int content;
    private int limitset;
    private int offset;
    
	public String addcomment() {
		Comment comment = new Comment();
		id = CreateIdUtil.getId();
		comment.setId(id);
		
		comment.setgId(gid);
		comment.setuId(uid);
		System.out.println(gid + "||" + uid);
		comment.setDetail(detail);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		time = sdf.format(new Date());
		// System.out.println(time);
		comment.setTime(time);
		comment.setImage(image);
		comment.setRate(rate);
		comment.setReview(0);
		// System.out.println(comment);
		// System.out.println(comment.getId()+"||"+comment.getgId()+"||"+comment.getuId()+"||"+comment.getTime()+"||"+comment.getRedetail());
		commentDao.addComment(comment);
		
		Gift gift=gdao.findGiftById(gid);
		gift.setComment(gift.getComment()+1);
		gdao.updategift(gift);
		
		JSONObject obj = new JSONObject();
		if (commentDao.findcommentid(id)) {
			obj.put("code", 200);
			obj.put("status", 1);
		} else {
			obj.put("code", 400);
			obj.put("status", 0);
		}
		jsondata = obj.toString();
		return "success";
	}

	public String updatecomment() {
		JSONObject obj = new JSONObject();
		Comment comment = commentDao.findComment(id);
		comment.setRedetail(redetail);
		commentDao.updateComment(comment);
		if(commentDao.findComment(id).getRedetail()==redetail){
			obj.put("code", 200);
			obj.put("status", 1);
		}else{
			obj.put("code", 400);
			obj.put("status", 0);
		}
		jsondata = obj.toString();
		return "success";
	}

	public String findcomment() {
		
		/*pagesize=24;*/
		/*gid="8e7b2c11bb47";
		choice=5;*/
		if(pagesize==0){
			pagesize=20;
		}
		System.out.println(page + " | " + pagesize + " | " + gid);
		pages=commentDao.findpages(pagesize, gid,choice,content);
		if(page>pages){
    		page=pages;
    	}else if(page<1){
    		page=1;
    	}
		System.out.println("------------------------------");
		list = commentDao.findComments(page, pagesize, gid,choice,content);
		
		ArrayList<Comment> comments=new ArrayList<Comment>();
		System.out.println("list的长度为："+list.size());
		for(int i=0;i<list.size();i++){
			System.out.println("---------------------");
			User user=udao.findUserById(list.get(i).getuId());
			System.out.println(user.getImage());
			System.out.println(user.toString());
			System.out.println("_____________________________");
			Gift gift=gdao.findGiftById(list.get(i).getgId());
			System.out.println("*******************************");
			Comment comment=new Comment(user.getId(),list.get(i).getDetail(),list.get(i).getRedetail(),
					list.get(i).getTime(),list.get(i).getImage(),list.get(i).getRate(),user.getEmail(),user.getImage(),
					gift.getTitle(),user.getName());
			
			comments.add(comment);
			
		}
		
		 JSONArray json=JSONArray.fromObject(comments);
		 JSONObject obj=new JSONObject();
    	 obj.put("code", 200);
     	 obj.put("status", 1);
     	 obj.put("results", json);
     	 jsondata=obj.toString();
     	 return "success";
     	 
		
		/*JSONArray json = JSONArray.fromObject(list);
		JSONObject obj = new JSONObject();
		if (list.size() != 0) {
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

		jsondata = obj.toString();
		System.out.println(jsondata);
		return "success";*/
	}

	
	
	public String findAllComment(){
		/*gid="97c6f3651415";*/
		if(limitset==0){
			limitset=20;
			offset=0;
		}
		
		System.out.println("查询该商品的所有评论，该商品的gid为："+gid);
		//List<Comment> list=commentDao.findAllComment(gid);
		System.out.println("前端过来的limitset:"+limitset+"   "+"offset:"+offset);
		ArrayList<Comment> comments=new ArrayList<Comment>();
		
		List<Comment> list=commentDao.findCommentBySize(limitset, offset, gid);
		System.out.println("list的长度为："+list.size());
		for(int i=0;i<list.size();i++){
			System.out.println("---------------------");
			User user=udao.findUserById(list.get(i).getuId());
			System.out.println("_____________________________");
			Gift gift=gdao.findGiftById(list.get(i).getgId());
			System.out.println("*******************************");
			Comment comment=new Comment(user.getId(),list.get(i).getDetail(),list.get(i).getRedetail(),
					list.get(i).getTime(),list.get(i).getImage(),list.get(i).getRate(),user.getEmail(),user.getImage(),
					gift.getTitle(),user.getName());
			
			comments.add(comment);
			
		}
		
		 JSONArray json=JSONArray.fromObject(comments);
		 JSONObject obj=new JSONObject();
    	 obj.put("code", 200);
     	 obj.put("status", 1);
     	 obj.put("results", json);
     	 jsondata=obj.toString();
     	 return "success";
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/*
	 * public String getgId() { return gId; } public void setgId(String gId) {
	 * this.gId = gId; } public String getuId() { return uId; } public void
	 * setuId(String uId) { this.uId = uId; }
	 */
	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getRedetail() {
		return redetail;
	}

	public void setRedetail(String redetail) {
		this.redetail = redetail;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
     
	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public CommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
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

	public List<Comment> getList() {
		return list;
	}

	public void setList(List<Comment> list) {
		this.list = list;
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

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public int getContent() {
		return content;
	}

	public void setContent(int content) {
		this.content = content;
	}

	public int getLimitset() {
		return limitset;
	}

	public void setLimitset(int limitset) {
		this.limitset = limitset;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public UserDao getUdao() {
		return udao;
	}

	public void setUdao(UserDao udao) {
		this.udao = udao;
	}

	public GiftDao getGdao() {
		return gdao;
	}

	public void setGdao(GiftDao gdao) {
		this.gdao = gdao;
	}

	
	
     
}
