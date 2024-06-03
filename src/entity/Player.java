package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 */
public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public int screenX, screenY;


    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        // make player in screen center
        screenX = GamePanel.screenWidth / 2 - (GamePanel.tileSize / 2);
        screenY = GamePanel.screenHeight / 2 - (GamePanel.tileSize / 2);

        setDefaultValue();
        getImagePlayer();
    }

    /**
     * Set player starting position, speed and direction
     */
    public void setDefaultValue() {
        worldX = GamePanel.tileSize * 23;
        worldY = GamePanel.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    /**
     * Read player images.
     */
    public void getImagePlayer() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update user position and direction based on keyboard events.
     */
    public void update() {
        if (keyHandler.leftPressed) {
            direction = "left";
            worldX -= speed;
            System.out.println("Player moved left!");
        }
        if (keyHandler.rightPressed) {
            direction = "right";
            worldX += speed;
            System.out.println("Player moved right!");
        }
        if (keyHandler.upPressed) {
            direction = "up";
            worldY -= speed;
            System.out.println("Player moved upwards!");
        }
        if (keyHandler.downPressed) {
            direction = "down";
            worldY += speed;
            System.out.println("Player moved downwards!");
        }

        // each time update is called, spriteCounter increases
        spriteCounter ++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            }
            else if (spriteNum == 2) {
                 spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    /**
     *
     * @param g2, painting brush for player figure.
     */
    public void draw(Graphics2D g2) {
        BufferedImage bufferedImage = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    bufferedImage = up1;
                }
                if (spriteNum == 2) {
                    bufferedImage = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    bufferedImage = down1;
                }
                if (spriteNum == 2) {
                    bufferedImage = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    bufferedImage = left1;
                }
                if (spriteNum == 2) {
                    bufferedImage = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    bufferedImage = right1;
                }
                if (spriteNum == 2) {
                    bufferedImage = right2;
                }
                break;
        }
        g2.drawImage(bufferedImage, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize,null);
    }
}
