package edu.coldrain.service;

import java.util.List;

import edu.coldrain.domain.CategoryVO;

public interface CategoryService {

	public List<CategoryVO> getList();
	
	public List<CategoryVO> getListByFno(int fno);
	
	public int register(CategoryVO category);
	
	public CategoryVO get(int cno);
	
	public CategoryVO getFirstRecordByFno(int fno);
	
	public CategoryVO getByCategoryNameAndFno(String category_name, int fno);
	
	public boolean modify(CategoryVO category);
	
	public boolean remove(int cno);
}
