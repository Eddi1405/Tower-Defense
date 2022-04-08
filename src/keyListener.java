import java.awt.event.*;
import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;



/**
 * Hier wird gecheckt ob die ausgewählte Taste gedrückt wurde
 */

public class keyListener implements KeyListener
{
  //Variablen
  boolean esc_pressed;
  int code;


  //Checkt ob eine Taste gedrückt wurde
  @Override
  public void keyPressed(KeyEvent event) {

    code = event.getKeyCode();

    //Wen ESC gedrückt wird, wird esc_pressed auf true gesetzt.
    if( code == KeyEvent.VK_ESCAPE){

      esc_pressed = true;
    }
  }

  //Checkt ob eine Taste losgelassen wurde
  @Override
  public void keyReleased(KeyEvent event){

    //Wen ESC losgelassen wird, wird esc_pressed auf false gesetzt.
    if(event.getKeyCode() == KeyEvent.VK_ESCAPE){

      esc_pressed = false;

    }

  }
  public int getCode(KeyEvent event){
    return event.getKeyCode();
  }

  //Checkt ob eine Taste eingegeben wurde
  public void keyTyped(KeyEvent event){
  }
}