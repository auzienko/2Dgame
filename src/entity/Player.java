package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    int hasKey = 0;

    //PLAYER'S DEFAULT POSITION
    int playerX = 23;
    int playerY = 21;
    int playerSpeed = 4;
    String playerDirection = "down";

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        tileSize = gamePanel.getTileSize();
        screenX = (gamePanel.getScreenWidth() - tileSize) / 2;
        screenY = (gamePanel.getScreenHeight() - tileSize) / 2;
        solidAreaDefaultX = tileSize / 6;
        solidAreaDefaultY = tileSize / 3;
        solidArea = new Rectangle(tileSize / 6, tileSize / 3, tileSize - tileSize / 3, tileSize - tileSize / 3);
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = playerX * tileSize;
        worldY = playerY * tileSize;
        speed = playerSpeed;
        direction = playerDirection;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyHandler.upPressed == true) {
            direction = "up";
        } else if (keyHandler.leftPressed == true) {
            direction = "left";
        } else if (keyHandler.downPressed == true) {
            direction = "down";
        } else if (keyHandler.rightPressed == true) {
            direction = "right";
        } else {
            return;
        }

        collisionOn = false;
        gamePanel.getCollisionChecker().checkTile(this);
        int objIndex = gamePanel.getCollisionChecker().checkObject(this, true);
        pickUpObject(objIndex);

        if (collisionOn == false) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
                default:
                    break;
            }
        }

        ++spriteCounter;
        if (spriteCounter > 12) {
            if (spriteNumber == 1) spriteNumber = 2;
            else if (spriteNumber == 2) spriteNumber = 1;
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNumber == 1)
                    image = up1;
                else if (spriteNumber == 2)
                    image = up2;
                break;
            case "down":
                if (spriteNumber == 1)
                    image = down1;
                else if (spriteNumber == 2)
                    image = down2;
                break;
            case "left":
                if (spriteNumber == 1)
                    image = left1;
                else if (spriteNumber == 2)
                    image = left2;
                break;
            case "right":
                if (spriteNumber == 1)
                    image = right1;
                else if (spriteNumber == 2)
                    image = right2;
                break;
            default:
                break;
        }
        g2.drawImage(image, screenX, screenY, tileSize, tileSize, null);
    }

    public void pickUpObject(int index) {
        if (index != 999) {
            String objectName = gamePanel.getObjs()[index].getName();
            switch (objectName) {
                case "Key":
                    hasKey++;
                    gamePanel.playSE(1);
                    gamePanel.getObjs()[index] = null;
                    break;
                case "Door":
                    if (hasKey > 0) {
                        hasKey--;
                        gamePanel.playSE(3);
                        gamePanel.getObjs()[index] = null;
                    }
                    break;
                case "Boots":
                    speed += 2;
                    gamePanel.playSE(2);
                    gamePanel.getObjs()[index] = null;
                    break;
                case "Chest":
                    gamePanel.playSE(4);
                    gamePanel.stopMusic();
                    gamePanel.getObjs()[index] = null;
                    break;
                default:
                    break;
            }
        }
    }
}
