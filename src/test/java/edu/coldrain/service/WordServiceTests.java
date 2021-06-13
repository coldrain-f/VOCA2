package edu.coldrain.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.coldrain.domain.WordVO;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class WordServiceTests {
	
	@Autowired
	private WordService service;
	
	@Test
	public void testExist() {
		assertNotNull(service);
	}
	
	@Test
	public void testGetList() {
		List<WordVO> list = service.getList();
		list.forEach(word -> log.info(word));
	}
	
	@Test
	public void testRegister() {
		WordVO word = new WordVO();
		word.setWord_name("tasty");
		word.setWord_meaning("맛있는");
		word.setState("NEW");
		word.setCno(1);
		
		int count = service.register(word);
		log.info("REGISTER COUNT = " + count);
		log.info("WORD.WNO = " + word.getWno());
	}
	
	@Test
	public void testGet() {
		WordVO word = service.get(24);
		log.info("WORD = " + word);
	}
	
	@Test
	public void testModify() {
		WordVO word = service.get(24);
		word.setWord_name("delicious");
		word.setWord_meaning("맛있는");
		word.setState("OLD");
		word.setCno(1);
		
		boolean success = service.modify(word);
		log.info("MODIFY SUCCESS = " + success);
	}
	
	@Test
	public void testRemove() {
		boolean success = service.remove(24);
		log.info("REMOVE SUCCESS = " + success);
	}
}
