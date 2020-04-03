package com.spring.human_resources.web;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.spring.human_resources.domain.HR;
import com.spring.human_resources.domain.User;
import com.spring.human_resources.repository.HR_Repository;
import com.spring.human_resources.repository.User_Repository;
import com.spring.human_resources.repository.User_Specs;
import com.spring.human_resources.service.User_Service;
import com.spring.human_resources.vo.User_Delete_VO;

@Controller

public class User_Controller {
	
	@Autowired
	User_Repository user_Repository;
	@Autowired
	User_Service user_Service;
	
	//기본 출력
	@RequestMapping(value="user")
	public String nolist(){
		return "user/user_page";
	}
	
	//전체 데이터 출력
	@RequestMapping(value="user/list/{pageNum}/{startPage}",method = RequestMethod.GET)
	public String list(Model model, @PathVariable("pageNum") int pageNum, @PathVariable("startPage") int startPage){
		
		//페이지 적용한 전체 데이터 출력 후 list에 담음 
		List<User> list = user_Service.findAllPageable(pageNum);
		//페이지를 나타낼 데이터 갯수 구하기
		Long pageCnt = user_Service.calc_pagecnt();
		
		//데이터를 모델에 담음
		model.addAttribute("list", list);	//전체 데이터		
		model.addAttribute("pageCnt", pageCnt);	//데이터 갯수에 기반한 전체 페이지 수
		model.addAttribute("startPage", startPage);	//페이지처리를 위한 시작 페이지값
		
		return "user/user_list";
	}
	
	//데이터 검색 조회
	@RequestMapping(value="user/onelist/{select_column}/{find_str}/{pageNum}/{startPage}", method = RequestMethod.GET)
	public String onelist(Model model, @PathVariable("select_column") String select_column, @PathVariable("find_str")String find_str, @PathVariable("pageNum") int pageNum, @PathVariable("startPage") int startPage){
		
		//필터와 페이지 기능처리한 데이터를 담음
		List<User> list = user_Service.findAllByFilterAndPageable(select_column, find_str, pageNum);
		//페이지 갯수를 구함
		Long pageCnt = user_Service.calc_pagecnt(select_column, find_str);
		//데이터를 모델에 담음
		model.addAttribute("list",list);	//필터 처리한 데이터 
		model.addAttribute("pageCnt",pageCnt);	//페이지 갯수
		model.addAttribute("startPage", startPage);	//페이지 처리 위한 시작 페이지 
		
		return "user/user_list";
	}
	
	//데이터 삽입 페이지 이동
	@RequestMapping(value = "user/user_insert")
	public String go_insert() {
		
		return "user/insert_user";
	}
	
	//아이디 중복 체크
	@RequestMapping(value="/user/username_check",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> user_id_check(@RequestParam("username") String username){
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			User list = user_Repository.findByUsername(username);
			if(list == null) {
				map.put("message", "사용가능한 아이디입니다.");
			}else {
				map.put("message", "이미 사용되고 있는 아이디입니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	
	//데이터 삽입 (유저 정보)
	@RequestMapping(value = "user/insert_logic",method=RequestMethod.POST)
	public ModelAndView insert(Model model, User user) {
		
		user_Service.insert_user(user);

		//회원 조회 페이지로 리다이렉트
		ModelAndView url = new ModelAndView("redirect:/go_user");

		return url;
	}
	
	//데이터 삭제
	@RequestMapping(value="user/delete_user/{deleteUser}",method=RequestMethod.GET)
	public ModelAndView delete_user(@PathVariable("deleteUser") String deleteUser) {
		//리다이렉트를 위해서 ModelAndView 객체 선언(리다이렉트 한 이유는 삭제 후 새로고침 시 또 삭제하기 때문)
		ModelAndView url;
		try {
			//삭제하는 메소드 호출
			user_Service.delete_users(deleteUser);
			//리다이렉트 할 경로 설정
			url = new ModelAndView("redirect:/go_user");
		}catch(Exception e) {
			e.printStackTrace();
			url = new ModelAndView("redirect:/go_user");
		}
		//회원 조회 페이지로 리다이렉트
		return url;
	}
	
	//더미 데이터 생성
	@RequestMapping(value="/user/dummy")
	public String dummy() {
		
		for(Integer i=0; i < 100; i++) {
			User user = new User("testkopo"+i.toString());
			user_Repository.save(user);			
		}
		
		//회원 조회 페이지로 리다이렉트
		return "index";
	}
	
}
