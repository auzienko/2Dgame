package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    KeyHandler keyHandler = new KeyHandler();
    Sound sound = new Sound();
    Player player = new Player(this, keyHandler);
    TileManager tileManager = new TileManager(this);
    CollisionChecker collisionChecker = new CollisionChecker(this);
    AssetSetter assetSetter = new AssetSetter(this);
    SuperObject[] objs = new SuperObject[10];
    Thread gameThread;

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //FPS
    final int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setupGame() {
        assetSetter.setObject();
        playMusic(0);
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public Player getPlayer() {
        return player;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public SuperObject[] getObjs() {
        return objs;
    }

//    SLEEP GAMELOOP METHOD
//    @Override
//    public void run() {
//        double drawInterval = 1000000000 / FPS;
//        double nextDrawTime = System.nanoTime() + drawInterval;
//        while (gameThread != null) {
//
//            update();
//            repaint();
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime /= 1000000;
//                if (remainingTime < 0) remainingTime = 0;
//                Thread.sleep((long) remainingTime);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            nextDrawTime += drawInterval;
//        }
//    }

    //    DELTA/ACCUMULATOR GAMELOOP METHOD
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                --delta;
            }

        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        for (int i = 0; i < objs.length; i++) {
            if (objs[i] != null) objs[i].draw(g2, this);
        }
        player.draw(g2);
        g2.dispose();
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }
}
