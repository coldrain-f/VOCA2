package edu.coldrain.mapper;

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
public class CategoryMapperTests {

	@Autowired
	private CategoryMapper mapper;
	
	@Test
	public void testExist() {
		assertNotNull(mapper);
	}
	
	@Test
	public void testGetList() {
		List<CategoryVO> categoryList = mapper.getList();
		categoryList.forEach(category -> log.info(category));
	}
	
	@Test
	public void testGetListByFno() {
		List<CategoryVO> categoryList = mapper.getListByFno(2);
		categoryList.forEach(category -> log.info(category));
	}
	
	@Test
	public void testInsert() {
		CategoryVO category = new CategoryVO();
		category.setCategory_name("Unit 06 - 테스트");
		category.setState("NEW");
		category.setFno(1);
		
		int count = mapper.insert(category);
		log.info("INSERT COUNT = " + count);
	}
	
	@Test //문제 있음 cno가 1이 더 증가해서 들어간다.
	public void testInsertSelectKey() {
		CategoryVO category = new CategoryVO();
		category.setCategory_name("Unut 09 - 테스트3");
		category.setState("NEW");
		category.setFno(1);
		
		int count = mapper.insertSelectKey(category);
		log.info("INSERT SELECT KEY COUNT = " + count);
		log.info("CATEGORY.CNO = " + category.getCno());
	}
	
	@Test
	public void testRead() {
		CategoryVO category = mapper.read(22);
		log.info("CATEGORY = " + category);
	}
	
	@Test
	public void testReadByCategoryNameAndFno() {
		
		log.info("-------------------------------------------");
		String category_name = "Unit 02 - 일상1";
		int fno = 1;
		log.info("category_name = " + category_name);
		log.info("fno = " + fno);
		
		CategoryVO category = mapper.readByCategoryNameAndFno(category_name, 1);
		log.info("CATEGORYY = " + category);
	}
	
	@Test
	public void testUpdate() {
		CategoryVO category = mapper.read(22);
		category.setCategory_name("Unit 06 - 테스트 업데이트");
		category.setState("OLD");
		
		int count = mapper.update(category);
		log.info("UPDATE COUNT = " + count);
	}
	
	@Test
	public void testDelete() {
		int count = mapper.delete(22);
		log.info("DELETE COUNT = " + count);
	}
}
