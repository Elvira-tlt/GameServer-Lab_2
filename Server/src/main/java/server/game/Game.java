package server.game;

import java.util.*;

import move.Move;
import move.TypeValueCurrentStateGame;
import responses.EndedGameResponse;
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
    private boolean isGameWin;

    private GameRepository gameRepository;

    public Game(ClientConnector clientConnector1, ClientConnector clientConnector2){
    	setTypeTeam2String();
    	setClientConnectorsPlayers(clientConnector1, clientConnector2);
        setTypeTeamToPlayers();
        setFirstMadeMovePlayer();
    }

    //обработчик действий игроков:
    public void moveMade(Move movePlayer, User playerMadeAMove){

        if(playerMadeAMove.equals(currentPlayer)){
            otherPlayer = getOtherPlayer(currentPlayer);

            int xMove = movePlayer.getRowIndex();
            int yMove = movePlayer.getColumnIndex();

            TypeTeam typeTeamCurrentPlayer = players2TypeTeam.get(currentPlayer);
            String valuePlayerTeam = typeTeam2DisplayedString.get(typeTeamCurrentPlayer);

            if (checkMayMadeMoveToHere(xMove, yMove)){
                madeMoves[xMove][yMove] = valuePlayerTeam;

                boolean isGameEnd = checkGame(xMove, yMove);
                sendResponseForPlayers(isGameEnd, currentPlayer, otherPlayer);

                //change current player:
                currentPlayer = otherPlayer;
            }
        }
    }

    public Map<User, TypeTeam> getPlayers2TypeTeam() {
        return players2TypeTeam;
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

        //
        System.out.println("IN GAME Type player1: " + player1 + typeTeamPlayer1);
        System.out.println("IN GAME Type player2: " + player2 + typeTeamPlayer2);
        //


    }

    private boolean checkGame(int xMove, int yMove){
        boolean isGameEnd;
    	CheckWinGame checkWinGame = new CheckWinGame(madeMoves);
    	isGameWin = checkWinGame.getGameisWin(xMove, yMove);
		if(isGameWin){
            endedThisGame();
            isGameEnd = true;
        } else {
			isGameEnd = getCheckEndedGame();
		}
        return  isGameEnd;
    }

    private boolean getCheckEndedGame(){
    	boolean gameIsEnd = true;
    	for(String[] valueArrayCell: madeMoves){
    		for(String valueCell: valueArrayCell){
    			if(valueCell == null){
    				gameIsEnd = false;
    				break;
    			}
    		}
    	}
    	return gameIsEnd;
    }

    private boolean checkMayMadeMoveToHere(int xMove, int yMove){
    	String valueHere = madeMoves[xMove][yMove];
    	boolean mayMakeMove = valueHere == null;
    	return mayMakeMove;
    }

    public User getOtherPlayer(User currentPlayer){
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
    	typeTeam2DisplayedString.put(TypeTeam.NOUGHT, "O");
    }

    private void sendResponseForPlayers(boolean isGameEnd, User playerCurrent, User playerOther){
    	ClientConnector clientConnectorPlayer1 = players2Connectors.get(playerCurrent);
    	ClientConnector clientConnectorPlayer2 = players2Connectors.get(playerOther);
    	MoveGameResponse moveGameResponse;
    	EndedGameResponse endedGameResponse;
    	
        String nameNextCurrentUser = playerOther.getNameUser();
        moveGameResponse = new MoveGameResponse(madeMoves);
        moveGameResponse.setNameCurrentUser(nameNextCurrentUser);
        	
        clientConnectorPlayer1.sendAction(moveGameResponse);
        clientConnectorPlayer2.sendAction(moveGameResponse);
    		
        if (isGameEnd){
             endedGameResponse = new EndedGameResponse(TypeValueCurrentStateGame.GAME_ENDED);
             if(isGameWin){
                 endedGameResponse.setWinUser(getNamePlayerCurrentStroke());
             }
             clientConnectorPlayer1.sendAction(endedGameResponse);
             clientConnectorPlayer2.sendAction(endedGameResponse);
         }
    }
    
    private void setFirstMadeMovePlayer(){
        Set<User> playersThisGame = players2TypeTeam.keySet();
        for (User player :playersThisGame) {
            TypeTeam typeTeamThisUser = players2TypeTeam.get(player);
            if(typeTeamThisUser.equals(TypeTeam.CROSS)) {
                currentPlayer = player;
                break;
            }
        }
    }
    private User otherPlayer;

    public String getNamePlayerCurrentStroke() {
        String namePlayerCurrentStroke = currentPlayer.getNameUser();
        return namePlayerCurrentStroke;
    }

    public void setGameRepository(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    private void endedThisGame(){
        gameRepository.removeGame(this);
    }
}
