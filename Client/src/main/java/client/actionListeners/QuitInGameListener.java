package client.actionListeners;

import client.ServerConnector;
import client.view.PanelOnlineUsers;
import client.view.TableModelOnlineUsers;
import requests.ExitFromGameClientRequest;
import requests.StartGameRequest;
import user.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;


public class QuitInGameListener implements ActionListener {
	private ServerConnector serverConnector;

	@Override
    public void actionPerformed(ActionEvent e) {
		ExitFromGameClientRequest exitFromGameRequest = new ExitFromGameClientRequest();
		serverConnector.sendToClientConnector(exitFromGameRequest);
    }

    public void setServerConnector(ServerConnector serverConnector){
    	this.serverConnector = serverConnector;
    }
    
}
