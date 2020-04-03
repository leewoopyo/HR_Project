package com.spring.human_resources.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class HR {

	@Id
	@GeneratedValue
	@Column
	private Long id;	//아이디
	
	@OneToOne
	@JsonManagedReference
	private User user;	//User정보
	
	public HR() {
		super();
	}

	public HR(User user) {
		super();
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
