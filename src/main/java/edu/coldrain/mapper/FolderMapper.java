package edu.coldrain.mapper;

import java.util.List;

import edu.coldrain.domain.FolderVO;

public interface FolderMapper {

	public List<FolderVO> getList();
	
	public int insert(FolderVO folder);
	
	public int insertSelectKey(FolderVO folder);
	
	public FolderVO read(int fno);
	
	public FolderVO readByFolderName(String folder_name);
	
	public int update(FolderVO folder);
	
	public int delete(int fno);

}
