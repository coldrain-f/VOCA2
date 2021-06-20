package edu.coldrain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.coldrain.domain.WordVO;
import edu.coldrain.mapper.WordMapper;

@Service
public class WordServiceImpl implements WordService {
	
	@Autowired
	private WordMapper mapper;

	@Override
	public List<WordVO> getList() {
		return mapper.getList();
	}

	@Override
	public int register(WordVO word) {
		return mapper.insertSelectKey(word);
	}

	@Override
	public WordVO get(int wno) {
		return mapper.read(wno);
	}

	@Override
	public boolean modify(WordVO word) {
		return mapper.update(word) == 1;
	}

	@Override
	public boolean remove(int wno) {
		return mapper.delete(wno) == 1;
	}

	@Override
	public List<WordVO> getListByCno(int cno) {
		return mapper.getListByCno(cno);
	}

}
