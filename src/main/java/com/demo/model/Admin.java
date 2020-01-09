package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class Admin 
{	
	
	@Id
	@Column(name="id")
	private long id;
	
	@Column(name="uname")
	String uname;
	
	@Column(name="pass")
	String pass;


	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getUname() {
		return uname;
	}



	public void setUname(String uname) {
		this.uname = uname;
	}



	public String getPass() {
		return pass;
	}



	public void setPass(String pass) {
		this.pass = pass;
	}




	
	
}