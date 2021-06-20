package edu.coldrain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.coldrain.domain.CategoryVO;
import edu.coldrain.mapper.CategoryMapper;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryMapper mapper;

	@Override
	public List<CategoryVO> getList() {
		
		return mapper.getList();
	}
	
	@Override
	public List<CategoryVO> getListByFno(int fno) {
		return mapper.getListByFno(fno);
	}

	@Override
	public int register(CategoryVO category) {
		return mapper.insertSelectKey(category);
	}

	@Override
	public CategoryVO get(int cno) {
		return mapper.read(cno);
	}

	@Override
	public boolean modify(CategoryVO category) {
		return mapper.update(category) == 1;
	}

	@Override
	public boolean remove(int cno) {
		return mapper.delete(cno) == 1;
	}

	@Override
	public CategoryVO getByCategoryNameAndFno(String category_name, int fno) {
		return mapper.readByCategoryNameAndFno(category_name, fno);
	}

	

	
	
}
