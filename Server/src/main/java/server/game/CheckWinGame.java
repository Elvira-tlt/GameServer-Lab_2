package server.game;

public class CheckWinGame {
	private String[][] madeMoves;
	private int xLastMove;
	private int yLastMove;
	private String valueLastMove;
	
	public CheckWinGame(String[][] madeMoves) {
		this.madeMoves = madeMoves;
	}

    public boolean getGameisWin(int xLastMove, int yLastMove){
    	boolean isGameWin;
    	valueLastMove = madeMoves[xLastMove][yLastMove];
    	this.xLastMove = xLastMove;
    	this.yLastMove = yLastMove;
    	
    	isGameWin = verticalCheck();
    	if(!isGameWin){
			isGameWin = gorizotalCheck();
			if(!isGameWin){
				isGameWin = diagonalLeft2RightCheck();
				if(!isGameWin){
					isGameWin = diagonalRight2LeftCheck();
					}
				}
			}
		return isGameWin;
	}


    private boolean verticalCheck() {
		int amountEqualse = 0;
		for (int i = 0; i < 3; i++) {
			String valueCurrentIteration = madeMoves[i][yLastMove];
			if ((valueCurrentIteration !=null) && (valueCurrentIteration.equals(valueLastMove))) {
				amountEqualse++;
			}
		}
		return amountEqualse == 3;
	}

	private boolean gorizotalCheck() {
		int amountEqualse = 0;
		for (int i = 0; i < 3; i++) {
			String valueCurrentIteration = madeMoves[xLastMove][i];
			if ((valueCurrentIteration !=null) && (valueCurrentIteration.equals(valueLastMove))) {
				amountEqualse++;
			}
		}
		return amountEqualse == 3;
	}

	private boolean diagonalLeft2RightCheck() {
		int amountEqualse = 0;
		for (int i = 0; i < 3; i++) {
			String valueCurrentIteration = madeMoves[i][i];
			if ((valueCurrentIteration !=null) && (valueCurrentIteration.equals(valueLastMove))) {
				amountEqualse++;
			}
		}
		return amountEqualse == 3;
	}

	private boolean diagonalRight2LeftCheck() {
		int amountEqualse = 0;
			for (int i = 0, j = 2; i < 3; i++, j--) {
				String valueCurrentIteration = madeMoves[i][j];
				if ((valueCurrentIteration !=null) && (valueCurrentIteration.equals(valueLastMove))) {
				amountEqualse++;
			}
		}
		return amountEqualse == 3;
	}
}
