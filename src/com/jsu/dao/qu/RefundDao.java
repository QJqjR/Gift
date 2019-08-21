package com.jsu.dao.qu;

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
import com.jsu.entity.Refund;
@Repository
public class RefundDao extends HibernateDaoSupport{
	@Resource
	public void setSF(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	public void AddRefund(Refund refund){
		System.out.println("开始插入---------------");
		System.out.println(refund.getId());
		getHibernateTemplate().save(refund);
		System.out.println("插入完成------------------");
	}
	
	@SuppressWarnings("unchecked")
	public List<Refund> findAllRefund(final int offset,final int limit){
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query=session.createQuery("from Refund");
				query.setFirstResult(offset);
				query.setMaxResults(limit);
				List<Refund> list=(List<Refund>)query.list();
				return list;
			}
		});
	}

	public Refund findRefundById(String rid) {
		Refund refund=(Refund) getHibernateTemplate().get(Refund.class, rid);
		return refund;
	}
	
	
}
