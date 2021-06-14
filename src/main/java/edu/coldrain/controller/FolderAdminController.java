package edu.coldrain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.coldrain.domain.FolderVO;
import edu.coldrain.service.FolderService;
import edu.coldrain.type.CRUDRequestType;
import edu.coldrain.type.DomainType;
import edu.coldrain.util.StateMessageResolver;
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
	public String remove(FolderVO folder, RedirectAttributes rttr) {
		log.info("FolderAdminController.remove()");
		
		log.info("FOLDER.FNO = " + folder.getFno());
		boolean success = service.remove(folder.getFno());
		log.info("FOLDER REMOVE SUCCESS = " + success);
	
		StateMessageResolver mr = 
				new StateMessageResolver(folder.getFno(), DomainType.FOLDER, CRUDRequestType.REMOVE, success);
		String stateMessage = mr.getStateMessage();
		rttr.addFlashAttribute("stateMessage", stateMessage);
		
		return "redirect:/admin/folder/list";
	}
	
	@PostMapping("/register")
	public String register(FolderVO folder, RedirectAttributes rttr) {
		log.info("FolderAdminController.register()");
		
		log.info("FOLDER = " + folder);
		boolean success = ( service.register(folder) == 1 );
		log.info("FOLDER REGISTER SUCCESS = " + success);
		
		StateMessageResolver mr = 
				new StateMessageResolver(folder.getFno(), DomainType.FOLDER, CRUDRequestType.REGISTER, success);
		String stateMessage = mr.getStateMessage();
		rttr.addFlashAttribute("stateMessage", stateMessage);			
		
		return "redirect:/admin/folder/list";
	}
	
	@PostMapping("modify")
	public String modify(FolderVO folder, RedirectAttributes rttr) {
		log.info("FolderAdminController.modify()");
		
		log.info("FOLDER = " + folder);
		boolean success = service.modify(folder);
		log.info("FOLDER MODIFY SUCCESS = " + success);
		
		StateMessageResolver mr = 
				new StateMessageResolver(folder.getFno(), DomainType.FOLDER, CRUDRequestType.MODIFY, success);
		String stateMessage = mr.getStateMessage();
		rttr.addFlashAttribute("stateMessage", stateMessage);
		
		return "redirect:/admin/folder/list";
	}
	
}
