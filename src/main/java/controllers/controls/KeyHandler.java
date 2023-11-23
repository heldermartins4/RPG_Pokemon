<<<<<<< HEAD:src/main/java/controllers/controls/KeyHandler.java
package controllers.controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean up, down, left, right;

    public KeyHandler() {
        up = down = left = right = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
        // char keyChar = e.getKeyChar();
        // System.out.println("Key Typed: " + keyChar);
    }    

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                up = true;
                break;
            case KeyEvent.VK_A:
                left = true;
                break;
            case KeyEvent.VK_S:
                down = true;
                break;
            case KeyEvent.VK_D:
                right = true;
                break;
        
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                up = false;
                break;
            case KeyEvent.VK_A:
                left = false;
                break;
            case KeyEvent.VK_S:
                down = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
        
            default:
                break;
        }
    }
}
=======
package controllers.game.controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean up, down, left, right;

    public KeyHandler() {
        up = down = left = right = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
        // char keyChar = e.getKeyChar();
        // System.out.println("Key Typed: " + keyChar);
    }    

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                up = true;
                break;
            case KeyEvent.VK_A:
                left = true;
                break;
            case KeyEvent.VK_S:
                down = true;
                break;
            case KeyEvent.VK_D:
                right = true;
                break;
        
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                up = false;
                break;
            case KeyEvent.VK_A:
                left = false;
                break;
            case KeyEvent.VK_S:
                down = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
        
            default:
                break;
        }
    }
}
>>>>>>> 1bdcb4bf15ac087550d45cca8b0fcc401119ff13:src/main/java/controllers/game/controls/KeyHandler.java
