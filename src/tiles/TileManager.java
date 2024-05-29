package tiles;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;
    int mapTileNum[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        // to tore tile (pics and collision)
        tiles =  new Tile[10];
        mapTileNum = new int[GamePanel.maxScreenCol][GamePanel.maxScreenRow];

        getTileImage();
        loadMap("/maps/map00.txt");
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

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
            while (col < GamePanel.maxScreenCol && row < GamePanel.maxScreenRow) {
                String line = bufferedReader.readLine();

                while (col < GamePanel.maxScreenCol) {
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
        int x = 0;
        int y = 0;
        int col = 0;
        int row = 0;

        while (col < GamePanel.maxScreenCol && row < GamePanel.maxScreenRow) {
            // read pic number
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tiles[tileNum].image, x, y, GamePanel.tileSize, GamePanel.tileSize, null);
            col ++;
            x += GamePanel.tileSize;

            if (col == GamePanel.maxScreenCol) {
                col = 0;
                x = 0;
                row ++;
                y += GamePanel.tileSize;
            }
        }
    }
}
