package edu.coldrain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.coldrain.domain.CategoryVO;
import edu.coldrain.domain.FolderVO;
import edu.coldrain.service.CategoryService;
import edu.coldrain.service.FolderService;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/home/*")
@Log4j
public class WordLearnController {

	@Autowired
	private FolderService folderService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/learn")
	public void learn(Model model) {
		log.info("WordLearnController.learn()");
		
		List<FolderVO> folderList = folderService.getList();
		model.addAttribute("folderList", folderList);
		
		List<CategoryVO> categoryList = categoryService.getList();
		model.addAttribute("categoryList", categoryList);
	}
}
