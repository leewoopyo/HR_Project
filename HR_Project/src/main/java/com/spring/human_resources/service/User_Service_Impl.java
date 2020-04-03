package com.spring.human_resources.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spring.human_resources.domain.User;
import com.spring.human_resources.repository.User_Repository;
import com.spring.human_resources.repository.User_Specs;

@Service
public class User_Service_Impl implements User_Service {

	@Autowired
	User_Repository user_Repository;

	//전체 데이터 조회
	@Override
	public List<User> findAllPageable(int pageNum) {
		PageRequest pageable = PageRequest.of(pageNum, 10);
		Page<User> page = user_Repository.findAll(pageable);
		List<User> list = page.getContent();
		return list;
	}

	// 필터 적용된 데이터 조회
	@Override
	public List<User> findAllByFilterAndPageable(String select_column, String find_str, int pageNum) {

		PageRequest pageable = PageRequest.of(pageNum, 10);

		Map<String, Object> filter = new HashMap<String, Object>();

		filter.put(select_column, find_str);

		Page<User> page = user_Repository.findAll(User_Specs.search(filter), pageable);

		// 페이지 객체의 내용을 list로 옮기기(모델에 담기 위해서)
		List<User> list = page.getContent();

		return list;
	}

	// 데이터 삭제
	@Override
	public void delete_users(String deleteUser) {
		// 1. VO와 매핑되서 데이터를 전송하는 방법
//		Gson gson = new Gson();	 
//		Type type = new TypeToken<List<User_Delete_VO>>(){}.getType();  
//
//		List<User_Delete_VO> testList= gson.fromJson(deleteUser, type);
//
//		System.out.println("12345 : "+testList.get(1).getUsername());

		// 방법2 : 파싱을 통한 방법
		JsonParser parser = new JsonParser();
		JsonObject univ = (JsonObject) parser.parse("{" + deleteUser + "}");
		JsonArray arr = (JsonArray) univ.get("deleteuUser");
		String username;
		for (int i = 0; i < arr.size(); i++) {
			JsonObject tmp = (JsonObject) arr.get(i);
			username = tmp.get("username").toString();
			//System.out.println(username.replaceAll("\"", ""));
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
