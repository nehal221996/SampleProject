package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="user")

public class User
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false)
	int id;

	
	@Column(name="firstname", nullable = false)
	String firstname;
	
	
	
	@Column(name="lastname", nullable = false)
	String lastname;
	

	@Column(name="email", nullable = false)
	String email;
	
	
	@Column(name="mobile")
	String mobile;
	
	
	@Column(name="password", nullable = false)
	String password;
	
	
	@Column(name="gender")
	String gender;
	
	@Lob
	@Column(name="profile")
	byte[] profile;
	
	@Column(name = "picture_url")
	private String picture_url;

	@Column(name = "google_id", unique = true)
	private String google_id;
	
	@JsonIgnore
	@Column(name = "doj", nullable = false)
	private String doj;

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public String getPicture_url() {
		return picture_url;
	}

	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}

	public String getGoogle_id() {
		return google_id;
	}

	public void setGoogle_id(String google_id) {
		this.google_id = google_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public byte[] getProfile() {
		return profile;
	}

	public void setProfile(byte[] profile) {
		this.profile = profile;
	}

	public User()
	{
		super();
	}
	
	public User(int id,String firstname,String lastname,String email,String mobile,String password,String gender, byte[] profile)
	{
		super();
		this.id=id;
		this.firstname=firstname;
		this.lastname=lastname;
		this.email=email;
		this.mobile=mobile;
		this.password=password;
		this.gender=gender;
		this.profile=profile;
	}
	
	public String toString()
	{
		return String.format(
				"User[id=%d, firstname='%s',lastname='%s', email='%s', mobile='%s', password='%s', gender='%s', profile='%s']",
				id, firstname, lastname, email, mobile, password, gender, profile);
	}
	
	
}