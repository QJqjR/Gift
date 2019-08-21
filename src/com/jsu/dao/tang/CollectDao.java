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
import com.jsu.entity.Order;

@Repository
public class CollectDao extends HibernateDaoSupport{
	@Resource
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	//根据userid和giftid添加一条收藏数据，来自tb_collect表
	public void addCollectById(Collect collect){
		getHibernateTemplate().save(collect);		
	}
	//根据userid和giftid删除一条收藏数据。来自tb_collect表
	public void deleteCollectById(final String userid, final String giftid){
		getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("from Collect where u_id = ? and g_id = ?");
				query.setString(0, userid);
				query.setString(1, giftid);
				List<Collect> collectList = query.list();
				System.out.println("collectLid大小："+collectList.size());
				session.delete(collectList.get(0));
				session.close();
				return null;
			}
			
		});
	}
	
	public List<Collect> findAllCollectByUid(String id){
		String hql="from Collect where uId = ? order by collect_time DESC";
		List<Collect> collects=getHibernateTemplate().find(hql,id);
		return collects;
	}
	
	
  
      @SuppressWarnings("unchecked")
	public Collect findcollect(final String uid,final String gid){
		 List<Collect> list=getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql="from Collect where uId=? and gId=?";
				Query query=session.createQuery(hql);
				query.setString(0, uid);
				query.setString(1, gid);
				List<Collect> lists=query.list();
				
				return lists;
			}
		});	
		 Collect collect=list.get(0); 
		 return  collect;
      }
      
      @SuppressWarnings("unchecked")
	public List<Collect> findcollects(final int page,final int pagesize, final String uid){
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql="from Collect where uId=? order by time desc";
				Query query=session.createQuery(hql);
				query.setString(0, uid);
				query.setFirstResult((page-1)*pagesize);
				query.setMaxResults(pagesize);
				List<Collect> list=query.list();
				return list;
			}
		});
			
    	  
     }
     @SuppressWarnings("unchecked")
	public int findpages( int pagesize, final String uid){
    	 int pages;
    	 List<Collect> list=getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql="from Collect where uId=?";
				Query query=session.createQuery(hql);
				query.setString(0, uid);
				List<Collect> lists=query.list();
				return lists;
			}
		});
    	 if(list.size()%pagesize==0){
    		 pages=list.size()/pagesize;
    	 }else{
    		 pages=list.size()/pagesize+1;
    	 }
    	 return pages;
     }
     
     
     @SuppressWarnings("unchecked")
	public List<Collect> findIsCollect(final String uid, final String gid) {
    	 return (List<Collect>)getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("from Collect where uId = ? and gId = ?");
				query.setString(0, uid);
				query.setString(1, gid);
				List<Collect> collectList = query.list();
				System.out.println("ccollect大小："+collectList.size());
				
				return collectList;
			}
    		 
    	 });
     }
}
