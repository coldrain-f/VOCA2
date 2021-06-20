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
import edu.coldrain.domain.WordVO;
import edu.coldrain.service.CategoryService;
import edu.coldrain.service.FolderService;
import edu.coldrain.service.WordService;
import edu.coldrain.type.CRUDRequestType;
import edu.coldrain.type.DomainType;
import edu.coldrain.util.StateMessageResolver;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin/word/*")
@Log4j
public class WordAdminController {

	@Autowired
	private WordService wordService;
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/list")
	public String list(FolderVO folderVO, CategoryVO categoryVO, Model model) {
		log.info("WordAdminController.list()");
		
		//상단 메뉴 셀렉트에 뿌려줄 폴더의 리스트를 가지고 온다.
		List<FolderVO> folderList = folderService.getList();
		folderList.forEach(folder -> log.info(folder));
		model.addAttribute("folderList", folderList);
		
		//나중에 수정해야 한다. ( 맨 처음에 NULL일 때 처리, 나중에 맨 첫 번째 값으로 가져오도록 수정해야 한다. )
		if (folderVO.getFolder_name() == null) {
			folderVO.setFolder_name("단어가 읽기다 테스트편");
		}
		
		if (categoryVO.getCategory_name() == null) {
			categoryVO.setCategory_name("Unit 01 - 요리");
		}
		
		log.info("CATEGORY_VO = " + categoryVO);
		//1. 선택된 폴더를 가지고 온다.
		log.info("FOLDER_VO = " + folderVO);
		FolderVO selectedFolder = folderService.getByFolderName(folderVO.getFolder_name());
		model.addAttribute("selectedFolder", selectedFolder);
		
		//2. 선택된 폴더의 번호를 가지고 카테고리 리스트를 가지고 온다.
		List<CategoryVO> categoryList = categoryService.getListByFno(selectedFolder.getFno());
		model.addAttribute("categoryList", categoryList);
		
		//3. 선택된 카테고리 이름으로 카테고리를 가지고 온다.
		CategoryVO selectedCategory = categoryService.getByCategoryNameAndFno(categoryVO.getCategory_name(), selectedFolder.getFno());
		model.addAttribute("selectedCategory", selectedCategory);
		
		//4. 가져온 카테고리의 번호로 단어 리스트를 가지고 온다.
		List<WordVO> wordList = wordService.getListByCno(selectedCategory.getCno());
		model.addAttribute("wordList", wordList);
		
		return "/admin/word_list";
	}
	
	@PostMapping("/remove")
	public String remove(WordVO word, RedirectAttributes rttr) {
		log.info("WordAdminController.remove()");
		
		log.info("WORD = " + word);
		
		boolean success = wordService.remove(word.getWno());
		log.info("WORD REMOVE SUCCESS = " + success);
		
		StateMessageResolver mr = 
				new StateMessageResolver(word.getWno(), DomainType.WORD, CRUDRequestType.REMOVE, success);
		String stateMessage = mr.getStateMessage();
		rttr.addFlashAttribute("stateMessage", stateMessage);
		
		return "redirect:/admin/word/list";
	}
	
	@PostMapping("/modify")
	public String modify(WordVO word, RedirectAttributes rttr) {
		log.info("WordAdminController.modify()");
		
		//STATE SETUP
		word.setState("NEW");
		
		//CNO SETUP
		word.setCno(1);
		
		log.info("WORD = " + word);
		
		boolean success = wordService.modify(word);
		log.info("WORD MODIFY SUCCESS = " + success);
		
		StateMessageResolver mr = 
				new StateMessageResolver(word.getWno(), DomainType.WORD, CRUDRequestType.MODIFY, success);
		String stateMessage = mr.getStateMessage();
		rttr.addFlashAttribute("stateMessage", stateMessage);
		
		return "redirect:/admin/word/list";
	}
	
	@PostMapping("/register")
	public String register(WordVO word, RedirectAttributes rttr) {
		log.info("WordAdminController.register()");
		
		//STATE SETUP
		word.setState("NEW");
		
		//CNO SETUP
		word.setCno(1);
		
		log.info("WORD = " + word);
		
		boolean success = ( wordService.register(word) == 1 );
		log.info("REGISTER SUCCESS = " + success);
		
		StateMessageResolver mr = 
				new StateMessageResolver(word.getWno(), DomainType.WORD, CRUDRequestType.REGISTER, success);
		String stateMessage = mr.getStateMessage();
		rttr.addFlashAttribute("stateMessage", stateMessage);
		
		return "redirect:/admin/word/list";
	}
}
