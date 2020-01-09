package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UserDao;
import com.demo.model.User;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	UserDao userDao;

	@Override
	public void UpdateDataById(User user)
	{
		userDao.UpdateDataById(user);
	}

	@Override
	public void InsertData(User user) 
	{
		userDao.InsertData(user);
	}


	@Override
	public List<User> getUserList() 
	{
		return userDao.getUserList();
	}

	@Override
	public void deleteData(int did) 
	{
		userDao.deleteData(did);
	}

	@Override
	public User getDataById(int uid) 
	{
		
		return userDao.getDataById(uid);
	}

	@Override
	public User getUserDetail(String uname, String pass) 
	{
		return userDao.getUserDetail(uname,pass);
	}

	@Override
	public List<User> findAll(int id) 
	{
		return userDao.findAll(id);
	}

	@Override
	public List<User> findAll()
	{
		return userDao.findAll();
	}

	/*@Override
	public List<User> getUserByName(String firstname)
	{
		return userDao.getUserByName(firstname);
	}*/

	@Override
	public int updatePassword(String password, int id) {
		// TODO Auto-generated method stub
		return userDao.updatePassword(password,id);
	}

	@Override
	public List<User> getUserById(int id)
	{
		return userDao.getUserById(id);
	}

	@Override
	public boolean findUserBygoogle(String google_id, String name)
	{
		return userDao.findUserBygoogle(google_id,name);
	}

	@Override
	public User saveUser(User user)
	{
		return userDao.saveUser(user);
	}

	@Override
	public List<User> getUserByName(String name)
	{
		return userDao.getUserByName(name);
	}


	
}
