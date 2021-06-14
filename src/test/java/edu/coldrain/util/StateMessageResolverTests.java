package edu.coldrain.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.coldrain.type.CRUDRequestType;
import edu.coldrain.type.DomainType;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class StateMessageResolverTests {
	
	@Test
	public void testFolderRegister() {
		StateMessageResolver mr = new StateMessageResolver(10, DomainType.FOLDER, CRUDRequestType.REGISTER, true);
		String stateMessage = mr.getStateMessage();
		
		log.info(stateMessage);
	}
	
	@Test
	public void testFolderModify() {
		StateMessageResolver mr = new StateMessageResolver(10, DomainType.FOLDER, CRUDRequestType.MODIFY, true);
		String stateMessage = mr.getStateMessage();
		
		log.info(stateMessage);
	}
	
	@Test
	public void testFolderRemove() {
		StateMessageResolver mr = new StateMessageResolver(10, DomainType.FOLDER, CRUDRequestType.MODIFY, true);
		String stateMessage = mr.getStateMessage();
		
		log.info(stateMessage);
	}
	
	@Test
	public void testCategoryRegister() {
		StateMessageResolver mr = new StateMessageResolver(10, DomainType.CATEGORY, CRUDRequestType.REGISTER, true);
		String stateMessage = mr.getStateMessage();
		
		log.info(stateMessage);
	}
	
	@Test
	public void testCategoryModify() {
		StateMessageResolver mr = new StateMessageResolver(10, DomainType.CATEGORY, CRUDRequestType.MODIFY, true);
		String stateMessage = mr.getStateMessage();
		
		log.info(stateMessage);
	}
	
	@Test
	public void testCategoryRemove() {
		StateMessageResolver mr = new StateMessageResolver(10, DomainType.CATEGORY, CRUDRequestType.REMOVE, true);
		String stateMessage = mr.getStateMessage();
		
		log.info(stateMessage);
	}
	
	@Test
	public void testWordRegister() {
		StateMessageResolver mr = new StateMessageResolver(10, DomainType.WORD, CRUDRequestType.REGISTER, true);
		String stateMessage = mr.getStateMessage();
		
		log.info(stateMessage);
	}
	
	@Test
	public void testWordModify() {
		StateMessageResolver mr = new StateMessageResolver(10, DomainType.WORD, CRUDRequestType.MODIFY, true);
		String stateMessage = mr.getStateMessage();
		
		log.info(stateMessage);
	}
	
	@Test
	public void testWordRemove() {
		StateMessageResolver mr = new StateMessageResolver(10, DomainType.WORD, CRUDRequestType.REMOVE, true);
		String stateMessage = mr.getStateMessage();
		
		log.info(stateMessage);
	}
}
