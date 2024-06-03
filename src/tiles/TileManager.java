package tiles;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

/**
 * Map drawer
 */

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;
    int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        tiles =  new Tile[10];  // to store tile pics & collision
        mapTileNum = new int[GamePanel.maxWorldCol][GamePanel.maxWorldCol];     //make map size = world size

        getTileImage();
        //loadMap("/maps/map00.txt");
        loadMap("/maps/map_world.txt");
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tiles[3].collision = true;

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tiles[5].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapPath) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(mapPath);
            BufferedReader bufferedReader =  new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;
            while (col < GamePanel.maxWorldCol && row < GamePanel.maxWorldRow) {
                String line = bufferedReader.readLine();

                while (col < GamePanel.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col ++;
                }
                col = 0;
                row ++;
            }
        } catch (IOException c) {
            c.printStackTrace();
        }

    }


    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < GamePanel.maxWorldCol && worldRow < GamePanel.maxWorldRow) {
            int imgNum = mapTileNum[worldCol][worldRow];    // read pic num

            int worldX = worldCol * GamePanel.tileSize;
            int worldY = worldRow * GamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            // only draw the tiles in the window
            // add a tile on x and y so no black edge is shown
            if (worldX + GamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX
                && worldX - GamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX
                && worldY + GamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY
                && worldY - GamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY
            ) {
                g2.drawImage(tiles[imgNum].image, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);
            }

            // next column
            worldCol ++;

            // end of row
            if (worldCol == GamePanel.maxWorldCol) {
                // switch to next row
                worldCol = 0;
                worldRow ++;
            }
        }
    }
}
