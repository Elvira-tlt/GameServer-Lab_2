package requests;


import move.Move;
import responses.Action;
import user.User;

public class MoveGameRequest implements Action {
	Move movePlayer = new Move();

	public MoveGameRequest(User player, int rowIndex, int columnIndex ) {
		movePlayer.setMoveElements(player, rowIndex, columnIndex);
	}
	
	public Move getMove() {
		return movePlayer;
	}
	
}
