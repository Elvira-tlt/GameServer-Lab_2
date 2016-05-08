package client.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
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
	private GamePlayerPanel OtherPlayersPanel = new GamePlayerPanel();



	private final Dimension SIZE_PLAYERS_PANEL = new Dimension(550, 200);

	public GamePanel(){
		configurePanel();

		/////
		display();
		//
	}

	public void display(){
		prepareElements();
		tableModelGame.setMadeMoves(madeMoves);
	}



	public void setMadeMovesToTable(String[][] madeMoves){
		tableModelGame.setMadeMoves(madeMoves);
		updateTableModel();
	}

	private void updateTableModel() {
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
		playersPanel.setMinimumSize(SIZE_PLAYERS_PANEL);
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

		/////
		int widht = 175; //232
		int height =200; //200
		Dimension size = new Dimension(widht, height);

		JPanel panelGamer = new JPanel();
		panelGamer.setPreferredSize(size);
		panelGamer.setBorder(new LineBorder(Color.red));

		JPanel panelGamer1 = new JPanel();
		panelGamer1.setPreferredSize(size);
		panelGamer1.setBorder(new LineBorder(Color.red));

		JPanel panelGamer2 = new JPanel();
		panelGamer2.setPreferredSize(size);
		panelGamer2.setBorder(new LineBorder(Color.red));

		playersPanel.add(panelGamer);
		playersPanel.add(panelGamer1);
		playersPanel.add(panelGamer2);
		////


		add(playersPanel, BorderLayout.WEST);
		add(tableGameMoved, BorderLayout.CENTER);

	}
}
