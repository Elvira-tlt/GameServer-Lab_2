package server.game;

import java.util.*;

import move.Move;
import responses.MoveGameResponse;
import server.ClientConnector;
import typeTeam.TypeTeam;
import user.User;

public class Game {
	private Map<User, ClientConnector> players2Connectors = new HashMap<>();
    private Map<User, TypeTeam> players2TypeTeam = new HashMap<>();
    private Map<TypeTeam, String> typeTeam2DisplayedString = new HashMap<>();
	private List<User> playersThisGame = new ArrayList<User>();
	private String[][] madeMoves  = new String[3][3];

	private User player1;
    private User player2;
    
    private User currentPlayer;
    private User otherPlayer;

    public Game(ClientConnector clientConnector1, ClientConnector clientConnector2){
    	setTypeTeam2String();
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
    public void moveMade(Move movePlayer, User playerMadeAMove){
        currentPlayer  = playerMadeAMove;
        otherPlayer = getOtherPlayer(currentPlayer);

        int xMove = movePlayer.getRowIndex();
        int yMove = movePlayer.getColumnIndex();
        
        TypeTeam typeTeamCurrentPlayer = players2TypeTeam.get(currentPlayer);
        String valuePlayerTeam = typeTeam2DisplayedString.get(typeTeamCurrentPlayer);
        
        setMadeMoveToArray(xMove, yMove, valuePlayerTeam);
        

    	//!!!!!!!!!!!!!!    	
        checkGame();
        
        sendResponseForPlayers(/*madeMoves, */currentPlayer, otherPlayer);
        
        //
        PRINT_Array(madeMoves);
        System.out.println("");
        //
        
        
        
        
    }
    
  /////
    private void PRINT_Array(String[][] arrayForPrint){
    	System.out.print("[");
    	for(int i=0; i<3; i++){
        	for(int y=0; y<3; y++){
        		System.out.print(arrayForPrint[i][y]);
        	}
        }
    	System.out.print("]");
    }
    /////
    
    private void setMadeMoveToArray(int xMove, int yMove, String settingValue){
    	madeMoves[xMove][yMove] = settingValue;
    }

    private void checkGame(){
    	//TODO
    	//проверка, есть ли выигравший
    }

   
    
    
    public Map<User, TypeTeam> getPlayers2TypeTeam() {
        return players2TypeTeam;
    }

    private User getOtherPlayer(User currentPlayer){
        for(User player: playersThisGame ){
            if (player != currentPlayer) {
            	otherPlayer = player;
                return otherPlayer;
            }
        }
        return otherPlayer;
    }

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
    
    private void setTypeTeam2String(){
    	typeTeam2DisplayedString.put(TypeTeam.CROSS, "X");
    	typeTeam2DisplayedString.put(TypeTeam.NOUGHT, "0");
    }
    
    private void sendResponseForPlayers(/*String[][] madeMovesNew, */User player1, User player2){
    	MoveGameResponse moveGameResponse = new MoveGameResponse(madeMoves);
    	ClientConnector clientConnectorPlayer1 = players2Connectors.get(player1);
    	ClientConnector clientConnectorPlayer2 = players2Connectors.get(player2);
    	clientConnectorPlayer1.sendAction(moveGameResponse);
    	clientConnectorPlayer2.sendAction(moveGameResponse);
    }




}
