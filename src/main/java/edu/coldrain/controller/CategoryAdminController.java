package edu.coldrain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.coldrain.domain.CategoryVO;
import edu.coldrain.service.CategoryService;
import edu.coldrain.type.CRUDRequestType;
import edu.coldrain.type.DomainType;
import edu.coldrain.util.StateMessageResolver;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin/category/*")
@Log4j
public class CategoryAdminController {

	@Autowired
	private CategoryService service;
	
	@GetMapping("/list")
	public String list(Model model) {
		log.info("CategoryAdminController.list()");
	
		List<CategoryVO> categoryList = service.getList();
		categoryList.forEach(category -> log.info(category));
		model.addAttribute("categoryList", categoryList);
		
		return "/admin/category_list";
	}
	
	@PostMapping("/remove")
	public String remove(CategoryVO category, RedirectAttributes rttr) {
		log.info("CategoryAdminController.remove()");
		
		log.info("CATEGORY = " + category);
		boolean success = service.remove(category.getCno());
		log.info("CATEGORY REMOVE SUCCESS = " + success);
		
		StateMessageResolver mr = 
				new StateMessageResolver(category.getCno(), DomainType.CATEGORY, CRUDRequestType.REMOVE, success);
		String stateMessage = mr.getStateMessage();
		
		rttr.addFlashAttribute("stateMessage", stateMessage);
		
		return "redirect:/admin/category/list";
	}
	
	@PostMapping("/modify")
	public String modify(CategoryVO category, RedirectAttributes rttr) {
		log.info("CategoryAdminController.modify()");
		
		//STATE SETUP
		category.setState("NEW");
		
		log.info("CATEGORY = " + category);
		boolean success = service.modify(category);
		log.info("MODIFY SUCCESS = " + success);
		
		StateMessageResolver mr = 
				new StateMessageResolver(category.getCno(), DomainType.CATEGORY, CRUDRequestType.MODIFY, success);
		String stateMessage = mr.getStateMessage();
		
		rttr.addFlashAttribute("stateMessage", stateMessage);
		
		return "redirect:/admin/category/list";
	}
	
	@PostMapping("/register")
	public String register(CategoryVO category, RedirectAttributes rttr) {
		log.info("CategoryAdminController.register()");
		
		//STATE SETUP
		category.setState("NEW");
		
		//FNO SETUP
		category.setFno(1);
		
		log.info("CATEGORY = " + category);
		boolean success = ( service.register(category) == 1 );
		log.info("CATEGORY REGISTER SUCCESS = " + success);
		
		StateMessageResolver mr = 
				new StateMessageResolver(category.getCno(), DomainType.CATEGORY, CRUDRequestType.REGISTER, success);
		String stateMessage = mr.getStateMessage();
		
		rttr.addFlashAttribute("stateMessage", stateMessage);
		
		return "redirect:/admin/category/list";
	}
}
