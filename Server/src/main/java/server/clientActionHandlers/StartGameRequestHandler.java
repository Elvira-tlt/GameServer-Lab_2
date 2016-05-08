package server.clientActionHandlers;


import requests.MoveGameRequest;
import requests.StartGameRequest;
import server.ClientActionHandler;
import server.ClientConnector;
import server.ConnectedUsers;
import user.User;

public class StartGameRequestHandler implements ClientActionHandler<StartGameRequest> {
    private ConnectedUsers connectedUsers;
    private User player1;
    private User player2;


    @Override
    public void handle(StartGameRequest startGameRequest, ClientConnector clientConnector) {
        player1 = startGameRequest.getThisPlayer();
        player2 = startGameRequest.getOtherPlayer();

        boolean players2IsSelected = player2 != null;

        if(players2IsSelected){
            //TODO
        } else {
            //если игрок не выбран, доприменить метод рандомного определения игрока

        }






    }

}
