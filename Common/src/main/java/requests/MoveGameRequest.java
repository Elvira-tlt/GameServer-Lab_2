package requests;


import responses.Action;
import user.User;

public class MoveGameRequest implements Action {
	private User player;
	private int rowIndex;
	private int columnIndex;
	
	public MoveGameRequest(User player, int rowIndex, int columnIndex ) {
		this.player = player;
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}
	
	public User getPlayerMakeAMove(){
		return player;
	}
	
	public int getRowIndex() {
		return rowIndex;
	}

	public int getColumnIndex() {
		return columnIndex;
	}
	
}
