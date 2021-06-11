package edu.coldrain.service;

import java.util.List;

import edu.coldrain.domain.CategoryVO;

public interface CategoryService {

	public List<CategoryVO> getList();
	
	public int register(CategoryVO category);
	
	public CategoryVO get(int cno);
	
	public boolean modify(CategoryVO category);
	
	public boolean remove(int cno);
}
