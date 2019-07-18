package com.guessingGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GuessingGame {

    private JPanel gameView;
    private JTextField numberField;
    private JButton guessButton;
    private JLabel infoText;
    private JLabel numberGuess;
    private JLabel txtNumberGuess;
    private JLabel txtGuessNumber;
    private JButton playAgainButton;
    private int theNumber;


    private void initialSetup(){
        theNumber = (int) (Math.random() * 100) + 1;
        guessButton.setVisible(true);
        txtGuessNumber.setVisible(true);
        numberField.setVisible(true);
        numberGuess.setText("0");
        playAgainButton.setEnabled(false);
        infoText.setText("Enter a number above and click Guess!");
        numberField.setText("");
        numberField.requestFocus();
        numberField.selectAll();
    }

    private static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }


    private void checkResult() {
        String guess = numberField.getText();
        if (!isNumeric(guess) || Integer.parseInt(guess) < 1 || Integer.parseInt(guess) > 100) {
            infoText.setText("Choose a valid number between 1 and 100!");
            numberField.requestFocus();
            numberField.selectAll();
        } else {
            int myGuess = Integer.parseInt(guess);

            int oldNumber = Integer.parseInt(numberGuess.getText());
            oldNumber++;
            numberGuess.setText(String.valueOf(oldNumber));
            if (myGuess > theNumber) {
                infoText.setText("Go lower!!");
                numberField.requestFocus();
                numberField.selectAll();
            } else if (myGuess < theNumber) {
                infoText.setText("Go higher!!");
                numberField.requestFocus();
                numberField.selectAll();
            } else {
                //   frame.setVisible(false);
                infoText.setText("You won! The number was " + theNumber);
                guessButton.setVisible(false);
                txtGuessNumber.setVisible(false);
                numberField.setVisible(false);
                playAgainButton.setEnabled(true);
                playAgainButton.requestFocus();
            }
        }

    }

    private void setListeners(){
        numberField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                int tecla = e.getKeyChar();
                if (tecla == 10) {
                    checkResult();
                }
            }
        });
        guessButton.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent actionEvent) {
                checkResult();
            }
        });
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialSetup();
            }
        });
        playAgainButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                int tecla = e.getKeyChar();
                if (tecla == 10) {
                    initialSetup();
                }
            }
        });

    }

    public static void main(String[] args) {
        GuessingGame juego = new GuessingGame();
        JFrame frame = new JFrame("Guessing Game");
        frame.setContentPane(juego.gameView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        frame.setVisible(true);

        juego.initialSetup();
        juego.setListeners();
    }
}
