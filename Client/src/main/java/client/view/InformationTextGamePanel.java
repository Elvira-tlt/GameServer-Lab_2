package client.view;

import move.TypeValueCurrentStateGame;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class InformationTextGamePanel extends JPanel{
	private JLabel gameText = new JLabel();
	private final String INFORMATION_TEXT_STROKE = "Ход игрока ";
	private final String INFORMATION_RESULT_GAME = "<html> Игра окончена.<br> Победил игрок: ";
	private final int WIDHT = 275;
	private final int HEIGHT =200;
	private Dimension size = new Dimension(WIDHT, HEIGHT);
	private Dimension sizeText = new Dimension(WIDHT, HEIGHT);
	
	
	public InformationTextGamePanel() {
		gameText.setPreferredSize(sizeText);
		gameText.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		setPreferredSize(size);
		setBorder(new LineBorder(Color.red));
		
		add(gameText);
	}
	
	public void changeInformationText (TypeValueCurrentStateGame typeInformationTextGame, String nameCurrentUser) {
		StringBuffer stringBufferInformationText = new StringBuffer();

		if (typeInformationTextGame == TypeValueCurrentStateGame.CHANGE_CURRENT_PLAYER) {
			stringBufferInformationText.append(INFORMATION_TEXT_STROKE);
		} else  {
			stringBufferInformationText.append(INFORMATION_RESULT_GAME);
		}
		stringBufferInformationText.append(nameCurrentUser);
		String textInformationPanel = stringBufferInformationText.toString();
		gameText.setText(textInformationPanel);
	}
}
