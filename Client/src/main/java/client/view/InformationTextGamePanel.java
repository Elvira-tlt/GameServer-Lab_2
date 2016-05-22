package client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class InformationTextGamePanel extends JPanel{
	private JLabel gameText = new JLabel();
	private final int WIDHT = 275;
	private final int HEIGHT =200;
	private Dimension size = new Dimension(WIDHT, HEIGHT);
	private Dimension sizeText = new Dimension(WIDHT, HEIGHT);
	
	
	public InformationTextGamePanel() {
		gameText.setPreferredSize(sizeText);
		setPreferredSize(size);
		setBorder(new LineBorder(Color.red));
		
		add(gameText);
	}
	
	public void changeText(String newText) {
		gameText.setText(newText);
		//invoke.later run()...
	}
}
