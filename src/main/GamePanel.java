package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // final: unchangeable, static: class variable
    final static int originalTileSize = 16;  // 16 * 16 tiles
    final static int scale = 3;     // to increase the size of the figures
    public final static int tileSize = originalTileSize * scale;   // 48 * 48 tiles
    final static int maxScreenCol = 16;
    final static int maxScreenRow = 12;
    final static int screenWidth = tileSize * maxScreenCol;
    final static int screenHeight = tileSize * maxScreenRow;

    int FPS = 60;

    // EventHandler
    KeyHandler keyHandler = new KeyHandler();
    // Run the game with a thread, so it can be paused, continued. To repeat some process again and again
    Thread gameThread;


    // Player position
    Player player = new Player(this, keyHandler);


    // Constructor
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        //  Improve game performance
        this.setDoubleBuffered(true);
        //  Assign eventHandler on events from this class
        this.addKeyListener(keyHandler);
        // Override the default focus
        this.setFocusable(true);
    }


    public void startGameThread() {
        // Passing GamePanel to gameThread, to execute the run method
        // Use the thread to execute run() with params from this class
        gameThread = new Thread(this);
        gameThread.start();
    }

    // The game loop
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;


        while (gameThread != null) {
            // System.out.println("Game thread is running!");
            //  1. Update game information
            update();
            //  2. Redraw screen with information
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        // super: the parent graph of g (JPanel)
        super.paintComponent(g);

        // Graphics2D: more functions than Graphic
        Graphics2D g2 = (Graphics2D) g;

        player.draw(g2);

        // Save some memories, release resource
        g2.dispose();
    }
}
