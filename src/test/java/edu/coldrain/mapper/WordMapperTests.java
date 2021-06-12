package edu.coldrain.mapper;

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
public class WordMapperTests {
	
	@Autowired
	private WordMapper mapper;
	
	@Test
	public void testExist() {
		assertNotNull(mapper);
	}
	
	@Test
	public void testGetList() {
		List<WordVO> list = mapper.getList();
		list.forEach(word -> log.info(word));
	}
	
	@Test
	public void testInsert() {
		WordVO word = new WordVO();
		word.setWord_name("tasty");
		word.setWord_meaning("맛있는");
		word.setState("NEW");
		word.setCno(1);
		
		int count = mapper.insert(word);
		log.info("INSERT COUNT = " + count);
	}
	
	@Test
	public void testInsertSelectKey() {
		WordVO word = new WordVO();
		word.setWord_name("tasty");
		word.setWord_meaning("맛있는");
		word.setState("NEW");
		word.setCno(1);
		
		int count = mapper.insertSelectKey(word);
		log.info("INSERT SELECT KEY COUNT = " + count);
		log.info("WORD.WNO = " + word.getWno());
	}
	
	@Test
	public void testRead() {
		WordVO word = mapper.read(22);
		log.info("WORD = " + word);
	}
	
	@Test
	public void testUpdate() {
		WordVO word = mapper.read(22);
		word.setWord_name("street food");
		word.setWord_meaning("길거리 음식");
		word.setState("OLD");
		word.setCno(1);
		
		int count = mapper.update(word);
		log.info("UPDATE COUNT = " + count);
	}
	
	@Test
	public void testDelete() {
		int count = mapper.delete(22);
		log.info("DELETE COUNT = " + count);
	}
}
