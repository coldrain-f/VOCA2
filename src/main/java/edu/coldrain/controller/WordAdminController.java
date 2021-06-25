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

	/*
	 * 작업해야 하는 내용
	 * 1. 단어가 읽기다 기본편에서 단어를 수정, 삭제하면 첫 번째 레코드로 이동하는 현상을
	 * 수정, 삭제 후 단어가 읽기다 기본편(수정, 삭제 작업을 한 카테고리)으로 유지가 되어야 함.     -------> 미해결
	 * 
	 * 2. 추가하기가 동작되지 않으니 동작되도록 수정해야 한다.
	 * 그리고 추가하고 추가한 카테고리(단어가 읽기다 기본편의 Unit 01)를 조회하도록 설정해야 한다.    --------> 미해결
	 * 
	 * 3. 단어가 읽기다 테스트편2에서 단어가 읽기다 기본편으로 넘어갔을 때,
	 * Unit 01 요리의 단어가 조회가 되지 않는다. 원인을 찾아야 한다. -------> 해결
	 */
	
	@Autowired
	private WordService wordService;
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/list2")
	public String list2(FolderVO folderVO, CategoryVO categoryVO, Model model) {
		log.info("WordAdminController.list2()");
		
		// 맨 처음 진입했을 때 VO의 값은?  --> folder_name = null, category_name = null
		// 이후에 체인지 이벤트가 발생해서 넘어오면 FolderVO는 이름만 넘어온다.
		log.info("FOLDER_VO = " + folderVO); 
		log.info("CATEGORY_VO = " + categoryVO);
		
		// 화면에 뿌려줄 모든 폴더를 조회한다. ( 모든 폴더는 항상 가져와야 한다 )
		List<FolderVO> folderList = folderService.getList();
		// 조회한 폴더를 모델에 담는다.
		model.addAttribute("folderList", folderList);
		
		// 맨 처음 진입했을 때 셀렉트 초기화 설정
		if (folderVO.getFolder_name() == null) {
			// 가장 처음 화면에 뿌려줄 맨 위 폴더의 속해있는 카테고리 리스트를 조회한다.
			List<CategoryVO> categoryList = categoryService.getListByFno(folderList.get(0).getFno());
			
			// 조회한 카테고리를 모델에 담는다.
			model.addAttribute("categoryList", categoryList);
			
			// 폴더 이름을 첫 번째 레코드로 설정한다. ( 폴더 셀렉트에서 selected로 사용하기 위함 )
			folderVO.setFolder_name(folderService.getFirstRecord().getFolder_name());
			
		} else {
			// 이후에 폴더 셀렉트 체인지 이벤트가 발생한다면?
			// 넘어온 folderVO 이름 정보를 가지고 카테고리 리스트를 조회해야 한다.
			int fno = folderService.getByFolderName(folderVO.getFolder_name()).getFno();
			List<CategoryVO> categoryList = categoryService.getListByFno(fno);
			model.addAttribute("categoryList", categoryList);
		}
		
		// 조회 버튼을 클릭하면? --> 폴더 이름과 카테고리 이름이 넘어온다.
		if (folderVO.getFolder_name() != null 
				&& categoryVO.getCategory_name() != null && categoryVO.getCategory_name() != "") {
			//폴더에 카테고리가 없는 상태로 조회 버튼을 누르면 카테고리 이름이 null일테니 그건 그냥 조회 안 되도록 처리한다.
			String categoryName = categoryVO.getCategory_name();
			int fno = folderService.getByFolderName(folderVO.getFolder_name()).getFno();
			int cno = categoryService.getByCategoryNameAndFno(categoryName, fno).getCno();
			List<WordVO> wordList = wordService.getListByCno(cno);
			
			//화면에 뿌려줄 단어 리스트를 모델에 담는다.
			model.addAttribute("wordList", wordList);
			
			// 카테고리 이름을 설정한다.( 카테고리 셀렉트에서 selected로 사용하기 위함 )
			categoryVO.setCategory_name(categoryName);
		}
		
		
		return "/admin/word_list2";
	}
	
	@GetMapping("/list")
	public String list(FolderVO folderVO, CategoryVO categoryVO, Model model) {
		log.info("WordAdminController.list()");
		
		//상단 메뉴 셀렉트에 뿌려줄 폴더의 리스트를 가지고 온다.
		List<FolderVO> folderList = folderService.getList();
		folderList.forEach(folder -> log.info(folder));
		model.addAttribute("folderList", folderList);
		
		//첫 번째 값으로 가져오도록 수정 했다. 
		if (folderVO.getFolder_name() == null) {
			folderVO = folderService.getFirstRecord();
			
		}
		
		//카테고리가 있던 없던 일단 맨 위에거를 가지고 온다.
		//1. 폴더 이름으로 FNO를 얻어온다.
		FolderVO f = folderService.getByFolderName(folderVO.getFolder_name());
		//2. 얻어온 FNO를 가지고 첫 번째 레코드를 가지고 온다.
		//getFirstRecordByFolderName()
		CategoryVO c = categoryService.getFirstRecordByFno(f.getFno());
		categoryVO.setCategory_name(c.getCategory_name());
		
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
		if (selectedCategory == null) { //가져온 카테고리가 없다면... (수정 해야 됨)
			log.info("널입니다................................................");
		} else {
			List<WordVO> wordList = wordService.getListByCno(selectedCategory.getCno());
			model.addAttribute("wordList", wordList);			
		}
		
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
