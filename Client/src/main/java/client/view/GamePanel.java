package client.view;

import javax.print.attribute.standard.MediaSize.Other;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import client.actionListeners.ClickOnGameTable;
import typeTeam.TypeTeam;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Iterator;
import java.util.Set;


public class GamePanel extends JPanel {
	private TableModelGame tableModelGame = new TableModelGame();
	private JTable tableGameMoved = new JTable(tableModelGame);
	private final String[][] STARTED_VALUE_MADE_MOVES = new String[][] {
			new String[] {" ", " ", " "},
			new String[] {" ", " ", " "},
			new String[] {" ", " ", " "}
	};
	private String[][] madeMoves = STARTED_VALUE_MADE_MOVES;
	private JPanel playersPanel = new JPanel();
	private GamePlayerPanel thisPlayersPanel = new GamePlayerPanel();
	private GamePlayerPanel rivalPlayersPanel = new GamePlayerPanel();
	private InformationTextGamePanel informationTextGamePanel = new InformationTextGamePanel();
	//private String thisPlayerName;
	//private String rivalName;

	private final Dimension SIZE_PLAYERS_PANEL = new Dimension(620, 250);

	public void display(){
		configurePanel();
		prepareElements();
		tableModelGame.setMadeMoves(madeMoves);
	}
	
	public void setMadeMovesToTable(String[][] madeMoves){
		tableModelGame.setMadeMoves(madeMoves);
		updateTableModelGame();
		//updateTextInformationGamePanel();
	}

	private void updateTableModelGame() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Set<TableModelListener> listenersTableModel = tableModelGame.getListeners();
				Iterator<TableModelListener> listenersIterator = listenersTableModel.iterator();
				while (listenersIterator.hasNext()) {
					TableModelListener listenerElem = listenersIterator.next();
					listenerElem.tableChanged(new TableModelEvent(tableModelGame));
				}
			}
		});
	}

	private void configurePanel() {
		playersPanel.setBorder(new LineBorder(Color.green));
		playersPanel.setMaximumSize(SIZE_PLAYERS_PANEL);
		playersPanel.setPreferredSize(SIZE_PLAYERS_PANEL);
		playersPanel.setMinimumSize(SIZE_PLAYERS_PANEL);

		//выделять по одной ячейке, а не всю строку сразу
		tableGameMoved.setColumnSelectionAllowed(true);
		//цвет выделенной ячейки
		tableGameMoved.setSelectionBackground(Color.yellow);

		tableGameMoved.setRowHeight(100);
		for (int i=0; i< tableModelGame.getColumnCount(); i++)  {
			tableGameMoved.getColumnModel().getColumn(i).setPreferredWidth(100);
			tableGameMoved.getColumnModel().getColumn(i).setMaxWidth(100);
			tableGameMoved.getColumnModel().getColumn(i).setMinWidth(100);
		}
	}

	private void prepareElements() {
		thisPlayersPanel.display();
		rivalPlayersPanel.display();
		
		playersPanel.add(thisPlayersPanel);
		playersPanel.add(informationTextGamePanel);
		playersPanel.add(rivalPlayersPanel);

		add(playersPanel, BorderLayout.WEST);
		add(tableGameMoved, BorderLayout.CENTER);
	}

	public void setTypeTeamToPlayers(TypeTeam typeTeamThisPlayer, TypeTeam typeTeamRival){
		thisPlayersPanel.setTypeTeam(typeTeamThisPlayer);
		rivalPlayersPanel.setTypeTeam(typeTeamRival);
	}
	
	public void setNameToPlayers(String thisPlayerName, String rivalName){
		prepareGamePlayersPanel(thisPlayerName, rivalName);
	}
	
	private void prepareGamePlayersPanel(String thisPlayerName, String rivalName){
		thisPlayersPanel.setElementsToPanel(TypePlayers.YOU, thisPlayerName);
		rivalPlayersPanel.setElementsToPanel(TypePlayers.RIVAL, rivalName);
	}
	
	public void changeTextInformationGamePanel(String newText){
		informationTextGamePanel.changeText(newText); 
	}
	
	private void addActionListenersToTable(MouseAdapter mouseAdapter){
		//tableModelGame.addTableModelListener(actionListener);
		tableGameMoved.addMouseListener(new ClickOnGameTable(tableGameMoved));
		
		/*
		
		*/
	}
	
	//http://www.skipy.ru/technics/layouts.html
}
