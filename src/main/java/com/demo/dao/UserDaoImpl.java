package com.demo.dao;

import java.util.List;

import javax.transaction.Transactional;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.model.User;




@Repository
@Transactional
public class UserDaoImpl implements UserDao
{
	@Autowired
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void UpdateDataById(User user) 
	{
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		session.update(user);
		t.commit();
		/*sessionFactory.getCurrentSession().update(user);*/
	}

	@Override
	public void InsertData(User user) 
	{
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		session.save(user);
		t.commit();
		//sessionFactory.getCurrentSession().save();
	}

	/*@Override
	public User getUserDetail(String uname, String pass)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from User where firstname= :uname and password= :pass");
		query.setParameter("uname", uname);
		query.setParameter("pass", pass);
		return (User) query.uniqueResult()
		
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		String SQL_QUERY="from User where firstname=:uname and password=:pass"  ;
		Query query=session.createQuery(SQL_QUERY);
		query.setParameter("uname", uname);
		query.setParameter("pass", pass);
		List<User> u= query.getResultList();
		t.commit();
		return (User) u;
	}
*/
	
	/*@Override
	public List<User> getUserDetail(String uname, String pass)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from User where firstname= :uname and password= :pass");
		query.setParameter("uname", uname);
		query.setParameter("pass", pass);
		return (User) query.uniqueResult()
		
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		String SQL_QUERY="from User where firstname=:uname and password=:pass"  ;
		Query query=session.createQuery(SQL_QUERY);
		query.setParameter("uname", uname);
		query.setParameter("pass", pass);
		List<User> u= query.list();
		t.commit();
		return  u;
		
	}*/

	@Override
	public User getUserDetail(String uname, String pass) 
	{
		Session session = sessionFactory.getCurrentSession();
		Transaction t=session.beginTransaction();
		String SQL_QUERY="from User where firstname=:uname and password=:pass"  ;
		Query query=session.createQuery(SQL_QUERY);
		query.setParameter("uname", uname);
		query.setParameter("pass", pass);
		User u=(User) query.uniqueResult();
		t.commit();
		return u;
	}
	
	@Override
	public List<User> getUserList() 
	{
		// return sessionFactory.getCurrentSession().createQuery("from User").list();
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<User> allUser=session.createQuery("from User").list();
		t.commit();
		return allUser;
	}

	@Override
	public void deleteData(int did)
	{
		/*User u = (User) sessionFactory.getCurrentSession().load(User.class, did);
		sessionFactory.getCurrentSession().delete(u);	*/
		
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		User u=session.load(User.class, did);
		session.delete(u);
		System.out.println("Person deleted successfully, person details=" + u);
		t.commit();
		
	}

	@Override
	public User getDataById(int uid)
	{
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		User u=(User) session.createQuery("from User u where id="+uid).uniqueResult();
		t.commit();
		// return (User) sessionFactory.getCurrentSession().createQuery("from User u where id="+uid).uniqueResult();
		return u;
	}

	@Override
	public List<User> findAll(int id)
	{
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		String SQL_QUERY="from User where id=:id";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter("id", id);
		List<User> u = query.list();
		System.out.println("Person loaded successfully, Person details=" + u);
		t.commit();
		return u;
	}

	@Override
	public List<User> findAll() 
	{

		return null;
	}

	/*@Override
	public int updatePassword(String password)
	{
		System.out.println("In password reset User dao");
		Session session = sessionFactory.getCurrentSession();
		System.out.println(password);
		Transaction t = session.beginTransaction();
		Query query = session.createQuery("update User set password=:pass");
		query.setParameter("pass", password);
		int result = query.executeUpdate();
		// Commit the transaction and close the session
		t.commit();
		System.out.println("No of rows updated: " + result);
		return result;
	}*/

	/*@Override
	public List<User> getUserByName(String firstname)
	{
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		String SQL_QUERY = "  from User where firstname=:firstname";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter("firstname", firstname);
		List<User> usr = query.list();
		t.commit();
		return usr;
	}*/

	@Override
	public int updatePassword(String password, int id)
	{
		System.out.println("In password reset User dao");
		Session session = sessionFactory.getCurrentSession();
		System.out.println(password);
		Transaction t = session.beginTransaction();
		Query query = session.createQuery("update User set password=:pass where id=:id");
		query.setParameter("pass", password);
		query.setParameter("id", id);
		 query.executeUpdate();
		// Commit the transaction and close the session
		t.commit();
		return 0;
		
	}

	@Override
	public List<User> getUserById(int id)
	{
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		String SQL_QUERY = "  from User where id=:id";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter("id", id);
		List<User> usr = query.list();
		t.commit();
		return usr;
	}

	@Override
	public boolean findUserBygoogle(String google_id, String name)
	{
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		String SQL_QUERY = "  from User  where firstname=:name and google_id=:google_id";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter("name", name);
		query.setParameter("google_id", google_id);
		List<User> user = query.list();
		int user_size = user.size();
		boolean userFound;
		if (user_size > 0)
		{
			userFound = true;
		} 
		else
		{
			userFound = false;
		}
		t.commit();
		return userFound;
		
	}

	@Override
	public User saveUser(User user)
	{
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		session.save(user);
		t.commit();
		return user;
	
	}

	@Override
	public List<User> getUserByName(String name)
	{
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		String SQL_QUERY = "  from User  where firstname=:name";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter("name", name);
		List<User> usr = query.list();
		t.commit();
		return usr;
		
	}

	/*@Override
	public int updatePassword(String password)
	{
		System.out.println("In password reset User dao");
		Session session = sessionFactory.getCurrentSession();
		System.out.println(password);
		Transaction t = session.beginTransaction();
		Query query = session.createQuery("update User set password=:pass where id=:id");
		query.setParameter("pass", password);
		
		int result = query.executeUpdate();
		// Commit the transaction and close the session
		t.commit();
		System.out.println("No of rows updated: " + result);
		return result;
	}*/

	
}
