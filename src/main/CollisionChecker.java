package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entityLeftWorldX + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entityTopWorldY + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.getTileSize();
        int entityRightCol = entityRightWorldX / gamePanel.getTileSize();
        int entityTopRow = entityTopWorldY / gamePanel.getTileSize();
        int entityBottomRow = entityBottomWorldY / gamePanel.getTileSize();

        int tileNum1 = 0, tileNum2 = 0;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTiles()[entityTopRow][entityLeftCol];
                tileNum2 = gamePanel.getTileManager().getMapTiles()[entityTopRow][entityRightCol];
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTiles()[entityBottomRow][entityLeftCol];
                tileNum2 = gamePanel.getTileManager().getMapTiles()[entityBottomRow][entityRightCol];
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTiles()[entityBottomRow][entityLeftCol];
                tileNum2 = gamePanel.getTileManager().getMapTiles()[entityTopRow][entityLeftCol];
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTiles()[entityBottomRow][entityRightCol];
                tileNum2 = gamePanel.getTileManager().getMapTiles()[entityTopRow][entityRightCol];
                break;
            default:
                break;
        }
        if (gamePanel.getTileManager().getTiles()[tileNum1].getCollision() == true ||
                gamePanel.getTileManager().getTiles()[tileNum2].getCollision() == true)
            entity.collisionOn = true;

    }
}
