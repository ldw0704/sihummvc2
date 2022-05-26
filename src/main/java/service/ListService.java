package service;

import java.util.Collection;

import domain.BoardVO;

public interface ListService {
	public Collection<BoardVO> read();
}
