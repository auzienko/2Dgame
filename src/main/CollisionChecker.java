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

    public int checkObject(Entity entity, boolean player) {
        int index = 999;
        for (int i = 0; i <  gamePanel.objs.length; ++i) {
            if (gamePanel.objs[i] != null) {
                //Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //Get the object's solid area position
                gamePanel.objs[i].solidArea.x = gamePanel.objs[i].worldX + gamePanel.objs[i].solidArea.x;
                gamePanel.objs[i].solidArea.y = gamePanel.objs[i].worldY + gamePanel.objs[i].solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                    default: break;
                }
                if (entity.solidArea.intersects(gamePanel.objs[i].solidArea)){
                    if(gamePanel.objs[i].collision == true) {
                        entity.collisionOn = true;
                    }
                    if (player == true) {
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gamePanel.objs[i].solidArea.x = gamePanel.objs[i].solidAreaDefaultX;
                gamePanel.objs[i].solidArea.y = gamePanel.objs[i].solidAreaDefaultY;
            }
        }

        return index;
    }
}
