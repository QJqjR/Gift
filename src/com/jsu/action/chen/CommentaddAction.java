package com.jsu.action.chen;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.jsu.dao.chen.CommentDao;
import com.jsu.dao.chen.CommentaddDao;
import com.jsu.entity.Comment;
import com.jsu.entity.Commentadd;
import com.jsu.util.CreateIdUtil;

@Controller
public class CommentaddAction {
	     @Resource
	     private CommentaddDao commentaddDao;
	     @Resource
	     private CommentDao commentDao;
	     private String id;
         private String cid;
         private String details;
         private String image;
         private String time;
         private String jsondata;
         
         public String addcommentadd(){
        	 Commentadd commentadd=new Commentadd();
        	 Comment comment=new Comment();
        	 comment=commentDao.findComment(cid);
        	 comment.setReview(1);        	
        	 commentDao.updateComment(comment);
        	 commentadd.setcId(cid);
        	 id=CreateIdUtil.getId();
        	 commentadd.setId(id);
        	 commentadd.setDetails(details);
        	 commentadd.setImage(image);
        	 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        	 time=sdf.format(new Date());
        	 commentadd.setTime(time);
        	 //System.out.println(commentadd.getId()+"||"+commentadd.getcId()+"||"+commentadd.getDetails()+"||"+commentadd.getImage()+"||"+commentadd.getTime());
        	 commentaddDao.addcommentadd(commentadd);
        	 JSONObject obj=new JSONObject();
        	 if(commentaddDao.findcommentaddid(id)&&commentDao.findComment(cid).getReview()==1){
        		 obj.put("code", 200);
             	 obj.put("status", 1);
        	 }else{
        		 obj.put("code", 400);
            	 obj.put("status", 0);
        	 }
        	 jsondata=obj.toString();
        	 return "success";
          }
         public String findcommentadd(){
        	 List<Commentadd> list=commentaddDao.findcommentadds(cid);
        	 JSONArray json=JSONArray.fromObject(list);
        	 JSONObject obj=new JSONObject();
        	 if(list.size()!=0){
        	 obj.put("code", 200);
         	 obj.put("status", 1);
         	 obj.put("results", json);
        	 }else{
         		obj.put("code", 400);
            	 obj.put("status", 0);
            	 obj.put("results", json);
         	 }
        	 jsondata=obj.toString();
        	 return "success";
         }
		public CommentaddDao getCommentaddDao() {
			return commentaddDao;
		}
		public void setCommentaddDao(CommentaddDao commentaddDao) {
			this.commentaddDao = commentaddDao;
		}
		public String getCid() {
			return cid;
		}
		public void setCid(String cid) {
			this.cid = cid;
		}
		public String getDetails() {
			return details;
		}
		public void setDetails(String details) {
			this.details = details;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getJsondata() {
			return jsondata;
		}
		public void setJsondata(String jsondata) {
			this.jsondata = jsondata;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public CommentDao getCommentDao() {
			return commentDao;
		}
		public void setCommentDao(CommentDao commentDao) {
			this.commentDao = commentDao;
		}
         
}
