package client.view;

import javax.swing.*;
import java.awt.*;

public class GamePlayerPanel extends JPanel {
	private final int WIDTH_PANEL = 232;
	private final int HEIGHT_PANEL = 200;
	private final Dimension PANEL_SIZE = new Dimension(WIDTH_PANEL,HEIGHT_PANEL);
	private String whoDisplayed;
	private String namePlayers ;
	private String typeTeamPlayer;
	private JLabel whoDisplayedLabel = new JLabel(whoDisplayed);
	private JLabel playersNameLabel = new JLabel(namePlayers);
	private JLabel typeTeamLabel = new JLabel(typeTeamPlayer);


	public GamePlayerPanel() {
		setPreferredSize(PANEL_SIZE);
		setMinimumSize(PANEL_SIZE);
		setMaximumSize(PANEL_SIZE);
		setLayout(new GridLayout(3, 1));
	}

	public void setElementsToPanel(TypePlayers typePlayers, String namePlayers, TypeTeam typeTeam) {
		if(typePlayers == TypePlayers.YOU) {
			whoDisplayed = "ВЫ";
		} else {
			whoDisplayed = "ВАШ СОПЕРНИК";
		}

		this.namePlayers = namePlayers;

		if (typeTeam == TypeTeam.CROSS) {
			typeTeamPlayer = "X";
		} else {
			typeTeamPlayer = "0";
		}
	}

	public void display() {
		add(whoDisplayedLabel);
		add(playersNameLabel);
		add(typeTeamLabel);
	}

}
