package com.spring.human_resources.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.human_resources.repository.HR_Repository;

@Controller
public class HR_Controller {
	
	@Autowired
	HR_Repository hr_Repositoy;
	
	//인사 기본 출력
	@RequestMapping(value="hr")
	public String nolist(Model model){
		model.addAttribute("list",hr_Repositoy.findAll());
		return "hr/hr_page";
	}

}
