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
        mapTiles = new int[gamePanel.getMaxWorldRow()][gamePanel.getMaxWorldCol()];
        tileSize = gamePanel.getTileSize();
        loadMap("world01.txt");
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
            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road00.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapName) {
        try {
            InputStream is = getClass().getResourceAsStream("/maps/" + mapName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            for (int i = 0; i < gamePanel.getMaxWorldRow(); i++) {
                String line = reader.readLine();
                String[] numbers = line.split(" ");
                for (int j = 0; j < gamePanel.getMaxWorldCol(); j++) {
                    mapTiles[i][j] = Integer.parseInt(numbers[j]);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldRow = 0;
        while (worldRow < gamePanel.getMaxWorldRow()) {
            int worldCol = 0;
            while (worldCol < gamePanel.getMaxWorldCol()) {
                int worldX = worldCol * tileSize;
                int worldY = worldRow * tileSize;
                int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
                int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();
                if (worldX >= gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX() - tileSize&&
                        worldX <= gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX() + tileSize &&
                        worldY >= gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY() - tileSize &&
                        worldY <= gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY() + tileSize)
                    g2.drawImage(tiles[mapTiles[worldRow][worldCol]].image, screenX, screenY, tileSize, tileSize, null);
                ++worldCol;
            }
            ++worldRow;
        }
    }

}
