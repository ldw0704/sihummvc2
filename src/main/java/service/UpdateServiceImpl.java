package service;

import domain.BoardVO;
import mapper.UpdateMapper;

public class UpdateServiceImpl implements UpdateService{

	@Override
	public int update(BoardVO vo) {
		UpdateMapper mapper = new UpdateMapper();
		return mapper.update(vo);
	}


}
