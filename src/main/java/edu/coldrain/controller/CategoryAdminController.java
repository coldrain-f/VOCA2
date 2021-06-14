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
import edu.coldrain.domain.FolderVO;
import edu.coldrain.service.CategoryService;
import edu.coldrain.service.FolderService;
import edu.coldrain.type.CRUDRequestType;
import edu.coldrain.type.DomainType;
import edu.coldrain.util.StateMessageResolver;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin/category/*")
@Log4j
public class CategoryAdminController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private FolderService folderService;
	
	@GetMapping("/list")
	public String list(FolderVO folder, Model model) {
		log.info("CategoryAdminController.list()");
	
		//폴더 리스트 가져오기
		List<FolderVO> folderList = folderService.getList();
		folderList.forEach(f -> log.info(f));
		model.addAttribute("folderList", folderList);
		
		log.info("GET FOLDER = " + folder);
		
		if(folder.getFolder_name() != null) {
			//조회된 폴더의 이름으로 카테고리 리스트 가져오기 getListByFno()
			int fno = folderService.getByFolderName(folder.getFolder_name()).getFno();
			log.info("FNO = " + fno);
			List<CategoryVO> categoryList = categoryService.getListByFno(fno);
			categoryList.forEach(category -> log.info(category));
			model.addAttribute("categoryList", categoryList);
			
			FolderVO selectedFolder = folderService.getByFolderName(folder.getFolder_name());
			model.addAttribute("selectedFolder", selectedFolder);
		}
		
		
		return "/admin/category_list";
	}
	
	@PostMapping("/remove")
	public String remove(CategoryVO category, RedirectAttributes rttr) {
		log.info("CategoryAdminController.remove()");
		
		log.info("CATEGORY = " + category);
		boolean success = categoryService.remove(category.getCno());
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
		boolean success = categoryService.modify(category);
		log.info("MODIFY SUCCESS = " + success);
		
		StateMessageResolver mr = 
				new StateMessageResolver(category.getCno(), DomainType.CATEGORY, CRUDRequestType.MODIFY, success);
		String stateMessage = mr.getStateMessage();
		
		rttr.addFlashAttribute("stateMessage", stateMessage);
		
		return "redirect:/admin/category/list";
	}
	
	@PostMapping("/register")
	public String register(CategoryVO category, FolderVO folder, RedirectAttributes rttr) {
		log.info("CategoryAdminController.register()");
		
		//STATE SETUP
		category.setState("NEW");
		
		//FNO SETUP
		//폴더 이름으로 fno 조회한다.
		int fno = folderService.getByFolderName(folder.getFolder_name())
							   .getFno();
		category.setFno(fno);
		
		log.info("CATEGORY = " + category);
		boolean success = ( categoryService.register(category) == 1 );
		log.info("CATEGORY REGISTER SUCCESS = " + success);
		
		StateMessageResolver mr = 
				new StateMessageResolver(category.getCno(), DomainType.CATEGORY, CRUDRequestType.REGISTER, success);
		String stateMessage = mr.getStateMessage();
		
		rttr.addFlashAttribute("stateMessage", stateMessage);
		
		return "redirect:/admin/category/list";
	}
}
