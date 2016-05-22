package responses;



public class MoveGameResponse implements Action {
	private String[][] madeMoves;

	private String nameCurrentUser;

	public MoveGameResponse(String[][] madeMoves) {
		String[][] madeMovesNew = new String[3][3];
		for(int i=0; i<3; i++) {
			for (int j = 0; j < 3; j++) {
				madeMovesNew[i][j] = madeMoves[i][j];
			}
		}
		this.madeMoves = madeMovesNew;
	}

	public void setNameCurrentUser(String nameCurrentUser){
		this.nameCurrentUser = nameCurrentUser;
	}

	public String[][] getMadeMove(){
		return madeMoves;
	}

	@Override
	public String toString() {
		return "MoveGameResponse{" +
				"madeMoves=" + PRINT_Array(madeMoves) +
				'}';
	}

	private String PRINT_Array(String[][] arrayForPrint){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				sb.append(arrayForPrint[i][j] + ' ');
			}
		}
		sb.append("]");
		return sb.toString();
	}

	public String getNameCurrentUser() {
		return nameCurrentUser;
	}
}
