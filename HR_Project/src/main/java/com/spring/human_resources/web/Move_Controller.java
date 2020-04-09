package com.spring.human_resources.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Move_Controller {

	//시작 페이지 이동
	@RequestMapping(value = "/")
	public String start() {
		return "index";
	}
	
	//회원관리 페이지 이동
	@RequestMapping(value="go_user")
	public String go_user(Model model){
		model.addAttribute("type", "go_user");
		return "index";
	}
	
	//회원 데이터 입력 페이지 이동
	@RequestMapping(value = "/go_insert_user")
	public String go_user_insert(Model model) {
		model.addAttribute("type", "user_insert");
		return "index";
	}
	
	//회원 상세 정보 페이지 이동
	@RequestMapping(value = "/go_user_info/{username}", method = RequestMethod.GET)
	public String go_user_info(Model model,@PathVariable("username") String username) {
		model.addAttribute("type", "user_info");
		model.addAttribute("username", username);
		return "index";
	}
	
	//인사관리 페이지 이동
	@RequestMapping(value="go_hr")
	public String go_hr(Model model){
		model.addAttribute("type", "go_hr");
		return "index";
	}
	
}
