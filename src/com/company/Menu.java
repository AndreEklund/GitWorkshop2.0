package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Menu {

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JButton startBtn = new JButton("Start");
    JButton mapOne = new JButton("Map one");
    JTextField textRow = new JTextField("Row");
    JTextField textCol = new JTextField("Col");

    public Menu() {
        frame.setLocation(700,300);
        frame.setSize(400, 200);
        startBtn.setPreferredSize(new Dimension(100, 50));
        startBtn.addActionListener(new Listener());
        mapOne.setPreferredSize(new Dimension(100,50));
        mapOne.addActionListener(new Listener());
        textRow.setPreferredSize(new Dimension(30, 20));
        textCol.setPreferredSize(new Dimension(30, 20));
        panel.add(startBtn);
        panel.add(mapOne);
        panel.add(textRow);
        panel.add(textCol);
        frame.add(panel);
        frame.setVisible(true);

    }
    public class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            if (e.getSource()==startBtn) {
                try {
                    if (Integer.parseInt(textCol.getText()) < 15 || Integer.parseInt(textRow.getText()) < 15) {
                        new MainFrame(Integer.parseInt(textRow.getText()) * Integer.parseInt(textCol.getText()), Integer.parseInt(textRow.getText()), Integer.parseInt(textCol.getText()));
                    } else JOptionPane.showMessageDialog(null, "Put less than 15 as numbers");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (e.getSource()==mapOne){
                try {
                    new MainFrame(100,10,10);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
    }
}
