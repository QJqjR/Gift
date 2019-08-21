package com.jsu.dao.zhu;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.jsu.entity.PushGift;
@Repository
public class PushGiftDao extends HibernateDaoSupport{
	@Resource
	public void setSF(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	public List<PushGift> findByArticaId(String id){
		String hql = "from PushGift where artical_id = ?";
		List<PushGift> pushGifts = (List<PushGift>) getHibernateTemplate().find(hql,id);
		return pushGifts;
	}
	
	public void inserPushGift(PushGift psg){
		System.out.println("保存推荐");
		getHibernateTemplate().save(psg);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PushGift> findPushGifts(final String articalId){
		System.out.println("开始查找推荐");
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				String hql = "from PushGift where artical_id";
				Query query = session.createQuery(hql);
				query.setString(0,articalId);
				List<PushGift> pushGifts = query.list();	
				return pushGifts;
			}
		});
	}

	public void addPushGift(PushGift pusGift) {
		System.out.println("添加推送礼品");
		getHibernateTemplate().save(pusGift);
		System.out.println("完成");
		
	}

	public void deletePushGiftById(PushGift pushGift) {
		getHibernateTemplate().delete(pushGift);
		
	}

	public PushGift findById(String id) {
		PushGift pushGift=(PushGift) getHibernateTemplate().get(PushGift.class, id);
		return pushGift;
	}

	public void updatePushGiftById(PushGift pushGift) {
		getHibernateTemplate().update(pushGift);
		
	}	
	
	
	
}
