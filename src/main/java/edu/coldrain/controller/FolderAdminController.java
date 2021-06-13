package edu.coldrain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.coldrain.domain.FolderVO;
import edu.coldrain.service.FolderService;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin/folder/*")
@Log4j
public class FolderAdminController {
	
	@Autowired
	private FolderService service;
	
	@GetMapping("/list")
	public String list(Model model) {
		log.info("FolderAdminController.list()");
		
		List<FolderVO> folderList = service.getList();
		folderList.forEach(folder -> log.info(folder));
		model.addAttribute("folderList", folderList);
		
		return "/admin/folder_list";
	}
	
	@PostMapping("/remove")
	public String remove(FolderVO folder) {
		log.info("FolderAdminController.remove()");
		
		log.info("FOLDER.FNO = " + folder.getFno());
		boolean success = service.remove(folder.getFno());
		log.info("FOLDER REMOVE SUCCESS = " + success);
		
		return "redirect:/admin/folder/list";
	}
	
	@PostMapping("/register")
	public String register(FolderVO folder) {
		log.info("FolderAdminController.register()");
		
		log.info("FOLDER = " + folder);
		int success = service.register(folder);
		log.info("FOLDER REGISTER SUCCESS = " + (success == 1));
		
		return "redirect:/admin/folder/list";
	}
	
	@PostMapping("modify")
	public String modify(FolderVO folder) {
		log.info("FolderAdminController.modify()");
		
		log.info("FOLDER = " + folder);
		boolean success = service.modify(folder);
		log.info("FOLDER MODIFY SUCCESS = " + success);
		return "redirect:/admin/folder/list";
	}
}
