package server.game;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CheckWinGameTest {

    @Test
    public void testVerticalCheck(){
        String[][] checkingArray = new String[][]{
                { "x", "o", "o"} ,
                { "x", "o", "o"} ,
                { "x", null, null}
        };
        CheckWinGame checkWinGame = new CheckWinGame(checkingArray);
        boolean gameisWinInVerticalCheck = checkWinGame.getGameisWin(1,0);
        Assert.assertEquals(gameisWinInVerticalCheck, true);
    }

    @Test
    public void testVerticalCheckNegative (){
        String[][] checkingArray = new String[][]{
                { "x", null, "o"} ,
                { "x", null, "o"} ,
                { null, null, null}
        };
        CheckWinGame checkWinGame = new CheckWinGame(checkingArray);
        boolean gameisWinInVerticalCheck = checkWinGame.getGameisWin(1,0);
        Assert.assertEquals(gameisWinInVerticalCheck, false);
    }
}