package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    // Global Variables
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) { // Not used, but must be implemented for KeyListener

    }

    @Override
    public void keyPressed(KeyEvent e) { // Detects when/which key is pressed

        // ** Very Optional Addons and Known Issues **
        // Maybe add diagonals? It would be really annoying but possible
        // Key input is a bit tedious regarding response time
        // Unsure about animation cycle timing optimization
        // Possibly add character selection
        // Puzzle idea ~ pattern on the floor that you have to follow or something
        // ~ Set a logic check that determines if the character passes through the right cords

        int code = e.getKeyCode(); // Detects key pressed

        if (code == KeyEvent.VK_W) { // Detects W input
            upPressed = true;
        }

        if (code == KeyEvent.VK_S) { // Detects S input
            downPressed = true;
        }

        if (code == KeyEvent.VK_A) { // Detects A input
            leftPressed = true;
        }

        if (code == KeyEvent.VK_D) { // Detects D input
            rightPressed = true;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) { // Detects when/which key is released

        int code = e.getKeyCode(); // Can declare twice since we are retrieving from global location

        if (code == KeyEvent.VK_W); { // Detects W release
            upPressed = false;
        }

        if (code == KeyEvent.VK_S); { // Detects A release
            downPressed = false;
        }

        if (code == KeyEvent.VK_A); { // Detects S release
            leftPressed = false;
        }

        if (code == KeyEvent.VK_D); { // Detects D release
            rightPressed = false;
        }
    }
}
