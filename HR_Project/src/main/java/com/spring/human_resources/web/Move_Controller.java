package com.spring.human_resources.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping(value = "/go_insert_user")
	public String user_insert(Model model) {
		model.addAttribute("type", "user_insert");
		return "index";
	}
	
}
