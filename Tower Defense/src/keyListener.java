import java.awt.*;
import java.awt.event.*;


public class keyListener implements KeyListener{
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE){
            new Menu();
        }
    }


    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
