package service;

import domain.BoardVO;
import mapper.ViewMapper;

public class ViewServiceImpl implements ViewService {

	@Override
	public BoardVO read(BoardVO vo) {
		return new ViewMapper().read(vo);
	}


}
