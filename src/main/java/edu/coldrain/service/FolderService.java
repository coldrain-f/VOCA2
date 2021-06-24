package edu.coldrain.service;

import java.util.List;

import edu.coldrain.domain.FolderVO;

public interface FolderService {

	public List<FolderVO> getList();
	
	public int register(FolderVO folder);
	
	public FolderVO get(int fno);
	
	public FolderVO getFirstRecord();
	
	public FolderVO getByFolderName(String folder_name);
	
	public boolean modify(FolderVO folder);
	
	public boolean remove(int fno);
	
}
