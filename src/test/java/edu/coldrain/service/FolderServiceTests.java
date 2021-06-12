package edu.coldrain.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.coldrain.domain.FolderVO;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class FolderServiceTests {

	@Autowired
	private FolderService service;
	
	
	@Test
	public void testExist() {
		assertNotNull(service);
	}
	
	@Test
	public void testGetList() {
		List<FolderVO> folderList = service.getList();
		folderList.forEach(folder -> log.info(folder));
	}
	
	@Test
	public void testRegister() {
		FolderVO folder = new FolderVO();
		folder.setFolder_name("일본어 상용한자 2160자");
		int insertCount = service.register(folder);
		log.info("insertCount = " + insertCount);
		log.info("FOLDER.FNO = " + folder.getFno());
	}
	
	@Test
	public void testGet() {
		FolderVO folder = service.get(24);
		log.info("folder = " + folder);
	}
	
	@Test
	public void testModify() {
		FolderVO folder = service.get(24);
		folder.setFolder_name("일본어 상용한자 2160자 (수정)");
		boolean modifySuccess = service.modify(folder);
		log.info("modify success = " + modifySuccess);
		
		log.info(service.get(folder.getFno()));
	}
	
	@Test
	public void testRemove() {
		boolean removeSuccess = service.remove(43);
		log.info("remove success = " + removeSuccess);
		
		service.getList().forEach(board -> log.info(board));
	}
}
