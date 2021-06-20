package edu.coldrain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.coldrain.domain.FolderVO;
import edu.coldrain.domain.WordVO;

public interface WordService {
	
	public List<WordVO> getList();
	
	public List<WordVO> getListByCno(int cno);
	
	public int register(WordVO word);
	
	public WordVO get(int wno);
	
	public boolean modify(WordVO word);
	
	public boolean remove(int wno);

}
