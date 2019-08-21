package com.jsu.dao.qu;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.jsu.entity.User;

@Repository
public class UserDao extends HibernateDaoSupport{
	@Resource
	public void setSF(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	public void addUser(User user) {
		getHibernateTemplate().save(user);
	}
	
	public User findUserById(String id){
		System.out.println("前面的那个id："+id);
		User user=(User) getHibernateTemplate().get(User.class, id);
		System.out.println(user.toString());
		return user;
	}
	
	 public void updateUser(User user){
     	getHibernateTemplate().update(user);
     }
	
	public User findUserByEmail(String email){
		String hql="from User where email = ?";
		Session session=getSession();
		Query query=session.createQuery(hql);
		query.setParameter(0, email);
		List list=query.list();
		User user = (User) list.get(0);
		return user;
	}
	
	public boolean findEmail(String email){
		System.out.println("按email查询，email为："+email);
		String hql="from User where email = ?";

		Session session=getSession();
		Query query=session.createQuery(hql);
		query.setParameter(0, email);
		List list=query.list();
		Iterator it=list.iterator();
		while(it.hasNext()){
			if(list.size()!=0){
				System.out.println("找到了哟哟");
				return true;
			}
		}
		session.close();
		System.out.println("没有找到，好伤心");
		return false;
	}
	
	public boolean findEmailAndPwd(String email,String pwd){
		System.out.println("用户名："+email+ "   "+"密码："+pwd);
		String hql="from User where email = ? and pwd = ?";
		Session session=getSession();
		Query query=session.createQuery(hql);
		query.setParameter(0, email);
		query.setParameter(1, pwd);
		List list=query.list();
		Iterator it=list.iterator();
		while(it.hasNext()){
			if(list.size()!=0){
				System.out.println("用户名密码正确");
				return true;
			}
		}
		session.close();
		System.out.println("密码错误");
		return false;
	}
	
	//更新用户信息
	public void updateUserInforByUserid(final String userid,final String img,  final String tel, final String username, final String sex, final int age, final String hobby){
			getHibernateTemplate().executeFind(new HibernateCallback(){
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					session.beginTransaction();
					User user = (User)getHibernateTemplate().get(User.class, userid);
					user.setImage(img);
					user.setTel(tel);
					user.setName(username);
					user.setSex(sex);
					user.setAge(age);
					user.setHobby(hobby);
					session.update(user);
					session.beginTransaction().commit();
					return null;
				}
				
			});
	}
}
