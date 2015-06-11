package com.weigandtconsulting.javaschool.ilya;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ibeketov on 15/04/15.
 */
public class GUIForm implements Showable {

    private JButton button1;

    public JButton getButton1() {
        return button1;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JButton getButton4() {
        return button4;
    }

    public JButton getButton7() {
        return button7;
    }

    public JButton getButton2() {
        return button2;
    }

    public JButton getButton5() {
        return button5;
    }

    public JButton getButton8() {
        return button8;
    }

    public JButton getButton3() {
        return button3;
    }

    public JButton getButton6() {
        return button6;
    }

    public JButton getButton9() {
        return button9;
    }

    public JButton getButton10() {
        return button10;
    }

    private JPanel panel1;
    private JButton button4;
    private JButton button7;
    private JButton button2;
    private JButton button5;
    private JButton button8;
    private JButton button3;
    private JButton button6;
    private JButton button9;
    private JButton button10;

    public GUIForm() {


        button10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button1.setText("TEST");
            }
        });
    }

    public void refreshBattleField(GameField gameField){
        button1.setText(getGameChar(gameField.getBoard().get(0)));
        button2.setText(getGameChar(gameField.getBoard().get(1)));
        button3.setText(getGameChar(gameField.getBoard().get(2)));
        button4.setText(getGameChar(gameField.getBoard().get(3)));
        button5.setText(getGameChar(gameField.getBoard().get(4)));
        button6.setText(getGameChar(gameField.getBoard().get(5)));
        button7.setText(getGameChar(gameField.getBoard().get(6)));
        button8.setText(getGameChar(gameField.getBoard().get(7)));
        button9.setText(getGameChar(gameField.getBoard().get(8)));
    }

    public String getGameChar(GameSigns fieldSat){

        return fieldSat.playerSymbol;
    }

//    public void showForm () {
//        JFrame frame = new JFrame("GUIForm");
//        frame.setContentPane(new GUIForm().panel1);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }
}
