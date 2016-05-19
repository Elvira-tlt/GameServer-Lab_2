package server.game;

import java.util.*;

import move.Move;
import server.ClientConnector;
import typeTeam.TypeTeam;
import user.User;

public class Game {
	private Map<User, ClientConnector> players2Connectors = new HashMap<>();

    private Map<User, TypeTeam> players2TypeTeam = new HashMap<>();

	private List<User> playersThisGame = new ArrayList<User>();

	private User player1;
    private User player2;

    public Game(ClientConnector clientConnector1, ClientConnector clientConnector2){
    	setClientConnectorsPlayers(clientConnector1, clientConnector2);
        setTypeTeamToPlayers();
    }

    private void setClientConnectorsPlayers(ClientConnector clientConnector1, ClientConnector clientConnector2){
        player1 = clientConnector1.getConnectedUser();
        player2 = clientConnector2.getConnectedUser();

        players2Connectors.put(player1, clientConnector1);
        players2Connectors.put(player2, clientConnector2);

        playersThisGame.add(player1);
        playersThisGame.add(player2);
    }

    private void setTypeTeamToPlayers(){
        TypeTeam typeTeamPlayer1 = randomSelectedTeam();
        TypeTeam typeTeamPlayer2 = getTypeTeamNotThis(typeTeamPlayer1);

        players2TypeTeam.put(player1, typeTeamPlayer1);
        players2TypeTeam.put(player2, typeTeamPlayer2);
    }



  //обработчик действий игроков:
    public void moveMade(Move movePlayer){
       User currentPlayer  = movePlayer.getPlayerMakeAMove();
      // User otherPlayer = getOtherPlayer(currentPlayer);


        checkGame();
    }

    private void checkGame(){
    	//TODO
    	//проверка, есть ли выигравший
    }

    public Map<User, TypeTeam> getPlayers2TypeTeam() {
        return players2TypeTeam;
    }

   /* private User getOtherPlayer(User currentPlayer){
    	User otherPlayer;
        for(User player: playersThisGame ){
            if (player != currentPlayer) {
            	otherPlayer = player;
                return otherPlayer;
            }
        }
        return otherPlayer;
    }*/

    private TypeTeam randomSelectedTeam(){
        Random random = new Random();
        int randomSelectedNumber = random.nextInt(2);
        if (randomSelectedNumber == 1) {
            return TypeTeam.CROSS;
        }
        return TypeTeam.NOUGHT;
    }

    private TypeTeam getTypeTeamNotThis(TypeTeam typeTeam){
        if(typeTeam == TypeTeam.CROSS) {
            return TypeTeam.NOUGHT;
        }
        return TypeTeam.CROSS;
    }




}
