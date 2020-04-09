package com.spring.human_resources.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.human_resources.domain.User;
import com.spring.human_resources.repository.User_Repository;
import com.spring.human_resources.service.User_Service;

@Controller
public class User_Controller {
	
	@Autowired
	User_Repository user_Repository;
	@Autowired
	User_Service user_Service;
	
	//기본 출력
	@RequestMapping(value="user/list")
	public String nolist(){
		return "user/user_page";
	}
	
	//전체 데이터 출력(처음 로드 시 불려오는 페이지)
	@RequestMapping(value="user/list/{pageNum}/{startPage}/{sortType}/{sortDirection}",method = RequestMethod.GET)
	public String list(Model model, @PathVariable("pageNum") int pageNum, @PathVariable("startPage") int startPage, @PathVariable("sortType") String sortType, @PathVariable("sortDirection") String sortDirection){
		
		
		//페이지 적용한 전체 데이터 출력 후 list에 담음 
		List<User> list = user_Service.findAllPageable(pageNum,sortType,sortDirection);
		//페이지를 나타낼 데이터 갯수 구하기
		Long pageCnt = user_Service.calc_pagecnt();
		
		//데이터를 모델에 담음
		model.addAttribute("list", list);	//전체 데이터		
		model.addAttribute("pageCnt", pageCnt);	//데이터 갯수에 기반한 전체 페이지 수
		model.addAttribute("pageNum", pageNum);	//해당 페이지 번호
		model.addAttribute("startPage", startPage);	//페이지처리를 위한 시작 페이지값
		model.addAttribute("sortType", sortType);	//정렬 타입
		model.addAttribute("sortDirection", sortDirection);	//정렬 방향
		
		return "user/user_list";
	}
	

	
	//데이터 상세보기
	@RequestMapping(value="/user/user_info/{username}",method = RequestMethod.GET)
	public String user_info(Model model, @PathVariable("username") String username){
		
		User user = user_Repository.findByUsername(username);
		
		model.addAttribute("user", user);
		
		return "user/user_info";
	}
	
	//데이터 검색 조회
	@RequestMapping(value="user/onelist/{select_column}/{find_str}/{pageNum}/{startPage}/{sortType}/{sortDirection}", method = RequestMethod.GET)
	public String onelist(Model model, @PathVariable("select_column") String select_column, @PathVariable("find_str")String find_str, @PathVariable("pageNum") int pageNum, @PathVariable("startPage") int startPage, @PathVariable("sortType") String sortType, @PathVariable("sortDirection") String sortDirection){
		
		//필터와 페이지 기능처리한 데이터를 담음
		List<User> list = user_Service.findAllByFilterAndPageable(select_column, find_str, pageNum,sortType,sortDirection);
		//페이지 갯수를 구함
		Long pageCnt = user_Service.calc_pagecnt(select_column, find_str);
		//데이터를 모델에 담음
		model.addAttribute("list",list);	//필터 처리한 데이터 
		model.addAttribute("pageCnt",pageCnt);	//페이지 갯수
		model.addAttribute("startPage", startPage);	//페이지 처리 위한 시작 페이지 
		model.addAttribute("pageNum", pageNum);	//페이지 처리 위한 시작 페이지 
		
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
		//데이터를 넣는 서비스 호출
		user_Service.insert_user(user);

		//회원 조회 페이지로 리다이렉트
		ModelAndView url = new ModelAndView("redirect:/go_user");

		return url;
	}
	
	//데이터 수정 (유저 정보)
	@RequestMapping(value = "user/update_user",method=RequestMethod.POST)
	public ModelAndView update_user(Model model, User user,String update_name,@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date update_birth,String update_tel,String update_e_mail, String update_S_type) {
	
		//데이터를 넣는 서비스 호출
		user_Service.update_user(user,update_name,update_birth,update_tel,update_e_mail,update_S_type);

		//회원 조회 페이지로 리다이렉트
		ModelAndView url = new ModelAndView("redirect:/go_user");

		return url;
	}
	
	//데이터 삭제(상세보기에서 삭제)
	@RequestMapping(value="user/delete_user/{deleteUser}",method=RequestMethod.GET)
	public ModelAndView delete_user(Model model,@PathVariable("deleteUser") String deleteUser) {
		ModelAndView url;
		user_Service.delete_user(deleteUser);
		url = new ModelAndView("redirect:/go_user");
		return url;
	}
	
	//데이터 삭제(체크된 데이터들 삭제)
	@RequestMapping(value="user/delete_users/{deleteUser_list}",method=RequestMethod.GET)
	public ModelAndView delete_users(@PathVariable("deleteUser_list") String deleteUser_list) {
		//리다이렉트를 위해서 ModelAndView 객체 선언(리다이렉트 한 이유는 삭제 후 새로고침 시 또 삭제하기 때문)
		ModelAndView url;
		try {
			//삭제하는 메소드 호출
			user_Service.delete_users(deleteUser_list);
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
	
	//데이터 정렬하기
	@RequestMapping(value="/user/sort/{sortType}/{sortDirection}/{pageNum}/{startPage}", method = RequestMethod.GET)
	public String sort(Model model, @PathVariable("sortType") String sortType, @PathVariable("sortDirection") String sortDirection, @PathVariable("pageNum") int pageNum,@PathVariable("startPage") int startPage ) {
		//1. 정렬 기준
		//2. 정렬 방향
		//3. 출력할 페이지 번호
		System.out.println(sortDirection);
		System.out.println(sortType);
		System.out.println(pageNum);
		
		Page<User> page = null;
		
		if(sortType.equals("username")) {
			page =  user_Repository.findAll(PageRequest.of(pageNum,10, Sort.Direction.DESC, sortType));			
		}else if(sortType.equals("created")){
			page =  user_Repository.findAll(PageRequest.of(pageNum,10, Sort.Direction.DESC, sortType));
		}
		
		List<User> list = page.getContent();
		Long pageCnt = user_Service.calc_pagecnt();
		
		model.addAttribute("list", list);	//전체 데이터		
		model.addAttribute("pageCnt", pageCnt);	//데이터 갯수에 기반한 전체 페이지 수
		model.addAttribute("startPage", startPage);	//페이지처리를 위한 시작 페이지값
		model.addAttribute("pageNum", pageNum);	//페이지처리를 위한 시작 페이지값
		
		return "user/user_list";
	}
}
