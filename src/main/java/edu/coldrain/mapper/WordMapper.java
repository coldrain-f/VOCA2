package edu.coldrain.mapper;

import java.util.List;

import edu.coldrain.domain.WordVO;

public interface WordMapper {
	
	public List<WordVO> getList();
	
	public List<WordVO> getListByCno(int cno);
	
	public int insert(WordVO word);
	
	public int insertSelectKey(WordVO word);
	
	public WordVO read(int wno);
	
	public int update(WordVO word);
	
	public int delete(int wno);
	

}
