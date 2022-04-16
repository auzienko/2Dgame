package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2, GamePanel gamePanel){
        int tileSize = gamePanel.getTileSize();
        int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
        int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();
        if (worldX >= gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX() - tileSize&&
                worldX <= gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX() + tileSize &&
                worldY >= gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY() - tileSize &&
                worldY <= gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY() + tileSize)
            g2.drawImage(image, screenX, screenY, tileSize, tileSize, null);
    }
}
