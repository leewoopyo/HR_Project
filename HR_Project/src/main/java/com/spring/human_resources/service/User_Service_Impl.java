package com.spring.human_resources.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spring.human_resources.domain.User;
import com.spring.human_resources.repository.User_Repository;
import com.spring.human_resources.repository.User_Specs;

@Service
public class User_Service_Impl implements User_Service {

	//repository를 injection함
	@Autowired
	User_Repository user_Repository;

	//전체 데이터 조회
	@Override
	public List<User> findAllPageable(int pageNum,String sortType, String sortDirection) {
		
		PageRequest pageable = null;
		
		if(sortDirection.equals("desc")) {
			pageable = PageRequest.of(pageNum,10, Sort.Direction.DESC, sortType);			
		}else if(sortDirection.equals("asc")) {
			pageable = PageRequest.of(pageNum,10, Sort.Direction.ASC, sortType);
		}
		
		
		//해당 페이지 설정에 따른 전체 데이터를 page객체에 담음
		Page<User> page = user_Repository.findAll(pageable);
		//모델에 담기위해 page객체를 list로 변환
		List<User> list = page.getContent();
		
		return list;
	}

	// 필터 적용된 데이터 조회
	@Override
	public List<User> findAllByFilterAndPageable(String select_column, String find_str, int pageNum,String sortType,String sortDirection) {

		PageRequest pageable = null;
		
		if(sortDirection.equals("desc")) {
			pageable = PageRequest.of(pageNum,10, Sort.Direction.DESC, sortType);			
		}else if(sortDirection.equals("asc")) {
			pageable = PageRequest.of(pageNum,10, Sort.Direction.ASC, sortType);
		}
		
		//필터 처리를 위한 map 객체 생성
		Map<String, Object> filter = new HashMap<String, Object>();
		//filter에 필터 조건과 검색 값을 입력
		filter.put(select_column, find_str);
		//필터와 페이지 처리를 한 데이터를 page객체에 담음
		Page<User> page = user_Repository.findAll(User_Specs.search(filter), pageable);
		// 페이지 객체의 내용을 list로 옮기기(모델에 담기 위해서)
		List<User> list = page.getContent();

		return list;
	}

	//데이터 삽입
	@Override
	public void insert_user(User user) {
		user_Repository.save(user);
	}
	
	

	@Override
	public void update_user(User user, String update_name, Date update_birth, String update_tel, String update_eMail,
			String update_sType) {
		
		System.out.println(user.getUsername());
		System.out.println(update_name);
		System.out.println(update_birth);
		System.out.println(update_tel);
		System.out.println(update_eMail);
		System.out.println(update_sType);
		
		
		
		User updateUser = user_Repository.findByUsername(user.getUsername());
		
		updateUser.setName(update_name);
		updateUser.setBirth(update_birth);
		updateUser.setTel(update_tel);
		updateUser.seteMail(update_eMail);
		updateUser.setsType(update_sType);
		
		user_Repository.save(updateUser);
	}

	//데이터 삭제 (단일)
	@Override
	public void delete_user(String deleteUser) {
		user_Repository.delete(user_Repository.findByUsername(deleteUser));
	}

	// 데이터 삭제
	@Override
	public void delete_users(String deleteUser_list) {
		// 1. VO와 매핑되서 데이터를 전송하는 방법
//		Gson gson = new Gson();	 
//		Type type = new TypeToken<List<User_Delete_VO>>(){}.getType();  
//
//		List<User_Delete_VO> testList= gson.fromJson(deleteUser, type);
//
//		System.out.println("12345 : "+testList.get(1).getUsername());

		// 방법2 : 파싱을 통한 방법
		JsonParser parser = new JsonParser();	//json parser객체 생성
		//JsonObject객체에 문자열로 받은 json데이터를 파싱함 
		JsonObject univ = (JsonObject) parser.parse("{" + deleteUser_list + "}");
		//파싱한 데이터를 배열 객체에 담음
		JsonArray arr = (JsonArray) univ.get("deleteuUser");
		String username;
		//배열 안의 데이터를 하나씩 출력하면서 하나씩 데이터를 지움
		for (int i = 0; i < arr.size(); i++) {
			JsonObject tmp = (JsonObject) arr.get(i);
			username = tmp.get("username").toString();
			user_Repository.delete(user_Repository.findByUsername(username.replaceAll("\"", "")));
		}
	}
	
	//전체 데이터 갯수
	@Override
	public Long calc_pagecnt() {
		Long cnt = user_Repository.count();
		
		//System.out.println(cnt);
		cnt = (long) Math.ceil((double)cnt/10);
		
		return cnt;
	}

	//필터 처리 데이터 갯수
	@Override
	public Long calc_pagecnt(String select_column, String find_str) {
		
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put(select_column, find_str);
		Long cnt = user_Repository.count(User_Specs.search(filter));
		
		//System.out.println(cnt);
		cnt = (long) Math.ceil((double)cnt/10);
		
		return cnt;
	}
	
	
	
}
