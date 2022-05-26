package domain;

import java.util.Collection;

public class BoardInfo {

	private Collection<BoardVO> list;
	private int totalRow;
	
	public Collection<BoardVO> getList() {
		return list;
	}
	public void setList(Collection<BoardVO> list) {
		this.list = list;
	}
	public int getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}
}
