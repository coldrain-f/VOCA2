package edu.coldrain.mapper;

import java.util.List;

import edu.coldrain.domain.CategoryVO;

public interface CategoryMapper {
	
	public List<CategoryVO> getList();
	
	public int insert(CategoryVO category);
	
	public CategoryVO read(int cno);
	
	public int update(CategoryVO category);
	
	public int delete(int cno);
}
