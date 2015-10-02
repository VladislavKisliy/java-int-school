package com.weigandtconsulting.javaschool.dmitry;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.io.IOException;




@SuppressWarnings("serial")
public class GuiCS extends JFrame {
	private final int TCPPORT = 54555;
	private final int UDPPORT = 54777;
	private final int length=9;
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
	private Server server;
	private Client client;
	

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Object[] options = {"Server", "Client"};
		int n = JOptionPane.showOptionDialog(null, "I would like to run...",
													"CrissCrossGame!",
												    JOptionPane.YES_NO_OPTION,
												    JOptionPane.QUESTION_MESSAGE,
												    null, options, options[0]);		
		if (n == 0){
	        EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						GuiCS frame = new GuiCS(true);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});	        
		}else{
	       EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						GuiCS frame = new GuiCS(false);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});	  			
		}
		
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
	
/*	private void pauseAction(){
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
	} */
	
	private int doAction(){
		int step;
		int result = 0;
		if (referee.playerTurn==1)
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		referee.nextPlayerStep(gameField);
		refreshBattleField(gameField);
		step = referee.checkStep(gameField);
		switch (step){
		case 0: result = 0; break; //Has next step! 
		case -1: JOptionPane.showMessageDialog(null, "It's a draw!"); result = 1; 
				 server.sendToAllTCP("It's a draw!");break;
		case -99: JOptionPane.showMessageDialog(null, "Replace this sucker and try again!"); result = 1; 
			      server.sendToAllTCP("Replace this sucker and try again!"); break; 
		case 1: JOptionPane.showMessageDialog(null, "Player "+player1.getPlayerName()+" is the winner!"); result = 1; 
				server.sendToAllTCP("Player "+player1.getPlayerName()+" is the winner!"); break;
		case 2: JOptionPane.showMessageDialog(null, "Player "+player2.getPlayerName()+" is the winner!"); result = 1; 
				server.sendToAllTCP("Player "+player2.getPlayerName()+" is the winner!"); break;
		}	
		return result;
	}
	
	private void enabledButtons(boolean enabled){
		btnCell0.setEnabled(enabled);
		btnCell1.setEnabled(enabled);
		btnCell2.setEnabled(enabled);
		btnCell3.setEnabled(enabled);
		btnCell4.setEnabled(enabled);
		btnCell5.setEnabled(enabled);
		btnCell6.setEnabled(enabled);
		btnCell7.setEnabled(enabled);
		btnCell8.setEnabled(enabled);
	}
	
	private void initialize(boolean isServer, String clientName){
		
		if (isServer)
			setTitle("TicTacToe Server");
		else
			setTitle("TicTacToe Client");
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
		
		lblPlayer1 = new JLabel(clientName);
		panel.add(lblPlayer1, "2, 2, center, center");	
		lblPlayer2 = new JLabel("Computer");
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
	 * @throws IOException 
	 */
	public GuiCS(boolean isServer) throws InterruptedException, IOException {	
		String clientName = "Client";
		if (isServer){
			server = new Server();
			server.bind(TCPPORT, UDPPORT);
	        server.start();
	        
	        Kryo kryo = server.getKryo();
	        ObjectSpace.registerClasses(kryo);
	        kryo.register(ArrayList.class);
	        kryo.register(List.class);
	        kryo.register(CellState.class);	
	        
	        this.addWindowListener(new WindowAdapter() {
	            public void windowClosed(WindowEvent evt) {
	                server.stop();
	            }
	        });	
	        
	        server.addListener(new Listener() {
	            @SuppressWarnings("unchecked")
				@Override
	            public void received(Connection connection, Object object) {
	            	if (object instanceof ArrayList<?>){
		            	gameField = (ArrayList<CellState>) object;
		            	refreshBattleField(gameField);
		            	if (doAction()==0) doAction();
		            	refreshBattleField(gameField);
		            	//server.sendToAllTCP(gameField);
		                connection.sendTCP(gameField);
	            	}else if (object instanceof String){ 
	            		     if ("New game".equals(object))
	            		     	btnReferee.doClick();
	            		     if ("Name".equals(((String) object).substring(0, 4))){
	            		    	 player1.setPlayerName(((String) object).substring(5));
	            		     }	            		    	 
	            	      }
	            			            		
	            }
	        });	        
		}else{
			JTextField tServer = new JTextField("127.0.0.1");
			JTextField tPort = new JTextField("54555");	
			JTextField tName = new JTextField("Карбофос");
			final JComponent[] inputs = new JComponent[] {
					new JLabel("Server:"),
					tServer,
					new JLabel("Port:"),
					tPort,
					new JLabel("Port:"),
					tName
			};
			JOptionPane.showMessageDialog(null, inputs, "Specify connection parameters", JOptionPane.PLAIN_MESSAGE);
			clientName = tName.getText();
			
			client = new Client();
		    client.start();
		    try {
				client.connect(5000, tServer.getText(), Integer.valueOf(tPort.getText()), UDPPORT);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Wrong port. Contact your system administrator, asshole!");
				e.printStackTrace();
				System.exit(0);
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Couldn't connect. Contact your system administrator, asshole!");
				System.exit(0);
			}	
		    
		    Kryo kryo = client.getKryo();
		    ObjectSpace.registerClasses(kryo);
		    kryo.register(ArrayList.class);
		    kryo.register(List.class);
	        kryo.register(CellState.class);		
	        
	        this.addWindowListener(new WindowAdapter() {
	            public void windowClosed(WindowEvent evt) {
	                client.stop();
	            }
	        });	
	        
	        client.addListener(new Listener() {
	            @SuppressWarnings("unchecked")
				@Override
	            public void received(Connection connection, Object object) {
	            	if (object instanceof ArrayList<?>){
		            	gameField = (ArrayList<CellState>) object;
		            	refreshBattleField(gameField);
		            	enabledButtons(true);
	            	}else if (object instanceof String)
	            		JOptionPane.showMessageDialog(null, object);
	            }
	        });	        
		}
			
		initialize(isServer, clientName);

		if (!isServer){
			client.sendTCP("Name:"+lblPlayer1.getText());
			btnCell0.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameField.set(0, CellState.TIC);
					refreshBattleField(gameField);
					client.sendTCP(gameField);//if (doAction()==0) pauseAction();
					enabledButtons(false);
				}
			});
			
			btnCell1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameField.set(1, CellState.TIC);
					refreshBattleField(gameField);
					client.sendTCP(gameField);//if (doAction()==0) pauseAction();
					enabledButtons(false);
				}
			});
			
			btnCell2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameField.set(2, CellState.TIC);
					refreshBattleField(gameField);
					client.sendTCP(gameField);//if (doAction()==0) pauseAction();
					enabledButtons(false);
				}
			});		
			
			btnCell3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameField.set(3, CellState.TIC);
					refreshBattleField(gameField);
					client.sendTCP(gameField);//if (doAction()==0) pauseAction();
					enabledButtons(false);
				}
			});		
			
			btnCell4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameField.set(4, CellState.TIC);
					refreshBattleField(gameField);
					client.sendTCP(gameField);//if (doAction()==0) pauseAction();
					enabledButtons(false);
				}
			});		
			
			btnCell5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameField.set(5, CellState.TIC);
					refreshBattleField(gameField);
					client.sendTCP(gameField);//if (doAction()==0) pauseAction();
					enabledButtons(false);
				}
			});		
			
			btnCell6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameField.set(6, CellState.TIC);
					refreshBattleField(gameField);
					client.sendTCP(gameField);//if (doAction()==0) pauseAction();
					enabledButtons(false);
				}
			});		
			
			btnCell7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameField.set(7, CellState.TIC);
					refreshBattleField(gameField);
					client.sendTCP(gameField);//if (doAction()==0) pauseAction();
					enabledButtons(false);
				}
			});		
			
			btnCell8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameField.set(8, CellState.TIC);
					refreshBattleField(gameField);
					client.sendTCP(gameField);//if (doAction()==0) pauseAction();
					enabledButtons(false);
				}
			});		
			btnReferee.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0){
					client.sendTCP("New game");				
				}
			});
			client.sendTCP("New game");
		}else {
			btnReferee.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0){
					for (int i=0; i<length; i++)
						gameField.set(i, CellState.TOE);
					referee = new Referee(gameField);
					referee.addPlayer(player1);
					referee.addPlayer(player2);
					if (referee.playerTurn==1){
						doAction();					
					}
					server.sendToAllTCP(gameField);
					refreshBattleField(gameField);
				}
			});			
			if (referee.playerTurn==1)
				doAction();	
			server.sendToAllTCP(gameField);
		}		
	}
}
