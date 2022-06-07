package service;

import mapper.DeleteMapper;

public class DeleteServiceImpl {

	public void delete(int num) {
		DeleteMapper mapper = new DeleteMapper();
		mapper.delete(num);
	}

	
}
