package client.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class PanelIdentifiedUser extends JPanel {
	private final Dimension MINIMUM_PANEL_SIZE = new Dimension(40, 550);
	private final Dimension MAXIMUM_PANEL_SIZE = new Dimension(60, 650);
	
	private static final String TEXT_LABEL = "Вы зарегистрировались как		";
	private static final String TEXT_BUTTON = "Сменить пользователя";
	private JLabel textLabel = new JLabel(TEXT_LABEL);
	private JLabel userNameLabel = new JLabel();
	private JButton disconnected = new JButton(TEXT_BUTTON);
	
	private String nameIdentifiedUser;
	
	public PanelIdentifiedUser(){
		setLayout(new GridLayout(1,3));
		setBorder(new LineBorder(Color.red));
		setVisible(true);
		setMaximumSize(MAXIMUM_PANEL_SIZE);
		setMinimumSize(MINIMUM_PANEL_SIZE);
	}
	
	private void setElements(){
		add(textLabel);
		userNameLabel.setText(nameIdentifiedUser);
		add(userNameLabel);
		add(disconnected);
		
	}
	
	public void display(){
		setElements();
	}
	
	public void setNameIdentifiedUser(String nameIdentifiedUser) {
		this.nameIdentifiedUser = nameIdentifiedUser;
	}
}
