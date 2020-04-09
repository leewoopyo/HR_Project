package com.spring.human_resources.domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class HR {

	@Id
	@GeneratedValue
	@Column
	private Long id;	//아이디
	
	@Column
	@NotNull
	private String emp_id;	//사번
	
	@Column
	@NotNull
	private String jumin_id;	//주민등록번호

	@Column
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY ,mappedBy = "hr")
	@JsonBackReference
	private Collection<HR_Dept> dept;	//부서
	
	@Column
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY ,mappedBy = "hr")
	@JsonBackReference
	private Collection<HR_Rank> rank;	//직책
	
	@Column
	private boolean nationality;	//내국인 외국인 구분
	
	@Column
	private String type_working;	//근무구분
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date entered;	//입사일
	
	@Column
	private String type_entrance;	//입사구분
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date exited;	//입사일
	
	@Column
	private String exitedBy;	//퇴사사유
	
	@OneToOne
	@JsonManagedReference
	private User user;	//User정보

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getJumin_id() {
		return jumin_id;
	}

	public void setJumin_id(String jumin_id) {
		this.jumin_id = jumin_id;
	}

	public Collection<HR_Dept> getDept() {
		return dept;
	}

	public void setDept(Collection<HR_Dept> dept) {
		this.dept = dept;
	}

	public Collection<HR_Rank> getRank() {
		return rank;
	}

	public void setRank(Collection<HR_Rank> rank) {
		this.rank = rank;
	}

	public boolean isNationality() {
		return nationality;
	}

	public void setNationality(boolean nationality) {
		this.nationality = nationality;
	}

	public String getType_working() {
		return type_working;
	}

	public void setType_working(String type_working) {
		this.type_working = type_working;
	}

	public Date getEntered() {
		return entered;
	}

	public void setEntered(Date entered) {
		this.entered = entered;
	}

	public String getType_entrance() {
		return type_entrance;
	}

	public void setType_entrance(String type_entrance) {
		this.type_entrance = type_entrance;
	}

	public Date getExited() {
		return exited;
	}

	public void setExited(Date exited) {
		this.exited = exited;
	}

	public String getExitedBy() {
		return exitedBy;
	}

	public void setExitedBy(String exitedBy) {
		this.exitedBy = exitedBy;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
