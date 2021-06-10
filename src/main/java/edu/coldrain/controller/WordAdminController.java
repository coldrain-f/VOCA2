package edu.coldrain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin/word/*")
@Log4j
public class WordAdminController {

	@GetMapping("/word_list")
	public String wordList() {
		log.info("WordAdminController.wordList()");
		
		return "/admin/word_list";
	}
}
