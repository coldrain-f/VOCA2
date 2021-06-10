package edu.coldrain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.coldrain.domain.FolderVO;
import edu.coldrain.mapper.FolderMapper;

@Service
public class FolderServiceImpl implements FolderService {

	@Autowired
	private FolderMapper mapper;
	
	@Override
	public List<FolderVO> getList() {
		return mapper.getList();
	}

	@Override
	public int register(FolderVO folder) {
		return mapper.insert(folder);
	}

	@Override
	public FolderVO get(int fno) {
		return mapper.read(fno);
	}

	@Override
	public boolean modify(FolderVO folder) {
		return mapper.update(folder) == 1;
	}

	@Override
	public boolean remove(int fno) {
		return mapper.delete(fno) == 1;
	}

}
