package com.mcdJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class McdJava {


    private JTextField firstNumber;
    private JTextField secondNumber;
    private JPanel panel;
    private JLabel txtFirstNumber;
    private JLabel txtSecondNumber;
    private JLabel result;
    private JButton calculateButton;

    private class Pair {
        private int first;
        private int second;
    }

    Pair swap(int x, int y) {
        Pair res = new Pair();
        res.first = y;
        res.second = x;
        return res;
    }

    int getMcd(int x, int y) {
        if (x < 0) x = -x;
        if (y < 0) y = -y;
        Pair swapped;
        if (x < y) {
            swapped = swap(x, y);
            x = swapped.first;
            y = swapped.second;
        }
        if (x == 1) {
            return 1;
        } else if (y == 0) {
            return x;
        } else {
            x = x % y;
            swapped = swap(x, y);
            x = swapped.first;
            y = swapped.second;
            return getMcd(x, y);
        }


    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    private static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    void runListeners() {
        calculateButton.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent actionEvent) {
                runMcd();
            }
        });
        firstNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                int tecla = e.getKeyChar();
                if (tecla == 10) {
                    runMcd();
                }
            }
        });
        secondNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                int tecla = e.getKeyChar();
                if (tecla == 10) {
                    runMcd();
                }
            }
        });
    }

    void runMcd() {
        if (isInteger(firstNumber.getText()) && isInteger(secondNumber.getText())) {
            int res = getMcd(Integer.parseInt(firstNumber.getText()), Integer.parseInt(secondNumber.getText()));
            result.setText("(" + firstNumber.getText() + ":" + secondNumber.getText() + ") = " + res);
        } else {
            result.setText("Please write two valid numbers above");
        }
    }


    public static void main(String[] args) {
        McdJava mcdJava = new McdJava();
        JFrame frame = new JFrame("MCD Calculator");
        frame.setContentPane(mcdJava.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(400, 400));
        frame.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        frame.setVisible(true);

        mcdJava.runListeners();
    }
}
