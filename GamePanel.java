package Main;

import entity.Player;
import tile.TileManager;

import java.awt.*;
import java.awt.color.*;
import java.awt.Dimension.*;

import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    // Screen Settings
    final int originalTileSize = 32; // Pixel dimensions of the sprites/graphics used
    final int scale = 2; // Scale of image dilation/enlargement

    public final int tileSize = originalTileSize * scale; // Makes the images larger so user can see them better
    public final int maxScreenCol = 16; // 16 columns of tiles across
    public final int maxScreenRow = 12; // 12 rows of tiles across. Keep a 4:3 aspect ratio

    public final int screenWidth = tileSize * maxScreenCol; // Width of window created
    public final int screenHeight = tileSize * maxScreenRow; // Height of window created

    public int FPS = 60; // Frames per second (FPS)

    TileManager tileM = new TileManager(this); // Initiates Tile Manager in this GamePanel
    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // Opens thread

    Player player = new Player(this, keyH); // Creates player and allows access to this gp and this keyH

    public GamePanel() {

        // Constructor
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Determines size of window
        this.setBackground(Color.black); // Background Colour
        this.setDoubleBuffered(true); // Optimization of image pre-load state
        this.addKeyListener(keyH); // Initialises Key Handler keyH
        this.setFocusable(true); // Makes GamePanel specifically search for inputs from keyH

    }

    public void startGamaThread() {

        gameThread = new Thread(this); // Passes current class to the constructor (thread)
        gameThread.start(); // Initiates thread

    }

    public void startGameThread() { // Starts the game thread

        gameThread = new Thread(this); // Declaration of thread
        gameThread.start(); // Initializes thread using start method

    }

    // Unimplemented Methods
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // Nanoseconds are measured in billions. Draws a frame every 1/60 seconds
        double nextDrawTime = System.nanoTime() + drawInterval; // Sets the time the thread must wait for

        while (gameThread != null) {
            // System.out.println("Thread is open"); // Test Line

            long currentTime = System.nanoTime(); // MAY NEED TO DELETE BUT UNSURE IF SHIT WILL BREAK

            update(); // Updates game data

            repaint(); // Repaints graphics based on update() data



            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000; // Converts to milliseconds

                if(remainingTime < 0) {
                    remainingTime = 0; // Resets remaining time if sleep is not necessary
                }

                Thread.sleep((long) remainingTime); // Pauses game loop until sleep time has passed

                nextDrawTime += drawInterval; // Sets new draw time

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() { // Updates object data (graphics characteristics and location)

        player.update(); //  Specifically updates player variables

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g); // Pulls from JPanel Class

        Graphics2D g2 = (Graphics2D)g; // Converts Graphics(g) to Graphics2D graph. Allows usage of Graphics2D(g2) Class

        // Tile must come first since they are behind everything
        tileM.draw(g2); // Draws tiles

        player.draw(g2); // Draws the player

        g2.dispose(); // Dumps previously cached graphics. Graphic is changed any ways so no need for storage

    }
}
