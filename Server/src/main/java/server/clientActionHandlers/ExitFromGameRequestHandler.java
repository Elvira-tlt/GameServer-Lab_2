package server.clientActionHandlers;


import move.TypeValueCurrentStateGame;
import requests.ExitFromGameClientRequest;
import responses.EndedGameResponse;
import server.ClientActionHandler;
import server.ClientConnector;
import server.ConnectedUsers;
import server.game.Game;
import server.game.GameRepository;
import user.User;

public class ExitFromGameRequestHandler implements ClientActionHandler<ExitFromGameClientRequest> {
    private GameRepository gameRepository;
    private ConnectedUsers connectedUsers;
    private String nameOtherUserThisGame;

    @Override
    public void handle(ExitFromGameClientRequest exitFromGameRequest, ClientConnector clientConnector) {
        User userToExit = clientConnector.getConnectedUser();
        Game gameThisPlayer = gameRepository.getGameThisPlayer(userToExit);
        User otherPlayer = gameThisPlayer.getOtherPlayer(userToExit);
        nameOtherUserThisGame = otherPlayer.getNameUser();

        ClientConnector clientConnectorOtherUserThisGame = connectedUsers.getClientConnectorByUser(otherPlayer);
        sendEndedGameResponse(clientConnector, clientConnectorOtherUserThisGame);

        changeStatusPlayerInUserToFree(userToExit);
        changeStatusPlayerInUserToFree(otherPlayer);
    }

    private void sendEndedGameResponse(ClientConnector clientConnector1, ClientConnector clientConnector2){
        EndedGameResponse endedGameResponse = new EndedGameResponse(TypeValueCurrentStateGame.GAME_ENDED);
        endedGameResponse.setWinUser(nameOtherUserThisGame);

        clientConnector1.sendAction(endedGameResponse);
        clientConnector2.sendAction(endedGameResponse);
    }

    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    private void changeStatusPlayerInUserToFree(User userToStatusFree){
        connectedUsers.changeStatusPlayerTo(userToStatusFree,false);
    }

    public void setConnectedUsers(ConnectedUsers connectedUsers) {
        this.connectedUsers = connectedUsers;
    }
}
