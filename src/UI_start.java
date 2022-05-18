import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class UI_start  extends JFrame implements KeyListener{
    //Instanzen
    JFrame window = new JFrame();
    UI_mapmenu mm = new UI_mapmenu();
    UI_escmenu em = new UI_escmenu();
    UI_gamepanel gamePanel;

    //Variablen
    public boolean nStart;
    public boolean escOpen;
    public boolean nMenu;

    //Swing Components
    public JPanel start_Panel;
    private JButton start_Button;
    private JButton exit_Button;

    //Konstruktor
    public UI_start(){
        //Icon von dem Fenster
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/pictures_map/icon.png")));
        window.setIconImage(icon.getImage());

        //window eigenschaften
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Tower Defense");
        window.setLocationRelativeTo(null);
        window.setFocusable(true);
        window.requestFocusInWindow();
        window.setSize(854,480); //1920*1080
        window.setContentPane(start_Panel);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Vollbild
        //window.setUndecorated(true);

        window.setVisible(true);

        //Aktiviert den KeyListener Für das den JFrame Window (ab da fängt er die Signale der Tastatur auf)
        window.addKeyListener(this);

        buttonListeners();

    }

    public void buttonListeners(){
        ActionListener buttonListener = ae -> {
            //Speicher die Quelle des Events in eine Variable
            Object o = ae.getSource();

            if(o == mm.Map1 ){
                System.out.println("Sie haben die Map 1 Gewaehlt" );
                mapAuswahl(1);

            }else if (o == mm.Map2) {
                System.out.println("Sie haben die Map 2 Gewaehlt" );
                mapAuswahl(2);

            }else if (o == mm.Map3){
                System.out.println("Sie haben die Map 3 Gewaehlt" );
                mapAuswahl(3);

            //Start Button Funktion.
            }else if (o == start_Button){
                System.out.println("Start");
                start_Panel.setVisible(false);
                window.setContentPane(mm.menu_Panel);
                mm.menu_Panel.setVisible(true);
                //Damit in Start nicht das Startmenü aufgerufen werden kann.
                nStart = true;
                //Damit man das Menü im Menü nicht sieht.
                nMenu = true;
                //Exit Button Funktion
            }else if (o == exit_Button){
                System.out.println("Exit");
                System.exit(0);
            //ESC Menü Buttons
            }else if (o == em.weiter_Button){
                System.out.println("Continue");
                em.esc_Panel.setVisible(false);
                //Gibt an ob das Esc Menü offen oder geschlossen ist.
                escOpen = false;
            }//Exit Button Funktion
            else if (o == em.exit_Button){
                System.out.println("exitovermenu");
                System.exit(0);
            }
            else if (o == em.menu_Button){
                System.out.println("Main menu");
                em.esc_Panel.setVisible(false);
                window.setContentPane(mm.menu_Panel);
                mm.menu_Panel.setVisible(true);
                nStart = true;
                escOpen = false;
                nMenu = true;
            }
        };
        //Weist den ActionListener zu
        mm.Map1.addActionListener(buttonListener);
        mm.Map2.addActionListener(buttonListener);
        mm.Map3.addActionListener(buttonListener);
        exit_Button.addActionListener(buttonListener);
        start_Button.addActionListener(buttonListener);
        em.weiter_Button.addActionListener(buttonListener);
        em.exit_Button.addActionListener(buttonListener);
        em.menu_Button.addActionListener(buttonListener);
    }


    public static void main(String[] args) {
        UI_start Frame = new UI_start();
        System.out.println("-----Beginn-----");
        Frame.addKeyListener(Frame);
        System.out.println(System.getProperty("os.name"));

    }

    public void mapAuswahl(int map){
        Rectangle r = window.getBounds();
        if(gamePanel == null) {
            gamePanel = new UI_gamepanel(r.width,r.height);
        }
        mm.menu_Panel.setVisible(false);
        window.setContentPane(gamePanel);
        gamePanel.startGameThread();
        gamePanel.newMap(map);
        gamePanel.mapCache = true;
        nMenu = false;
        gamePanel.ui.ab = true;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        //Wenn die Esc Taste gedrückt wird und es noch nicht geöffnet ist, wird es sichtbar gemacht.
        if(keyEvent.getKeyCode() == 27 && nStart){
            if(escOpen){
                em.esc_Panel.setVisible(false );
                escOpen = false;
            }
            else {
                escOpen = true;
                em.esc_Panel.setVisible(true);
                //Ist dafür da das es als overlay angezeigt wird. klappt aber noch nicht ganz
                Rectangle r = window.getBounds();
                window.setLayout(null);
                window.add(em.esc_Panel);
                em.menu_Button.setVisible(!nMenu);
                em.esc_Panel.setBounds(0, 0, r.width, r.height);
                SwingUtilities.updateComponentTreeUI(window);
            }
        }
    }
}
