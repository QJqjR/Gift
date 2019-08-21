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
import com.jsu.entity.Comment;
import com.jsu.entity.Commentadd;
import com.jsu.entity.Gift;
@Repository
public class CommentDao extends HibernateDaoSupport{
	    @Resource
	    public void setSF(SessionFactory sessionFactory){
	    	super.setSessionFactory(sessionFactory);
	    }
       public void addComment(Comment comment){
    	   getHibernateTemplate().save(comment);
       }
       public void updateComment(Comment comment){
    	   getHibernateTemplate().update(comment);
       }
       public Comment findComment(String id){
    	   Comment comment=(Comment) getHibernateTemplate().get(Comment.class, id);
		   return comment;  
       }
       @SuppressWarnings("unchecked")
	public List<Comment> findComments(final int page,final int pagesize,final String gId,final int choice,final int content){
    	   return getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql="from Comment where gId=? ";
				if(choice==1){
					hql=hql+"and review=1";
				}else if(choice==2){
					hql=hql+"and image !='' ";
				}else if(choice==3){
					hql=hql+"and rate=1 or rate=2";
				}else if(choice==4){
					hql=hql+"and rate=3 or rate=4";
				}else if(choice==5){
					hql=hql+"and rate=5";
				}
				if(content==1){
					hql=hql+"and (image !='' or detail != '' )";
				}
				hql=hql+"order by time desc";
				Query query=session.createQuery(hql);
				query.setString(0, gId);
				query.setFirstResult((page-1)*pagesize);
				query.setMaxResults(pagesize);
				List<Comment> list=query.list();
				return list;
			}
		});
       }
       @SuppressWarnings("unchecked")
		public int findpages( int pagesize, final String gid,final int choice,final int content){
       	 int pages;
       	 List<Comment> list=getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					String hql="from Comment where gId=?";
					if(choice==1){
						hql=hql+"and review=1";
					}else if(choice==2){
						hql=hql+"and image !='' ";
					}else if(choice==3){
						hql=hql+"and rate=1 or rate=2";
					}else if(choice==4){
						hql=hql+"and rate=3 or rate=4";
					}else if(choice==5){
						hql=hql+"and rate=5";
					}
					if(content==1){
						hql=hql+"and (image !='' or detail !='' )";
					}
					Query query=session.createQuery(hql);
					query.setString(0, gid);
					List<Comment> lists=query.list();
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
	public boolean findcommentid(String uuid) {
		
		Comment comment=(Comment) getHibernateTemplate().get(Comment.class, uuid);
		if(comment==null){
			return false;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	public List<Comment> findAllComment(final String gid) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql="from Comment where gId = ?";
				Query query =session.createQuery(hql);
				
				List<Comment> list=query.list();
				return list;
			}
		});
	}
	
   @SuppressWarnings("unchecked")
	public List<Comment> findCommentBySize(final int limit,final int offset,final String gid){
		System.out.println("开始查找");
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("from Comment where gId = ?");
				System.out.println("查询中");
				query.setString(0, gid);
				query.setFirstResult(offset);
				query.setMaxResults(limit);
				List<Comment> list = (List<Comment>) query.list();
				System.out.println("查询完了");
				return list;
			}
		});
	}
}
