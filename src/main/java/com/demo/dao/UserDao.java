package com.demo.dao;

import java.util.List;

import com.demo.model.User;

public interface UserDao
{

	void UpdateDataById(User user);

	void InsertData(User user);

	List<User> getUserList();

	void deleteData(int did);

	User getDataById(int uid);

	User getUserDetail(String uname, String pass);

	List<User> findAll(int id);

	List<User> findAll();

	/*List<User> getUserByName(String firstname);*/

	int updatePassword(String password, int id);

	List<User> getUserById(int id);

	boolean findUserBygoogle(String google_id, String name);

	User saveUser(User user);

	List<User> getUserByName(String name);

}
