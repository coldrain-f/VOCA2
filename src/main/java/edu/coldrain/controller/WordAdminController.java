package edu.coldrain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.coldrain.domain.WordVO;
import edu.coldrain.service.WordService;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin/word/*")
@Log4j
public class WordAdminController {

	@Autowired
	private WordService service;
	
	@GetMapping("/list")
	public String list(Model model) {
		log.info("WordAdminController.list()");
		
		List<WordVO> wordList = service.getList();
		wordList.forEach(word -> log.info(word));
		model.addAttribute("wordList", wordList);
		
		return "/admin/word_list";
	}
	
	@PostMapping("/remove")
	public String remove(WordVO word) {
		log.info("WordAdminController.remove()");
		
		log.info("WORD = " + word);
		
		boolean success = service.remove(word.getWno());
		log.info("WORD REMOVE SUCCESS = " + success);
		
		return "redirect:/admin/word/list";
	}
	
	@PostMapping("/modify")
	public String modify(WordVO word) {
		log.info("WordAdminController.modify()");
		
		//STATE SETUP
		word.setState("NEW");
		
		//CNO SETUP
		word.setCno(1);
		
		log.info("WORD = " + word);
		
		boolean success = service.modify(word);
		log.info("WORD MODIFY SUCCESS = " + success);
		
		return "redirect:/admin/word/list";
	}
	
	@PostMapping("/register")
	public String register(WordVO word) {
		log.info("WordAdminController.register()");
		
		//STATE SETUP
		word.setState("NEW");
		
		//CNO SETUP
		word.setCno(1);
		
		log.info("WORD = " + word);
		
		boolean success = ( service.register(word) == 1 );
		log.info("REGISTER SUCCESS = " + success);
		
		return "redirect:/admin/word/list";
	}
}
