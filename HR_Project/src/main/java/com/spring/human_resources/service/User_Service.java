package com.spring.human_resources.service;

import java.util.Date;
import java.util.List;

import com.spring.human_resources.domain.User;

public interface User_Service {
	
	//전체 데이터 조회(페이지 적용)
	public List<User> findAllPageable(int pageNum,String sortType, String sortDirection);
	//필터 적용 데이터 조회 (페이지 적용)
	public List<User> findAllByFilterAndPageable(String select_column, String find_str, int pageNum,String sortType,String sortDirection);
	//데이터 삽입
	public void insert_user(User user);
	//데이터 수정
	public void update_user(User user,String update_name,Date update_birth,String update_tel,String update_e_mail,String update_S_type);
	//데이터 삭제(단일데이터)
	public void delete_user(String deleteUser);
	
	//데이터 삭제(여러데이터)
	public void delete_users(String deleteUser_list);
	
	//적용될 페이지 수 구하기
	public Long calc_pagecnt();
	
	//필터가 적용된 페이지 수 구하기
	public Long calc_pagecnt(String select_column, String find_str);
	
}
