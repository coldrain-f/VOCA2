package edu.coldrain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.coldrain.domain.CategoryVO;

public interface CategoryMapper {
	
	public List<CategoryVO> getList();
	
	public List<CategoryVO> getListByFno(int fno);
	
	public int insert(CategoryVO category);
	
	public int insertSelectKey(CategoryVO category);
	
	public CategoryVO read(int cno);
	
	public CategoryVO readByCategoryNameAndFno(@Param("category_name") String category_name, @Param("fno") int fno);
	
	public int update(CategoryVO category);
	
	public int delete(int cno);
	
}
