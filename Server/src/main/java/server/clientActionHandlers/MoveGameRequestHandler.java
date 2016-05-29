package server.clientActionHandlers;


import move.Move;
import requests.MoveGameRequest;
import server.ClientActionHandler;
import server.ClientConnector;
import server.game.Game;
import server.game.GameRepository;
import user.User;

public class MoveGameRequestHandler implements ClientActionHandler<MoveGameRequest> {
    private GameRepository gameRepository;

    @Override
    public void handle(MoveGameRequest moveGameRequest, ClientConnector clientConnector) {
    	Move madeMove = moveGameRequest.getMove();
    	
    	User playerMakeAMove = clientConnector.getConnectedUser();
    	Game gameThisPlayer = gameRepository.getGameThisPlayer(playerMakeAMove);
    	if(gameThisPlayer != null){
            gameThisPlayer.moveMade(madeMove, playerMakeAMove);
        }
    }
    
    public void setGameRepository(GameRepository gameRepository){
    	this.gameRepository = gameRepository;
    }

}
