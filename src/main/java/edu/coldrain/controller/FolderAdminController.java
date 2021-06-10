package edu.coldrain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin/folder/*")
@Log4j
public class FolderAdminController {
	
	@GetMapping("/folder_list")
	public String folderList() {
		log.info("FolderAdminController.folder_list()");
		
		return "/admin/folder_list";
	}
}
