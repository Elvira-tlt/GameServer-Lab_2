package move;


import user.User;

import java.io.Serializable;

public class Move implements Serializable {
	private User player;
	private int rowIndex;
	private int columnIndex;

	public void setMoveElements(int rowIndex, int columnIndex) {
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}
	
	public int getRowIndex() {
		return rowIndex;
	}

	public int getColumnIndex() {
		return columnIndex;
	}
	
}
