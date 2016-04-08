package com.project.traffic.repositoryImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.project.traffic.model.User;
import com.project.traffic.persistenceUtil.DataBaseUtil;
import com.project.traffic.repository.UserRepository;
import com.project.traffic.session.AppSession;

public class UserRepositoryImpl implements UserRepository{

	@Override
	public void save(User user) {
		Session session = DataBaseUtil.getSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public boolean findByUsername(String username) {
		Session session = DataBaseUtil.getSession();
		Object user = session.createCriteria(User.class).add(Restrictions.eq("userName", username)).uniqueResult();
		if( user != null && !((User)user).getUserName().isEmpty()){
			return false;
		}
		session.close();
		return true;
		
	}

	@Override
	public List<User> findAll() {
		Session session = DataBaseUtil.getSession();
		List<User> list = session.createCriteria(User.class).list();
		//session.close();
		return list;
	}

	@Override
	public void update(User user) {
		Session session = DataBaseUtil.getSession();
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public User findById(int id) {
	    Session session = DataBaseUtil.getSession();
            Object user = session.createCriteria(User.class).add(Restrictions.eq("id", id)).uniqueResult();
            if(user == null)
                    return null;
            session.close();
            return (User)user;
           }
	@Override
	public void deleteByUsername(String username) {
		Session session = DataBaseUtil.getSession();
		session.beginTransaction();
		//session.update(user);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteById(int id) {
		Session session = DataBaseUtil.getSession();
		session.beginTransaction();
		//session.update(user);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public boolean validateLogin(String username, String password) {
		Session session = DataBaseUtil.getSession();
		Object user = session.createCriteria(User.class).add(Restrictions.eq("userName", username)).add(Restrictions.eq("password", password)).uniqueResult();
		if( user != null && !((User)user).getUserName().isEmpty()){
		        AppSession.session.put("loginUser", user);
			return true;
		}
		session.close();
		return true;
	}

	@Override
	public User findByLogin(String username) {
		Session session = DataBaseUtil.getSession();
		Object user = session.createCriteria(User.class).add(Restrictions.eq("userName", username)).uniqueResult();
		if(user == null)
			return null;
		//session.close();
		return (User)user;
	}

}
