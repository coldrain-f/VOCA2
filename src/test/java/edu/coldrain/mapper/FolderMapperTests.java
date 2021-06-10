package edu.coldrain.mapper;

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
public class FolderMapperTests {
	
	@Autowired
	private FolderMapper mapper;
	
	@Test
	public void testExist() {
		assertNotNull(mapper);
	}
	
	@Test
	public void testGetList() {
		List<FolderVO> folderList = mapper.getList();
		
		folderList.forEach(folder -> log.info(folder));
	}
	
	@Test
	public void testInsert() {
		FolderVO folder = new FolderVO();
		folder.setFolder_name("일본어 터잡기");
		int insertCount = mapper.insert(folder);
		
		log.info("insertCount = " + insertCount);
	}
	
	@Test
	public void testRead() {
		FolderVO folder = mapper.read(2);
		log.info("folder = " + folder);
	}
	
	@Test
	public void testUpdate() {
		FolderVO folder = mapper.read(2);
		folder.setFolder_name("단어가 읽기다 실전편");
		int updateCount = mapper.update(folder);
		log.info("updateCount = " + updateCount);
	}
	
	@Test
	public void testDelete() {
		int deleteCount = mapper.delete(22);
		log.info("deleteCount = " + deleteCount);
	}
}
