package tiles;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        // to tore tile (pics and collision)
        tiles =  new Tile[10];
        getTileImage();
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2D) {
        for (int x = 0; x < GamePanel.screenWidth; x += 48) {
            // x, y: pixel position
            graphics2D.drawImage(tiles[0].image,x, 0, GamePanel.tileSize, GamePanel.tileSize, null);
        }


    }
}
