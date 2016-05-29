package server.game;

import java.awt.Label;

public class CheckWinGame {
	private String[][] madeMoves;
	private int xLastMove;
	private int yLastMove;
	private String valueLastMove;
	
	public CheckWinGame(String[][] madeMoves) {
		this.madeMoves = madeMoves;
	}

    public boolean getGameisWin(int xLastMove, int yLastMove){
    	boolean isGameWin = false;
    	valueLastMove = madeMoves[xLastMove][yLastMove];
    	this.xLastMove = xLastMove;
    	this.yLastMove = yLastMove;
    	
    	
    	// check on vertical
    	
    	
    	//
    	isGameWin = verticalCheck();
    	if(!isGameWin){
    		
    	}
    	//
    	
    	
    	
    	/*if(verticalCheck()){
        	isGameWin = verticalCheck();
        } /*if (!isGameWin){
        	
        }*/
    	
    	System.out.println("In GAME_Check: gameIsWin? - " + isGameWin);
    	return isGameWin;
    }
    
    private boolean verticalCheck() {
		int amountEqualse = 0;
		for (int i = 0; i < 3; i++) {
			String valueCurrentIteration = madeMoves[i][yLastMove];
			if (valueCurrentIteration.equals(valueLastMove)) {
				amountEqualse++;
			}
		}
		return amountEqualse == 3;
	}

/*
		for (int increase = -2; increase <= 2; increase++) {
			int increaseValue = xLastMove + increase;
			if ((increaseValue >= 0) && (increaseValue <= 2) && (increaseValue != xLastMove)) {
				String valueMoveNextCall = madeMoves[increaseValue][yLastMove];
				if ((valueMoveNextCall != null) && (valueMoveNextCall.equals(valueLastMove))) {
					amountEqualse++;
				}
			}
		}
		//
		System.out.println("amount Equals: " + amountEqualse);
		//
		return amountEqualse == 2;
	}
*/

    	
    	
}
