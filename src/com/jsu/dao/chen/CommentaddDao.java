package com.jsu.dao.chen;

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

import com.jsu.entity.Commentadd;
@Repository
public class CommentaddDao extends HibernateDaoSupport{
	   @Resource
	   public void setSF(SessionFactory sessionFactory){
		   super.setSessionFactory(sessionFactory);
	   }
	   public void addcommentadd(Commentadd commentadd){
		   getHibernateTemplate().save(commentadd);
	   }
       public void updatecommentadd(Commentadd commentadd){
    	   getHibernateTemplate().update(commentadd);
       }
       public void deletecommentadd(Commentadd commentadd){
    	   getHibernateTemplate().delete(commentadd);
       }
       public boolean findcommentaddid(String id){
    	   Commentadd commentadd=(Commentadd) getHibernateTemplate().get(Commentadd.class, id);
           if(commentadd==null){
        	   return false;
           }
           return true;
       }
       @SuppressWarnings("unchecked")
	public List<Commentadd> findcommentadds(final String cid){
    	   return  getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql="from Commentadd where cId=?";
				Query query =session.createQuery(hql);
				query.setString(0, cid);
				List<Commentadd> list=query.list();
				return list;
			}
		});
       }
       
}
