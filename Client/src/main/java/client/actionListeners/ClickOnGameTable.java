package client.actionListeners;

import client.ServerConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import requests.MoveGameRequest;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public class ClickOnGameTable extends MouseAdapter{
	private static final Logger LOG = LoggerFactory.getLogger(ClickOnGameTable.class);
	private JTable tableGame;
	private ServerConnector serverConnector;
	
	public ClickOnGameTable(JTable tableGame){
		this.tableGame = tableGame;
	}
	
	public void mouseClicked(MouseEvent e) {
		int rowClicked = tableGame.getSelectedRow();
		int columnClicked = tableGame.getSelectedColumn();
		LOG.debug("Clicked: {} {}", rowClicked, columnClicked);
		sendMadeMove(rowClicked, columnClicked);
	}

	private void sendMadeMove(int row, int column){
		MoveGameRequest moveGameRequest = new MoveGameRequest(row, column);
		serverConnector.sendToClientConnector(moveGameRequest);
	}


	public void setServerConnector(ServerConnector serverConnector){
		this.serverConnector = serverConnector;
	}
}
