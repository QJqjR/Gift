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

import com.jsu.entity.Artical;
import com.jsu.entity.PushGift;

@Repository
public class ArticalDao extends HibernateDaoSupport {
	@Resource
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	// 查询文章，分页
		@SuppressWarnings("unchecked")
		public List<Artical> findArticalAll(final int page, final int pageSize) {
			return getHibernateTemplate().executeFind(new HibernateCallback() {

				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					// TODO Auto-generated method stub
					System.out.println(page + "  " + pageSize);
					String hql = "from Artical order by time asc";
					Query query = session.createQuery(hql);
					query.setFirstResult((page - 1) * pageSize);
					query.setMaxResults(pageSize);
					List<Artical> articalAll = query.list();
					// for(Artical artical:list){
					// System.out.println(artical.getId());
					// }
					return articalAll;
				}
			});

		}

		
		public String insertArtical(Artical artical){
			getHibernateTemplate().save(artical);
			return artical.getId();
		}
	
		//根据ID删除文章
		@SuppressWarnings("unchecked")
		public void deleteArticalById(String id){
			System.out.println("delete");
			Artical artical = (Artical) getHibernateTemplate().get(Artical.class, id);
		System.out.println("删除文章");
			getHibernateTemplate().delete(artical);
			String hql = "from PushGift where artical_id = ?";
			List<PushGift> psg = getHibernateTemplate().find(hql,id);
			System.out.println(psg.size());
			for(PushGift a : psg){
				System.out.println("删除推荐礼品");
				getHibernateTemplate().delete(a);
			}
		}
		
		public Artical findArticalById(String id){
			System.out.println("根据ID查询artical");
			Artical artical = (Artical) getHibernateTemplate().get(Artical.class,id);
			return artical;
		}

		public void addArtical(Artical artical){
			getHibernateTemplate().save(artical);
		}

		public void updateActical(Artical artical) {
			getHibernateTemplate().update(artical);
			
		}

}
