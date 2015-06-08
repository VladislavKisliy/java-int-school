package com.weigandtconsulting.javaschool.dmitry;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingUtilities;


public class Gui extends JFrame {

	private JPanel contentPane;
	private Referee referee;
	private List<CellState> gameField;
	private TicTacToe player1;
	private TicTacToe player2;
	private JButton btnCell0;
	private JButton btnCell1;
	private JButton btnCell2;
	private JButton btnCell3;
	private JButton btnCell4;
	private JButton btnCell5;
	private JButton btnCell6;
	private JButton btnCell7;
	private JButton btnCell8;
	private JButton btnReferee;	
	private JLabel lblPlayer1;
	private JLabel lblPlayer2;	
	private final int length=9;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void refreshBattleField(List<CellState> battleField){
		btnCell0.setText(battleField.get(0).toString());
		btnCell1.setText(battleField.get(1).toString());
		btnCell2.setText(battleField.get(2).toString());
		btnCell3.setText(battleField.get(3).toString());
		btnCell4.setText(battleField.get(4).toString());
		btnCell5.setText(battleField.get(5).toString());
		btnCell6.setText(battleField.get(6).toString());
		btnCell7.setText(battleField.get(7).toString());
		btnCell8.setText(battleField.get(8).toString());
		
	}
	
	private void pauseAction(){
		if (referee.playerTurn==1){
			lblPlayer2.setText("Thinking...");
			Thread t= new Thread()  {
	            public void run()  {
	                 SwingUtilities.invokeLater(new Runnable() {
	                        public void run() {
	                        	lblPlayer2.setText("Thinking...");
	                        try{	
	                        	Thread.sleep(1000);
	    					} catch (InterruptedException ex){
	    						return;
	    					}
	                        doAction();
	    					lblPlayer2.setText("Computer");
	                       }
	                   });
	            }
	        };
	        t.start();
		}   
	}
	
	private int doAction(){
		int step;
		int result = 0;
		referee.nextPlayerStep(gameField);
		refreshBattleField(gameField);
		step = referee.checkStep(gameField);
		switch (step){
		case 0: result = 0; break; //Has next step! 
		case -1: JOptionPane.showMessageDialog(null, "It's a draw!"); result = 1; break;
		case -99: JOptionPane.showMessageDialog(null, "Replace this sucker and try again!"); result = 1; break; 
		case 1: JOptionPane.showMessageDialog(null, "Player "+player1.getPlayerName()+" is the winner!"); result = 1; break;
		case 2: JOptionPane.showMessageDialog(null, "Player "+player2.getPlayerName()+" is the winner!"); result = 1; break;
		}	
		return result;
	}
	
	private void initialize(){
		
		setTitle("TicTacToe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 395, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("1dlu"),
				ColumnSpec.decode("60dlu"),
				ColumnSpec.decode("1dlu"),
				ColumnSpec.decode("60dlu"),
				ColumnSpec.decode("1dlu"),
				ColumnSpec.decode("60dlu"),},
			new RowSpec[] {
				RowSpec.decode("1dlu"),
				RowSpec.decode("30dlu"),
				RowSpec.decode("1dlu"),
				RowSpec.decode("60dlu"),
				RowSpec.decode("1dlu"),
				RowSpec.decode("60dlu"),
				RowSpec.decode("1dlu"),
				RowSpec.decode("60dlu"),}));
		btnCell0 = new JButton("");
		btnCell0.setFont(new Font("Tahoma", Font.PLAIN, 50));		
		panel.add(btnCell0, "2, 4, fill, fill");
		btnCell1 = new JButton("");
		btnCell1.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel.add(btnCell1, "4, 4, fill, fill");
		btnCell2 = new JButton("");	
		btnCell2.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel.add(btnCell2, "6, 4, fill, fill");
		btnCell3 = new JButton("");
		btnCell3.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel.add(btnCell3, "2, 6, fill, fill");
		btnCell4 = new JButton("");
		btnCell4.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel.add(btnCell4, "4, 6, fill, fill");	
		btnCell5 = new JButton("");
		btnCell5.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel.add(btnCell5, "6, 6, fill, fill");
		btnCell6 = new JButton("");
		btnCell6.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel.add(btnCell6, "2, 8, fill, fill");		
		btnCell7 = new JButton("");
		btnCell7.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel.add(btnCell7, "4, 8, fill, fill");		
		btnCell8 = new JButton("");
		btnCell8.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel.add(btnCell8, "6, 8, fill, fill");
		btnReferee = new JButton("New game");
		panel.add(btnReferee, "4, 2, fill, fill");
		
		String name;
		name = JOptionPane.showInputDialog(null, "What is your name?", null);
		lblPlayer1 = new JLabel(name);
		panel.add(lblPlayer1, "2, 2, center, center");	
		name = "Computer";
		lblPlayer2 = new JLabel(name);
		panel.add(lblPlayer2, "6, 2, center, center");
		
		//init gamefield		
		gameField = new ArrayList<CellState>();
		for (int i=0; i<length; i++)
			gameField.add(i, CellState.TOE);
		
		//init players and referee
		player1 = new CrissCrossPlayer(CellState.TIC, lblPlayer1.getText());
		player2 = new CrissCrossPlayer(CellState.TAC, lblPlayer2.getText());  
		referee = new Referee(gameField);
		referee.addPlayer(player1);
		referee.addPlayer(player2);	
	}

	/**
	 * Create the frame.
	 */
	public Gui() throws InterruptedException {		
		
		initialize();
		
		btnCell0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameField.set(0, CellState.TIC);
				if (doAction()==0) pauseAction();
			}
		});
		
		btnCell1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameField.set(1, CellState.TIC);
				if (doAction()==0) pauseAction();
			}
		});
		
		btnCell2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameField.set(2, CellState.TIC);
				if (doAction()==0) pauseAction();
			}
		});		
		
		btnCell3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameField.set(3, CellState.TIC);
				if (doAction()==0) pauseAction();
			}
		});		
		
		btnCell4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameField.set(4, CellState.TIC);
				if (doAction()==0) pauseAction();
			}
		});		
		
		btnCell5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameField.set(5, CellState.TIC);
				if (doAction()==0) pauseAction();
			}
		});		
		
		btnCell6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameField.set(6, CellState.TIC);
				if (doAction()==0) pauseAction();
			}
		});		
		
		btnCell7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameField.set(7, CellState.TIC);
				if (doAction()==0) pauseAction();
			}
		});		
		
		btnCell8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameField.set(8, CellState.TIC);
				if (doAction()==0) pauseAction();
			}
		});				
		
		btnReferee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				for (int i=0; i<length; i++)
					gameField.set(i, CellState.TOE);
				referee = new Referee(gameField);
				referee.addPlayer(player1);
				referee.addPlayer(player2);
				if (referee.playerTurn==1){
					pauseAction();
				}			
				refreshBattleField(gameField);
			}
		});

		if (referee.playerTurn==1)
			doAction();		
	}
}
