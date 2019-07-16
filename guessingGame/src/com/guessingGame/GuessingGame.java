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
    private int theNumber;

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
        } else {
            int myGuess = Integer.parseInt(guess);

            int oldNumber = Integer.parseInt(numberGuess.getText());
            oldNumber++;
            numberGuess.setText(String.valueOf(oldNumber));
            if (myGuess > theNumber) {
                infoText.setText("Go lower!!");
            } else if (myGuess < theNumber) {
                infoText.setText("Go higher!!");
            } else {
                //   frame.setVisible(false);
                infoText.setText("You won! The number was " + theNumber);
                guessButton.setVisible(false);
                txtGuessNumber.setVisible(false);
                numberField.setVisible(false);
                // System.out.println("You took only " + counter + " guesses!");
            }
        }
    }

    public static void main(String[] args) {

        //initial setup
        GuessingGame juego = new GuessingGame();
        JFrame frame = new JFrame("Guessing Game");
        frame.setContentPane(juego.gameView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        frame.setVisible(true);
        juego.theNumber = (int) (Math.random() * 100) + 1;

        juego.numberField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                int tecla = e.getKeyChar();
                if (tecla == 10) {
                    juego.checkResult();
                }
            }
        });

        juego.guessButton.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent actionEvent) {
                juego.checkResult();
            }
        });

    }
}
