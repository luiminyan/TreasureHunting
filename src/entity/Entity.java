package entity;

import java.awt.image.BufferedImage;

/**
 * A class to store game entities' statistics. To be extended by specific figure classes.
 */
public class Entity {
    public int x;
    public int y;
    public int speed;
    public String direction;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;  // Figure images
    public int spriteCounter = 0;   // Timer for figure movements animation
    public int spriteNum = 1;   // Figure movement
}
