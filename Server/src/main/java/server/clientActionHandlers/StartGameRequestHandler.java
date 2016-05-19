package server.clientActionHandlers;


import requests.StartGameRequest;
import responses.StartGameResponse;
import server.ClientActionHandler;
import server.ClientConnector;
import server.ConnectedUsers;
import server.game.Game;
import server.game.GameRepository;
import typeTeam.TypeTeam;
import user.User;

import java.util.Map;

public class StartGameRequestHandler implements ClientActionHandler<StartGameRequest> {
    private ConnectedUsers connectedUsers;

    private User player1;
    private User player2;
    private Game gameToThisPlayers;
    private GameRepository gameRepository;
    private Map<User, TypeTeam> players2TypeTeam;
    private StartGameResponse startGameResponse1 = new StartGameResponse();
	private StartGameResponse startGameResponse2 = new StartGameResponse();
    
  
    @Override
    public void handle(StartGameRequest startGameRequest, ClientConnector clientConnector) {
        player1 = clientConnector.getConnectedUser();
        player2 = startGameRequest.getOtherPlayer();

        ClientConnector clientConnectorPlayer1 = clientConnector;
        ClientConnector clientConnectorPlayer2 = connectedUsers.getClientConnectorByUser(player2);
        
        gameToThisPlayers = new Game(clientConnectorPlayer1, clientConnectorPlayer2);
        players2TypeTeam = gameToThisPlayers.getPlayers2TypeTeam();
        sendStartGameResponse(clientConnectorPlayer1, clientConnectorPlayer2);
        gameRepository.setGame(gameToThisPlayers);
    }

	private void sendStartGameResponse(ClientConnector clientConnector1, ClientConnector clientConnector2){
        TypeTeam typeTeamPlayer1 = players2TypeTeam.get(player1);
        TypeTeam typeTeamPlayer2 = players2TypeTeam.get(player2);
        
        String player1Name = player1.getNameUser();
        String player2Name = player2.getNameUser();
        
        startGameResponse1.setTypeTeamAndNameThisPlayer(player1Name, typeTeamPlayer1);
        startGameResponse1.setTypeTeamAndNameOtherPlayer(player2Name, typeTeamPlayer2);
		
        startGameResponse2.setTypeTeamAndNameThisPlayer(player2Name, typeTeamPlayer2);
        startGameResponse2.setTypeTeamAndNameOtherPlayer(player1Name, typeTeamPlayer1);

        clientConnector1.sendAction(startGameResponse1);
        clientConnector2.sendAction(startGameResponse2);
	}
	
	public void setConnectedUsers(ConnectedUsers connectedUsers) {
        this.connectedUsers = connectedUsers;
    }

	public void setGameRepository(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}
}



