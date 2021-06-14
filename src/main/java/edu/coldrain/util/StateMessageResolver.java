package edu.coldrain.util;

import edu.coldrain.type.CRUDRequestType;
import edu.coldrain.type.DomainType;
import lombok.Setter;

@Setter
public class StateMessageResolver {

	private int no;
	private CRUDRequestType requestType;
	private DomainType domainType;
	private boolean success;
	private String stateMessage;
	
	public StateMessageResolver(int no, DomainType domainType, CRUDRequestType requestType, boolean success) {
		this.no = no;
		this.requestType = requestType;
		this.domainType = domainType;
		this.success = success;
		
		this.stateMessage = createStateMessage();
	}
	
	private String createStateMessage() {
		
		StringBuilder stateMessage = new StringBuilder();
		stateMessage.append("[ " + this.no + " ]" + "번 ");
		
		if ( domainType == DomainType.FOLDER ) {
			stateMessage.append(" 폴더의 ");
			
		} else if ( domainType == DomainType.CATEGORY ) {
			stateMessage.append(" 카테고리의 ");
			
		} else if ( domainType == DomainType.WORD ) {
			stateMessage.append(" 단어의 ");
		}
		
		
		if ( requestType == CRUDRequestType.REGISTER ) {
			stateMessage.append("추가가 ");
			
		} else if ( requestType == CRUDRequestType.MODIFY ) {
			stateMessage.append("수정이 ");
			
		} else if ( requestType == CRUDRequestType.REMOVE ) {
			stateMessage.append("삭제가 ");
			
		}
		
		if ( this.success ) {
			stateMessage.append("완료 되었습니다.");
			
		} else {
			stateMessage.append("실패 되었습니다.");
			
		}
		
		return stateMessage.toString();
	}

	public String getStateMessage() {
		return this.stateMessage;
	}
	
}
