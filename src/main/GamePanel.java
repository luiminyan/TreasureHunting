package main;

import entity.Player;
import tiles.TileManager;

import javax.swing.*;
import java.awt.*;


/**
 *  A class for the central game program, which defines the game statistics, creates the game panel,
 *  the game thread, events listener, draws game entities and maps.
 */

public class GamePanel extends JPanel implements Runnable{
    // final: unchangeable, static: class variable
    final static int originalTileSize = 16;  // 16 * 16 tiles
    final static int scale = 3;     // to increase the size of the figures
    public final static int tileSize = originalTileSize * scale;   // 48 * 48 tiles
    public final static int maxScreenCol = 16;
    public final static int maxScreenRow = 12;
    public final static int screenWidth = tileSize * maxScreenCol;
    public final static int screenHeight = tileSize * maxScreenRow;

    int FPS = 60;

    TileManager tileManager = new TileManager(this);    //Map drawer
    KeyHandler keyHandler = new KeyHandler();   // EventHandler
    Thread gameThread;  // Run the game with a thread, so it can be paused, continued. To repeat some process again and again
    Player player = new Player(this, keyHandler);   // Add Player


    /**
     *  Class constructor, which sets the window size, draws black background, add event listener and overrides the default focus
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);   //  Improve game performance
        //  Assign eventHandler on events from this class
        this.addKeyListener(keyHandler);
        // Override the default focus
        this.setFocusable(true);
    }

    /**
     * Create new thread for this class and start the thread run.
     */

    public void startGameThread() {
        // Passing GamePanel to gameThread, to execute the run method
        // Use the thread to execute run() with params from this class
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * The game loop. Implement the run() function for the game thread.
     */
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;


        while (gameThread != null) {
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

    /**
     * Update the player statistics (position, direction).
     */
    public void update() {
        player.update();
    }

    /**
     *
     * @param g the <code>Graphics</code> object (the painting brush), to be passed on to game map and figures.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);    // paintComponent() is from JPanel
        Graphics2D g2 = (Graphics2D) g;     // Graphics2D: more functions than Graphic

        tileManager.draw(g2);   //Draw map(s).

        player.draw(g2);    //Draw the player.


        g2.dispose();   // Save some memories, release resource
    }
}
