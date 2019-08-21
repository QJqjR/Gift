package com.jsu.dao.tang;

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

import com.jsu.entity.Collect;
import com.jsu.entity.Gift;
import com.jsu.entity.GiftParameter;

@Repository
public class IndexDetailsDao extends HibernateDaoSupport{
	@Resource
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	//根据giftid读取当前礼品的详情
	public Gift findByGift(final String giftid){
		return (Gift) getHibernateTemplate().get(Gift.class, giftid);
	}
	//根据giftid读取数据 ，来自tb_giftparameter表
	@SuppressWarnings("unchecked")
	public List<GiftParameter> findByGiftId(final String giftid){
		return getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("from GiftParameter where g_id=?");
				query.setString(0, giftid);
				List<GiftParameter> giftParameterList = query.list();
				session.close();
				System.out.println("giftParameterList大小："+giftParameterList.size());
				return giftParameterList;
			}			
		});
	}
	//根据userid和giftid查询收藏表，来自tb_collect表
	@SuppressWarnings("unchecked")
	public List<Collect> findCollectByUseridAndGid(final String userid, final String giftid){
		return getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("from Collect where u_id=? and g_id=?");
				query.setString(0, userid);
				query.setString(1, giftid);
				List<Collect> collectList = query.list();
				session.close();
				System.out.println("collectList大小："+collectList.size());
				return collectList;
			}
			
		});
	}
}
