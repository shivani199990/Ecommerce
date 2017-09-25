package com.DaOImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.DaO.UserDaO;
import com.model.User;
@Repository
public  class UserDaOImpl implements UserDaO
{
	@Autowired
	SessionFactory sessionFac;
	public void insertUser(User user)
	{  
		Session session=sessionFac.openSession();
		session.beginTransaction();
		session.persist(user);
		session.getTransaction().commit();
		
	}
	@Autowired
	public UserDaOImpl(SessionFactory sessionFactory)
	{
		super();
		sessionFac=sessionFactory;
	}
	
	}
	



