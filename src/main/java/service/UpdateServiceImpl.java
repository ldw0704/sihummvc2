package service;

import domain.BoardVO;
import mapper.UpdateMapper;

public class UpdateServiceImpl implements UpdateService{

	@Override
	public void update(BoardVO vo) {
		UpdateMapper mapper = new UpdateMapper();
		mapper.update(vo);
	}


}
