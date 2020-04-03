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
	private String e_mail;	//이메일
	
	@Column
	private String S_type; //성별
	
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
	
	public User(Long id, String username, String password, String name, Date birth, String tel, String e_mail,
			String s_type, Date created, HR hr) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.birth = birth;
		this.tel = tel;
		this.e_mail = e_mail;
		S_type = s_type;
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

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getS_type() {
		return S_type;
	}

	public void setS_type(String s_type) {
		S_type = s_type;
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
