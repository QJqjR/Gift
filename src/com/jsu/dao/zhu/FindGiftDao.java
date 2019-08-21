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
import com.jsu.entity.Gift;
@Repository
public class FindGiftDao extends HibernateDaoSupport{
	@Resource
	public void setSF(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Gift> findGifts(final String type){
		System.out.println("开始查找");
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				String hql = "from Gift where g_type = ?";
				Query query = session.createQuery(hql);
				query.setString(0,type);
				List<Gift> list = query.list();
//				for(Gift gift:list){
//					System.out.println(gift.toString());
//				}
				return list;
			}
		});
		
		
		
	}
	@SuppressWarnings("unchecked")
	public List<Gift> findGiftsByPartner(final String partner){
		System.out.println("开始模糊查询");
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				String hql = "from Gift where g_partner like ? ";
				Query query = session.createQuery(hql);
				query.setString(0,"%" + partner + "%");
				List<Gift> list = query.list();
				return list;
			}
		});
	}
	

}
