package edu.coldrain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.coldrain.domain.WordVO;
import edu.coldrain.service.WordService;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/example/*")
@Log4j
public class ExampleController {
	
	@Autowired
	private WordService service;

	//실패
	@GetMapping("/example1")
	public void example1(Model model) {
		
		List<WordVO> list = service.getList();
		model.addAttribute("words", list);
		
	}
	
}