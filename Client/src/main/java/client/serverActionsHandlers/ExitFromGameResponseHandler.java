package client.serverActionsHandlers;

import requests.ServerActionHandler;
import responses.ExitFromGameServerResponse;



public class ExitFromGameResponseHandler implements ServerActionHandler<ExitFromGameServerResponse> {

    @Override
    public void handle(ExitFromGameServerResponse exitFromGameResponse) {
        System.out.println("ExitFromGameResponseHandlerClient");
    }

}
