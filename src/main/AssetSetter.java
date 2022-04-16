package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void setObject(){
        gamePanel.getObjs()[0] = new OBJ_Key();
        gamePanel.getObjs()[0].worldX = 23 * gamePanel.getTileSize();
        gamePanel.getObjs()[0].worldY = 7 * gamePanel.getTileSize();

        gamePanel.getObjs()[1] = new OBJ_Key();
        gamePanel.getObjs()[1].worldX = 23 * gamePanel.getTileSize();
        gamePanel.getObjs()[1].worldY = 40 * gamePanel.getTileSize();

        gamePanel.getObjs()[2] = new OBJ_Key();
        gamePanel.getObjs()[2].worldX = 38 * gamePanel.getTileSize();
        gamePanel.getObjs()[2].worldY = 9 * gamePanel.getTileSize();

        gamePanel.getObjs()[3] = new OBJ_Door();
        gamePanel.getObjs()[3].worldX = 10 * gamePanel.getTileSize();
        gamePanel.getObjs()[3].worldY = 11 * gamePanel.getTileSize();

        gamePanel.getObjs()[4] = new OBJ_Door();
        gamePanel.getObjs()[4].worldX =  8 * gamePanel.getTileSize();
        gamePanel.getObjs()[4].worldY =  28 * gamePanel.getTileSize();

        gamePanel.getObjs()[5] = new OBJ_Door();
        gamePanel.getObjs()[5].worldX =  12 * gamePanel.getTileSize();
        gamePanel.getObjs()[5].worldY =  22 * gamePanel.getTileSize();

        gamePanel.getObjs()[6] = new OBJ_Chest();
        gamePanel.getObjs()[6].worldX =  10 * gamePanel.getTileSize();
        gamePanel.getObjs()[6].worldY =  7 * gamePanel.getTileSize();
    }
}
