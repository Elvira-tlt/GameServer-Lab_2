package client.serverActionsHandlers;

import requests.ServerActionHandler;
import responses.ExitServerResponse;


public class ExitResponseHandler implements ServerActionHandler<ExitServerResponse> {

    @Override
    public void handle(ExitServerResponse exitResponse) {
        System.out.println("ExitResponseHandlerClient");
    }

}
