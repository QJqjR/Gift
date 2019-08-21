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
import com.jsu.entity.User;

//进入主页前读取数据
@Repository
public class IndexDao extends HibernateDaoSupport{
	@Resource
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	//根据userid查询当前登录用户信息，来自tb_user表
	public User findByUserId(String userid){
		return (User) getHibernateTemplate().get(User.class, userid);
	}
	//查询24条显示在首页，来自tb_gift表
	@SuppressWarnings("unchecked")
	public List<Gift> findByIndex(final int offset,final int limit){
		return getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("from Gift where g_status=1");
				query.setFirstResult(offset);
				query.setMaxResults(limit);
				List<Gift> giftList =(List<Gift>) query.list();
				session.close();
				return giftList;
			}
			
		});
	}
	//多个字段模糊查询,来自tb_gift表
	@SuppressWarnings("unchecked")
	public List<Gift> findByUserHobby(final String hobbyName){
		return getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("from Gift where concat(g_title,g_parameter,g_situation,g_partner) like '%'||?||'%' and g_status=1");
				query.setString(0, hobbyName);
				query.setMaxResults(24);
				List<Gift> list = query.list();
				System.out.println("list大小："+list.size());
				return list;
			}
			
		});
	}
	//推荐没有满24个时候，再读24-list.size()个,来自tb_gift表
	@SuppressWarnings("unchecked")
	public List<Gift> findByUserHobbyNum(final int size) {
		return getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				System.out.println("size:"+size);
				Query query = session.createQuery("from Gift where g_status=1 order by g_id desc");
				query.setMaxResults(size);
				List<Gift> list = query.list();
				session.close();
				System.out.println("findByUserHobbyNum的list大小："+list.size());
				return list;
			}
			
		});
	}
	
}
