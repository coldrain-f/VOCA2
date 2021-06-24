package edu.coldrain.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.coldrain.domain.CategoryVO;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CategoryServiceTests {
	
	@Autowired
	private CategoryService service;
	
	@Test
	public void testExist() {
		assertNotNull(service);
	}
	
	@Test
	public void testGetList() {
		List<CategoryVO> list = service.getList();
		list.forEach(category -> log.info(list));
	}
	
	@Test
	public void testGetListByFno() {
		List<CategoryVO> list = service.getListByFno(1);
		list.forEach(category -> log.info(category));
	}
	
	@Test
	public void testGetByCategoryNameAndFno() {
		CategoryVO category = service.getByCategoryNameAndFno("Unit 01 - 요리", 1);
		log.info("CATEGORY = " + category);
	}
	
	@Test
	public void testRegister() {
		CategoryVO category = new CategoryVO();
		category.setCategory_name("Unit 06 - 테스트");
		category.setState("NEW");
		category.setFno(1);
		
		int count = service.register(category);
		log.info("INSERT COUNT = " + count);
		log.info("CATEGORY.CNO = " + category.getCno());
	}
	
	@Test
	public void testGet() {
		CategoryVO category = service.get(24);
		log.info("CATEGORY = " + category);
	}
	
	@Test
	public void testGetFirstRecordByFno() {
		CategoryVO category = service.getFirstRecordByFno(1);
		log.info(category);
	}
	
	@Test
	public void testModify() {
		CategoryVO category = service.get(24);
		category.setCategory_name("Unit 06 - 테스트2");
		category.setState("OLD");
		
		boolean result = service.modify(category);
		log.info("MODIFY RESULT = " + result);
	}
	
	@Test
	public void testRemove() {
		boolean result = service.remove(24);
		log.info("DELETE RESULT = " + result);
	}
	
	
}
