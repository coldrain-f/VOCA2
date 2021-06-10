package edu.coldrain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin/category/*")
@Log4j
public class CategoryAdminController {

	@GetMapping("/category_list")
	public String categoryList() {
		log.info("CategoryAdminController.categoryList()");
		
		return "/admin/category_list";
	}
}
