package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        //  close the window properly when click on X
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");


        //    Add panel into window
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        //    Make window fit with panel size
        window.pack();
        //   Place the window to screen center
        window.setLocationRelativeTo(null);
        //   Make screen visible
        window.setVisible(true);


        // Run game (byt calling the startGameThread function)
        gamePanel.startGameThread();
    }
}
