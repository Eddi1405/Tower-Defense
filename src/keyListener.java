import java.awt.event.*;

/**
 * Hier wird gecheckt ob die ausgewählte Taste gedrückt wurde
 */

public class keyListener implements KeyListener
{
  //Variablen
  boolean esc_pressed;
  int code;

  //Checkt ob eine Taste gedrückt wurde
  public void keyPressed(KeyEvent event) {

    code = event.getKeyCode();

    //Wen ESC gedrückt wird, wird esc_pressed auf true gesetzt.
    if( code == KeyEvent.VK_ESCAPE){

      esc_pressed = true;

    }
  }

  //Checkt ob eine Taste losgelassen wurde
  public void keyReleased(KeyEvent event){

    //Wen ESC losgelassen wird, wird esc_pressed auf false gesetzt.
    if(event.getKeyCode() == KeyEvent.VK_ESCAPE){

      esc_pressed = false;

    }

  }

  //Checkt ob eine Taste eingegeben wurde
  public void keyTyped(KeyEvent event){}
}