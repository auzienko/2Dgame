package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;
    int[][] mapTiles;
    int tileSize;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        mapTiles = new int[gamePanel.getMaxScreenRow()][gamePanel.getMaxScreenCol()];
        tileSize = gamePanel.getTileSize();
        loadMap("map01.txt");
        getTileImage();
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass00.png"));
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water00.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapName) {
        try {
            InputStream is = getClass().getResourceAsStream("/maps/" + mapName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            for (int i = 0; i < gamePanel.getMaxScreenRow(); i++) {
                String line = reader.readLine();
                String[] numbers = line.split(" ");
                for (int j = 0; j < gamePanel.getMaxScreenCol(); j++) {
                    mapTiles[i][j] = Integer.parseInt(numbers[j]);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        while (row < gamePanel.getMaxScreenRow()) {
            col = 0;
            while (col < gamePanel.getMaxScreenCol()) {
                g2.drawImage(tiles[mapTiles[row][col]].image, tileSize * col, tileSize * row, tileSize, tileSize, null);
                ++col;
            }
            ++row;
        }
    }
}
