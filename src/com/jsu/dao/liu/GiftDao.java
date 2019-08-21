package com.jsu.dao.liu;

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
import com.jsu.entity.Order;

@Repository
public class GiftDao extends HibernateDaoSupport{
	
	
	@Resource
	public void setSF(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	public void insertGift(Gift gift){
		getHibernateTemplate().save(gift);
	}
	
	@SuppressWarnings("unchecked")
	public List<Gift> findGiftBySize(final int limit,final int offset){
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("from Gift");
				query.setFirstResult(offset);
				query.setMaxResults(limit);
				List<Gift> gs = (List<Gift>) query.list();
				return gs;
			}
		});
	}
	
	 //删除礼品
	 public void deletegift(Gift gift){
		 System.out.println("开始执行删除礼品");
		 getHibernateTemplate().delete(gift);
		 System.out.println("执行完了删除礼品");
	 }
	 
	 //修改礼品
	 public void updategift(Gift gift){
		 System.out.println("开始执行修改礼品");
		 getHibernateTemplate().update(gift);
		 System.out.println("执行完了修改礼品");
	 }
	 public boolean findgiftid(String id){
		 System.out.println("开始执行查询礼品id");
		Gift gift= (Gift) getHibernateTemplate().get(Gift.class, id);
		 System.out.println("执行完了查询礼品id");
		 if(gift==null){
			 return false;
		 }
		return true;
	 }
	 @SuppressWarnings("unchecked")
	public List<Gift> findGift(final int page,final int pagesize,final String title){
		 System.out.println("开始执行");
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				String hql="from Gift where title like ? ";
				Query query=session.createQuery(hql);
				query.setString(0, "%"+title+"%");
				query.setFirstResult((page-1)*pagesize);
				query.setMaxResults(pagesize);
				List<Gift> list=query.list();
				System.out.println("执行完了");
				return list;
			}
		});
		 
	 }
	 @SuppressWarnings("unchecked")
		public int findpages( int pagesize, final String title){
    	 int pages;
    	 List<Gift> list=getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					String hql="from Gift where title like ? ";
					Query query=session.createQuery(hql);
					query.setString(0, "%"+title+"%");
					List<Gift> lists=query.list();
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
	public List<Gift> giftSort(final int psort) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql = null;
				if(psort==1){
					hql="from Gift order by nprice desc";
				}else if(psort==0){
					hql="from Gift order by nprice asc";
				}
				
				Query query=session.createQuery(hql);
				List<Gift> list=query.list();
				System.out.println("执行完了");
				return list;
				

			}
		});
	}

	
	 public Gift findGiftById(String id){
		 System.out.println("开始执行查询礼品id");
		 
		 Gift gift= (Gift) getHibernateTemplate().get(Gift.class, id);
		 
		 System.out.println("执行完了查询礼品id");
		 return gift;
	 }
	 
	 
	 @SuppressWarnings("unchecked")
		public List<Gift> sortByPwd(final int page,final int pagesize,final String title,final int psort){
			 System.out.println("开始执行");
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					
					String hql="from Gift where title like ? ";
					if(psort==1){
						hql=hql+"order by nprice desc";
					}else if(psort==0){
						hql=hql+"order by nprice asc";
					}
					
					Query query=session.createQuery(hql);
					query.setString(0, "%"+title+"%");
					query.setFirstResult((page-1)*pagesize);
					query.setMaxResults(pagesize);
					List<Gift> list=query.list();
					System.out.println("执行完了");
					return list;
				}
			});
			 
		 }

	@SuppressWarnings("unchecked")
	public List<Gift> updateGiftTable(final String id,final int deal,final int sale, final int num) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
		
				System.out.println("id:"+id+",deal:"+deal+",sale:"+sale+",num:"+num);
				session.beginTransaction();
				Gift gift = (Gift) getHibernateTemplate().get(Gift.class, id);
				gift.setDeal(gift.getDeal()+1);
				gift.setSale(gift.getSale()+num);
				session.update(gift);
				session.getTransaction().commit();
				
				System.out.println("Gift表更新成功");
				session.close();
				return null;
			}
		});
		
	}
		
}
