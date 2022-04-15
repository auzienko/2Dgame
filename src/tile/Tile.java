package tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile {
    public BufferedImage image;
    boolean collision = false;

    public Tile() {
    }

    public Tile(String spriteName, boolean collision) {
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + spriteName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.collision = collision;
    }

    public boolean getCollision(){
        return collision;
    }
}
