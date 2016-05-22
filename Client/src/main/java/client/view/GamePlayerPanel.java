package client.view;

import javax.swing.*;

import typeTeam.TypeTeam;

import java.awt.*;
import java.util.Random;

public class GamePlayerPanel extends JPanel {
	private final int WIDTH_PANEL = 160; //232
	private final int HEIGHT_PANEL = 200;
	private final Dimension PANEL_SIZE = new Dimension(WIDTH_PANEL,HEIGHT_PANEL);
	private String whoDisplayed;
	private String namePlayer ;
	private String typeTeamPlayer;
	//private ImageIcon typeTeamIcon;
	private JLabel whoDisplayedLabel;
	private JLabel playersNameLabel;
	private JLabel typeTeamLabel;
	
	public GamePlayerPanel() {
		setPreferredSize(PANEL_SIZE);
		setMinimumSize(PANEL_SIZE);
		setMaximumSize(PANEL_SIZE);
		setLayout(new GridLayout(3, 1));
	}


	public void setElementsToPanel(TypePlayers typePlayers, String namePlayers) {
		if(typePlayers == TypePlayers.YOU) {
			whoDisplayed = "ВЫ";
		} else {
			whoDisplayed = "ВАШ СОПЕРНИК";
		}
		this.namePlayer = namePlayers;
	}

	public void setTypeTeam(TypeTeam typeTeamThisPlayer) {
		if (typeTeamThisPlayer == TypeTeam.CROSS) {
			typeTeamPlayer = "X";
		} else {
			typeTeamPlayer = "0";
		}
	}
	
	public void display() {
		prepareElements();
		
		add(whoDisplayedLabel, BorderLayout.CENTER);
		add(playersNameLabel);
		add(typeTeamLabel);
		setBackground(Color.CYAN);
		
	}
	
	public void prepareElements(){
		whoDisplayedLabel = new JLabel(whoDisplayed);
		playersNameLabel = new JLabel(namePlayer);
		typeTeamLabel = new JLabel(typeTeamPlayer);
		
		whoDisplayedLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		typeTeamLabel.setFont(new Font("Times New Roman", Font.ITALIC, 55));
	}
}