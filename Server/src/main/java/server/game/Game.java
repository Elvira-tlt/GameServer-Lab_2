package server.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import move.Move;
import server.ClientConnector;
import user.User;

public class Game {
	private Map<User, ClientConnector> players2Connectors = new HashMap<>();
	private List<User> playersThisGame = new ArrayList<User>();
	
	User currentPlayer;
    User otherPlayer; 
    
    public Game(ClientConnector clientConnector1, ClientConnector clientConnector2){
    	setClientConnectorsPlayers(clientConnector1, clientConnector2);
    }
    
    private void setClientConnectorsPlayers(ClientConnector clientConnector1, ClientConnector clientConnector2){
        User player1 = clientConnector1.getConnectedUser();
        User player2 = clientConnector2.getConnectedUser();

        players2Connectors.put(player1, clientConnector1);
        players2Connectors.put(player2, clientConnector2);

        playersThisGame.add(player1);
        playersThisGame.add(player2);
    }
    
  //обработчик действий игроков:
    public void moveMade(Move movePlayer){
        currentPlayer  = movePlayer.getPlayerMakeAMove();
        otherPlayer = getOtherPlayer(currentPlayer);
        
        
        
        
        checkGame();
    }

    
    
    private void checkGame(){
    	//TODO
    	//проверка, есть ли выигравший
    }

    private User getOtherPlayer(User currentPlayer){
        for(User player: playersThisGame ){
            if (player != currentPlayer) {
                otherPlayer = player;
                break;
            }
        }
        return otherPlayer;
    }
    
    
    
    
}
