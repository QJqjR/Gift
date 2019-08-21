package com.jsu.action.zhu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;

import com.jsu.dao.zhu.ArticalDao;
import com.jsu.dao.zhu.PushGiftDao;
import com.jsu.entity.Artical;
import com.jsu.entity.PushGift;
import com.jsu.util.CreateIdUtil;
import com.jsu.util.FormatUtil;

@Controller
public class ArticalAction {
	@Resource
	private ArticalDao articalDao;
	@Resource
	private PushGiftDao pushGiftDao;
	private List<PushGift> pushGifts;
	private String time;
	private String title;// 标题
	private String img;// 描述
	private int page;
	private String id;
	private int pagesize;
	private List<Artical> articals;
	private String jsondata;
	private String details;
	//推送礼品
	private String pgtitle;
	private String pgdetails;
	private String pgimage;
	private String pgprice;
	private String pglink;


	// 删除文章
	public String deleteArtical() {
		/*id="101";
		page=1;
		pagesize=10;*/
		System.out.println("删除文章");
		System.out.println("要删除的文章的ID" + id);
		articalDao.deleteArticalById(id);
		
		articals = articalDao.findArticalAll(page, pagesize);
		// larticals=articalDao.findArticalAll(1, 5);
		System.out.println("查询完成");
		// for(Artical artical:articals){
		// System.out.println(artical.getId());
		// }
		JSONArray json = JSONArray.fromObject(articals);
		JSONObject obj = new JSONObject();
		if (articals.size() != 0) {
			obj.put("code", 200);
			obj.put("status", 1);
			obj.put("result", json);
		} else {
			obj.put("code", 400);
			obj.put("status", 0);
			obj.put("result", json);
		}
		jsondata = obj.toString();
		return "deletesuccess";
	}

	// 查询文章
	public String showArtical() {
		System.out.println("开始进行查询");
	/*	page=1;
		pagesize=10; */
		articals = articalDao.findArticalAll(page, pagesize);
		// larticals=articalDao.findArticalAll(1, 5);
		System.out.println("查询完成");
		// for(Artical artical:articals){
		// System.out.println(artical.getId());
		// }
		JSONArray json = JSONArray.fromObject(articals);
		JSONObject obj = new JSONObject();
		if (articals.size() != 0) {
			obj.put("code", 200);
			obj.put("status", 1);
			obj.put("result", json);
		} else {
			obj.put("code", 400);
			obj.put("status", 0);
			obj.put("result", json);
		}
		jsondata = obj.toString();
		return "success";
	}
	
	//添加文章
	public String addArtical(){
		Artical artical;
		/*title="大神";
		img="脑壳痛";
		details="我也不知道该放个啥";
		
		pgtitle="大神,大神";
		pgdetails="大神,大神";
		pgprice="大神,大神";
		pglink="大神,大神";
		pgimage="大神,大神";*/
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		time = sdf.format(new Date());
		id=CreateIdUtil.getId();
		artical = new Artical(id,time,title,img,details);
		System.out.println("开始添加文章");
		articalDao.addArtical(artical);
		System.out.println("文章添加完成");
		
		System.out.println("开始进入添加推送礼品阶段");
		String[] titles=pgtitle.split(",");
		String[] detas=pgdetails.split(",");
		String[] images=pgimage.split(",");
		String[] prices=pgprice.split(",");
		String[] links=pglink.split(",");
		
		for(int i=0;i<titles.length;i++){
			PushGift pusGift=new PushGift(CreateIdUtil.getId(),id,titles[i],detas[i],images[i],prices[i],links[i]);
			pushGiftDao.addPushGift(pusGift);
		}

		JSONObject obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("msg", "文章添加成功");
		jsondata = obj.toString();
		return "success";
	}
	
	
	//更新文章
	public String updateArtical() {
		System.out.println("进入更新");
		Artical artical = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		time = sdf.format(new Date());
		System.out.println(time);
		
		/*id="5daaf5af86b6";
		title="测试";
		page=1;
		pagesize=10; */
		
		artical=articalDao.findArticalById(id);
		artical.setImg(img);
		artical.setTime(time);
		artical.setTitle(title);
		articalDao.updateActical(artical);
		
		articals = articalDao.findArticalAll(page, pagesize);
		JSONArray json = JSONArray.fromObject(articals);
		JSONObject obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("msg", "文章更新完成");
		obj.put("result", json);
		jsondata = obj.toString();
		return "success";
	}

	public ArticalDao getArticalDao() {
		return articalDao;
	}

	public void setArticalDao(ArticalDao articalDao) {
		this.articalDao = articalDao;
	}

	public PushGiftDao getPushGiftDao() {
		return pushGiftDao;
	}

	public void setPushGiftDao(PushGiftDao pushGiftDao) {
		this.pushGiftDao = pushGiftDao;
	}

	public List<PushGift> getPushGifts() {
		return pushGifts;
	}

	public void setPushGifts(List<PushGift> pushGifts) {
		this.pushGifts = pushGifts;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public List<Artical> getArticals() {
		return articals;
	}

	public void setArticals(List<Artical> articals) {
		this.articals = articals;
	}

	public String getJsondata() {
		return jsondata;
	}

	public void setJsondata(String jsondata) {
		this.jsondata = jsondata;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getPgtitle() {
		return pgtitle;
	}

	public void setPgtitle(String pgtitle) {
		this.pgtitle = pgtitle;
	}

	public String getPgdetails() {
		return pgdetails;
	}

	public void setPgdetails(String pgdetails) {
		this.pgdetails = pgdetails;
	}

	public String getPgimage() {
		return pgimage;
	}

	public void setPgimage(String pgimage) {
		this.pgimage = pgimage;
	}

	public String getPgprice() {
		return pgprice;
	}

	public void setPgprice(String pgprice) {
		this.pgprice = pgprice;
	}

	public String getPglink() {
		return pglink;
	}

	public void setPglink(String pglink) {
		this.pglink = pglink;
	}

	
}
