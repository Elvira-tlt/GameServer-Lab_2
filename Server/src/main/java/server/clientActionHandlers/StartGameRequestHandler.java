package server.clientActionHandlers;


import requests.StartGameRequest;
import responses.StartGameResponse;
import server.ClientActionHandler;
import server.ClientConnector;
import server.ConnectedUsers;
import server.game.Game;
import server.game.GameRepository;
import user.User;

public class StartGameRequestHandler implements ClientActionHandler<StartGameRequest> {
    private ConnectedUsers connectedUsers;

    private User player1;
    private User player2;
    private Game gameToThisPlayers;
    private GameRepository gameRepository;
    private boolean players2IsSelected  = player2 != null;
    @Override
    public void handle(StartGameRequest startGameRequest, ClientConnector clientConnector) {
        player1 = clientConnector.getConnectedUser();
        player2 = startGameRequest.getOtherPlayer();


        if(players2IsSelected){
            //TODO
        } else {
            //если игрок не выбран, то применить метод рандомного определения игрока
        	//player2 = connectedUsers.getRundomSelectedOnlineUser(player1);
        }

        ClientConnector clientConnectorPlayer1 = clientConnector;
        ClientConnector clientConnectorPlayer2 = connectedUsers.getClientConnectorByUser(player2);
        
        gameToThisPlayers = new Game(clientConnectorPlayer1, clientConnectorPlayer2);
        sendStartGameResponse(clientConnectorPlayer1, clientConnectorPlayer2);
        gameRepository.setGame(gameToThisPlayers);
    }

    public void setConnectedUsers(ConnectedUsers connectedUsers) {
        this.connectedUsers = connectedUsers;
    }

	public void setGameRepository(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	private void sendStartGameResponse(ClientConnector clientConnector1, ClientConnector clientConnector2){
        String player1Name = player1.getNameUser();
        String player2Name = player2.getNameUser();
		StartGameResponse startGameResponse1 = new StartGameResponse(player1Name, player2Name);
		StartGameResponse startGameResponse2 = new StartGameResponse(player2Name, player1Name);
        clientConnector1.sendAction(startGameResponse1);
        clientConnector2.sendAction(startGameResponse2);
	}

}
