package service;

import domain.BoardVO;
import mapper.WirterMapper;

public class WriterServiceImpl implements WriterService {

	@Override
	public void insert(BoardVO vo) {
		WirterMapper mapper = new WirterMapper();
		mapper.insert(vo);
	}


}
