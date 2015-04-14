package CrissCross;

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
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btn7;
	private JButton btn8;
	private JButton btn9;
	JLabel lblPlayer2;

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
		btn1.setText(battleField.get(0).toString());
		btn2.setText(battleField.get(1).toString());
		btn3.setText(battleField.get(2).toString());
		btn4.setText(battleField.get(3).toString());
		btn5.setText(battleField.get(4).toString());
		btn6.setText(battleField.get(5).toString());
		btn7.setText(battleField.get(6).toString());
		btn8.setText(battleField.get(7).toString());
		btn9.setText(battleField.get(8).toString());
		
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
		referee.nextPlayerStep(gameField);
		refreshBattleField(gameField);
		step = referee.checkStep(gameField);
		switch (step){
		case 0: /*System.out.println("Has next step!"); */ return 0; 
		case -1: JOptionPane.showMessageDialog(null, "It's a draw!"); return 1;
		case -99: JOptionPane.showMessageDialog(null, "Replace this sucker and try again!"); return 1; 
		case 1: JOptionPane.showMessageDialog(null, "Player "+player1.getPlayerName()+" is the winner!"); return 1;
		case 2: JOptionPane.showMessageDialog(null, "Player "+player2.getPlayerName()+" is the winner!"); return 1;
		}	
		return 0;
	}

	/**
	 * Create the frame.
	 */
	public Gui() throws InterruptedException {
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
		
		String name;
		name = JOptionPane.showInputDialog(null, "What is your name?", null);
		JLabel lblPlayer1 = new JLabel(name);
		panel.add(lblPlayer1, "2, 2, center, center");		
	
		name = "Computer";
		lblPlayer2 = new JLabel(name);
		panel.add(lblPlayer2, "6, 2, center, center");
		
		btn1 = new JButton("");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameField.set(0, CellState.TIC);
				if (doAction()==0) pauseAction();
			}
		});
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel.add(btn1, "2, 4, fill, fill");
		
		btn2 = new JButton("");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameField.set(1, CellState.TIC);
				if (doAction()==0) pauseAction();
			}
		});
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel.add(btn2, "4, 4, fill, fill");
		
		btn3 = new JButton("");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameField.set(2, CellState.TIC);
				if (doAction()==0) pauseAction();
			}
		});		
		btn3.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel.add(btn3, "6, 4, fill, fill");
		
		btn4 = new JButton("");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameField.set(3, CellState.TIC);
				if (doAction()==0) pauseAction();
			}
		});		
		btn4.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel.add(btn4, "2, 6, fill, fill");
		
		btn5 = new JButton("");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameField.set(4, CellState.TIC);
				if (doAction()==0) pauseAction();
			}
		});		
		btn5.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel.add(btn5, "4, 6, fill, fill");
		
		btn6 = new JButton("");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameField.set(5, CellState.TIC);
				if (doAction()==0) pauseAction();
			}
		});		
		btn6.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel.add(btn6, "6, 6, fill, fill");
		
		btn7 = new JButton("");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameField.set(6, CellState.TIC);
				if (doAction()==0) pauseAction();
			}
		});		
		btn7.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel.add(btn7, "2, 8, fill, fill");
		
		btn8 = new JButton("");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameField.set(7, CellState.TIC);
				if (doAction()==0) pauseAction();
			}
		});		
		btn8.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel.add(btn8, "4, 8, fill, fill");
		
		btn9 = new JButton("");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameField.set(8, CellState.TIC);
				if (doAction()==0) pauseAction();
			}
		});		
		btn9.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel.add(btn9, "6, 8, fill, fill");
		
		//init gamefield
		final int length = 9;
		gameField = new ArrayList<CellState>();
		for (int i=0; i<length; i++)
			gameField.add(i, CellState.TOE);
		
		//init players and referee
		player1 = new CrissCrossPlayer(CellState.TIC, lblPlayer1.getText());
		player2 = new CrissCrossPlayer(CellState.TAC, lblPlayer2.getText());  
		referee = new Referee(gameField);
		referee.addPlayer(player1);
		referee.addPlayer(player2);
		
		JButton btnReferee = new JButton("New game");
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
		panel.add(btnReferee, "4, 2, fill, fill");
		if (referee.playerTurn==1)
			doAction();		
	}
}
