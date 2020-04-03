package com.spring.human_resources.service;

import java.util.List;

import com.spring.human_resources.domain.User;

public interface User_Service {
	
	//전체 데이터 조회(페이지 적용)
	public List<User> findAllPageable(int pageNum);
	//필터 적용 데이터 조회 (페이지 적용)
	public List<User> findAllByFilterAndPageable(String select_column, String find_str, int pageNum);
	//데이터 삭제
	public void delete_users(String deleteUser);
	
	//적용될 페이지 수 구하기
	public Long calc_pagecnt();
	
	//필터가 적용된 페이지 수 구하기
	public Long calc_pagecnt(String select_column, String find_str);
	
}
