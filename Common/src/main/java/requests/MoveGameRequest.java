package requests;


import move.Move;
import responses.Action;
import user.User;

public class MoveGameRequest implements Action {
	Move movePlayer = new Move();

	public MoveGameRequest(int rowIndex, int columnIndex ) {
		movePlayer.setMoveElements(rowIndex, columnIndex);
	}
	
	public Move getMove() {
		return movePlayer;
	}
	
}
