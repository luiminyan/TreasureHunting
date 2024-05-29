package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValue();
        getImagePlayer();
    }

    public void setDefaultValue() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }


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


    public void update() {
        if (keyHandler.leftPressed) {
            direction = "left";
            x -= speed;
            System.out.println("Player moved left!");
        }
        if (keyHandler.rightPressed) {
            direction = "right";
            x += speed;
            System.out.println("Player moved right!");
        }
        if (keyHandler.upPressed) {
            direction = "up";
            y -= speed;
            System.out.println("Player moved upwards!");
        }
        if (keyHandler.downPressed) {
            direction = "down";
            y += speed;
            System.out.println("Player moved downwards!");
        }

    }

    public void draw(Graphics2D g2) {
        // g2 is like a pen
        BufferedImage bufferedImage = null;
        switch (direction) {
            case "up":
                bufferedImage = up1;
                break;
            case "down":
                bufferedImage = down1;
                break;
            case "left":
                bufferedImage = left1;
                break;
            case "right":
                bufferedImage = right1;
                break;
        }
        g2.drawImage(bufferedImage, x, y, gamePanel.tileSize, gamePanel.tileSize,null);
    }
}
