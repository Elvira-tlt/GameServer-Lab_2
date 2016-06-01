package client.view;

import move.TypeValueCurrentStateGame;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InformationTextGamePanel extends JPanel{
	private JLabel gameText = new JLabel();
	private final static String INFORMATION_TEXT_STROKE = "Ход игрока ";
	private final static String INFORMATION_RESULT_WIN_GAME = "<html> Игра окончена.<br> Победил игрок: ";
	private final static String INFORMATION_RESULT_STANDOFF_GAME = "<html> Игра окончена.<br> НИЧЬЯ ";
	private static final int WIDHT = 275;
	private static final int HEIGHT =200;
	private Dimension size = new Dimension(WIDHT, HEIGHT);
	private Dimension sizeText = new Dimension(WIDHT, HEIGHT);
	
	
	public InformationTextGamePanel() {
		gameText.setPreferredSize(sizeText);
		gameText.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		setPreferredSize(size);
		add(gameText);
	}
	
	public void changeInformationText (TypeValueCurrentStateGame typeInformationTextGame, String nameCurrentUser) {
		StringBuffer stringBufferInformationText = new StringBuffer();
		boolean nameCurrentUserIsNotNull = nameCurrentUser != null;

		if (typeInformationTextGame == TypeValueCurrentStateGame.CHANGE_CURRENT_PLAYER) {
			stringBufferInformationText.append(INFORMATION_TEXT_STROKE);
		} else  {
			if(nameCurrentUserIsNotNull) {
				stringBufferInformationText.append(INFORMATION_RESULT_WIN_GAME);
			} else {
				stringBufferInformationText.append(INFORMATION_RESULT_STANDOFF_GAME);
			}
		}
		if(nameCurrentUserIsNotNull) {
			stringBufferInformationText.append(nameCurrentUser);
		}
		String textInformationPanel = stringBufferInformationText.toString();
		gameText.setText(textInformationPanel);
	}
}
