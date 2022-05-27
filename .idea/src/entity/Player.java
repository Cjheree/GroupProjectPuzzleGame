package entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    private boolean standing;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp; // Sets game panel to current game panel loaded
        this.keyH = keyH; // Sets keyHandler to follow main package keyHandler

        setDefaultValues(); // Sets Default Values
        getPlayerImage();

    }

    public void setDefaultValues() { // Sets default values of entity

        x = 100; // X coordinate
        y = 100; // Y coordinate
        speed = 4; // Pixels traversed per iteration of FPS (1/FPS seconds)
        direction = "down"; // Player starts facing down
    }

    public void getPlayerImage() { // Retrieves image associated with player state (direction)

        try {
            /*
            Each variable we are assigning here is already declared as a buffered image
            We are retrieving the image through the given file path for each cycle/direction state
            ImageIO reads from the data stream and assigns the image data retrieved to our declared variable
            getClass() retrieves from resource folder that getClassLoader() loads
             */

            up = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Standing_Up.png"));
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Walking_Up_1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Walking_Up_2.png"));
            down = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Standing_Down.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Walking_Down_1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Walking_Down_2.png"));
            left = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Standing_Left.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Walking_Left_1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Walking_Left_2.png"));
            right = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Standing_Right.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Walking_Right_1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Walking_Right_2.png"));

        } catch (IOException e) { // Handles errors related to image/path not found
            e.printStackTrace();
        }
    }

    public void update() { // Player specific update() for player specific variables

         /*Location Information
         The screen's (0,0) coordinates are in the top left corner

         Y values increase as the location of the pixel moves further down the screen
         Y values decrease as the location of the pixel moves further up the screen

         X values increase as the location of the pixel moves further to the right on the screen
         X values decrease as the location of the pixel moves further to the left of the screen
        */

        if (keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {

            standing = false;

            if (keyH.upPressed == true) { // Moves player up by factor of player speed and sets direction
                direction = "up";
                y -= speed;

            } else if (keyH.downPressed == true) { // Moves player down by factor of player speed and sets direction
                direction = "down";
                y += speed;

            } else if (keyH.leftPressed == true) { // Moves player left by factor of player speed and sets direction
                direction = "left";
                x -= speed;

            } else if (keyH.rightPressed == true) { // Moves player right by factor of player speed and sets direction
                direction = "right";
                x += speed;
            }

            spriteCounter++;
            if(spriteCounter > (gp.FPS/8)) { // Switches walking cycle 8 times every second / every designated number of frames
                if (spriteNum == 1) {
                    spriteNum = 2;
                    System.out.println(spriteNum);
                } else if(spriteNum == 2) {
                    spriteNum = 1;
                    System.out.println(spriteNum);
                }

                spriteCounter = 0;
                System.out.println(spriteNum);
            }
        } else { // Sets standing when no movement (input) is detected
            standing = true;
        }
    }

    public void draw(Graphics2D g2) { // Player specific draw() for player specific drawings

        BufferedImage image = null; // Declaration of BufferedImage image

        switch(direction) { // Used a switch since we are just checking to see if it changed. "If" statement too long
                            // spriteNum alternates between one and two. Changing the images to simulate walking
            case "up": // Sets image to corresponding walking positions
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                if(standing) { // Returns to standing image
                    image = up;
                }
                break;
            case "down": // Sets image to corresponding walking positions
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                if(standing) { // Returns to standing image
                    image = down;
                }
                break;
            case "left": // Sets image to corresponding walking positions
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                if(standing) { // Returns to standing image
                    image = left;
                }
                break;
            case "right": // Sets image to corresponding walking positions
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                if(standing) { // Returns to standing image
                    image = right;
                }
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }

}
