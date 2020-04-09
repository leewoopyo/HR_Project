package com.spring.human_resources.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	@Column
	private Long id;	//고유번호
	
	@Column
	private String username; //아이디
	
	@Column
	private String password;	//비밀번호
	
	@Column
	private String name;	//이름
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;	//생년월일

	@Column
	private String tel;	//전화번호 

	@Column
	private String eMail;	//이메일
	
	@Column
	private String sType; //성별
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created;	//가입일자

	@OneToOne(cascade = CascadeType.REMOVE, mappedBy = "user")
	@JsonBackReference
	private HR hr;		//HR클래스

	
	public User() {
		super();
	}

	public User(String username) {
		super();
		this.username = username;
	}

	public User(Long id, String username, String password, String name, Date birth, String tel, String eMail,
			String sType, Date created, HR hr) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.birth = birth;
		this.tel = tel;
		this.eMail = eMail;
		this.sType = sType;
		this.created = created;
		this.hr = hr;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getsType() {
		return sType;
	}

	public void setsType(String sType) {
		this.sType = sType;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public HR getHr() {
		return hr;
	}

	public void setHr(HR hr) {
		this.hr = hr;
	}
	
	
}
