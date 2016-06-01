package client.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class PanelIdentifiedUser extends JPanel {
	private final Dimension MINIMUM_PANEL_SIZE = new Dimension(250, 10);
	private final Dimension MAXIMUM_PANEL_SIZE = new Dimension(250, 17);
	
	private static final String TEXT_LABEL = "Вы зарегистрировались как	";
	private JLabel textLabel = new JLabel(TEXT_LABEL);
	private JLabel userNameLabel= new JLabel();
	private static final Font FONT_NAME_LABEL = new Font("Times New Roman", Font.PLAIN, 20);

	public PanelIdentifiedUser(){
		setLayout(new GridLayout(2, 1));
		setMaximumSize(MAXIMUM_PANEL_SIZE);
		setMinimumSize(MINIMUM_PANEL_SIZE);
	}

	private void setElements(){
		userNameLabel.setFont(FONT_NAME_LABEL);
		textLabel.setPreferredSize(MAXIMUM_PANEL_SIZE);
		userNameLabel.setPreferredSize(MAXIMUM_PANEL_SIZE);
		userNameLabel.setForeground(Color.blue);

		add(textLabel);
		add(userNameLabel);
	}

	public void display(){
		setElements();
		setVisible(true);
	}

	public void setNameIdentifiedUser(String nameIdentifiedUser) {
		userNameLabel.setText(nameIdentifiedUser);
	}
}
