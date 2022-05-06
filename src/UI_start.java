import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UI_start  extends JFrame implements KeyListener{
    //Main wie aus C, der Start eines Programms der die erste Klasse aufruft
    JFrame window = new JFrame();
    UI_mapmenu mm = new UI_mapmenu();
    UI_escmenu em = new UI_escmenu();

    public boolean nstart;
    public boolean escopen;
    public boolean nmenu;

    public JPanel start_panel;
    private JButton start_button;
    private JButton exit_button;



    UI_gamepanel gamePanel;

    public UI_start(){
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/pictures_map/icon.png"));
        window.setIconImage(icon.getImage());

        //window eigenschaften
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Tower Defense");
        window.setLocationRelativeTo(null);
        window.setFocusable(true);
        window.requestFocusInWindow();
        //window.setSize(2048,  1080); //1920*1080
        window.setContentPane(start_panel);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //window.setUndecorated(true);
        window.setVisible(true);

        //Aktiviert den KeyListener Für das den JFrame Window (ab da fängt er die Signale der Tastatur auf)
        window.addKeyListener(this);

        buttonListeners();

    }

    public void buttonListeners(){
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Object o = ae.getSource();
                //TODO switch geht nicht
                if(o == mm.Map1 ){
                    System.out.println("Sie haben die Map 1 gewaehlt" );
                    mapAuswahl(1);

                }else if (o == mm.Map2) {
                    System.out.println("Sie haben die Map 2 gewaehlt" );
                    mapAuswahl(2);

                }else if (o == mm.Map3){
                    System.out.println("Sie haben die Map 3 gewaehlt" );
                    mapAuswahl(3);

                }else if (o == start_button){
                    System.out.println("Start");
                    start_panel.setVisible(false);
                    window.setContentPane(mm.menu_panel);
                    mm.menu_panel.setVisible(true);
                    nstart = true;
                    nmenu = true;
                }else if (o == exit_button){
                    System.out.println("Exit");
                    System.exit(0);
                //ESC Menü Buttons
                }else if (o == em.weiter_button){
                    System.out.println("Continue");
                    em.esc_panel.setVisible(false);
                    escopen = false;
                }
                else if (o == em.exit_button){
                    System.out.println("exitovermenu");
                    System.exit(0);
                }
                else if (o == em.menu_button){
                    System.out.println("Main menu");
                    em.esc_panel.setVisible(false);
                    window.setContentPane(mm.menu_panel);
                    mm.menu_panel.setVisible(true);
                    nstart = true;
                    escopen = false;
                    nmenu = true;
                }


            }
        };
        //TODO Auslagern in eine Class
        mm.Map1.addActionListener(buttonListener);
        mm.Map2.addActionListener(buttonListener);
        mm.Map3.addActionListener(buttonListener);
        exit_button.addActionListener(buttonListener);
        start_button.addActionListener(buttonListener);
        em.weiter_button.addActionListener(buttonListener);
        em.exit_button.addActionListener(buttonListener);
        em.menu_button.addActionListener(buttonListener);
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
            gamePanel = new UI_gamepanel(map,r.width,r.height);
        }
        mm.menu_panel.setVisible(false);
        window.setContentPane(gamePanel);
        gamePanel.startGameThread();
        gamePanel.newMap(map);
        gamePanel.mapCache = true;
        nmenu = false;
        UI_shop.ab = true;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        System.out.println(keyEvent.getKeyCode());
        if(keyEvent.getKeyCode() == 27 && nstart){
            if(escopen){
                em.esc_panel.setVisible(false );
                escopen = false;
                System.out.println("close");
            }
            else {
                escopen = true;
                em.esc_panel.setVisible(true);
                //Ist dafür da das es als overlay angezeigt wird. klappt aber noch nicht ganz
                Rectangle r = window.getBounds();
                window.setLayout(null);
                window.add(em.esc_panel);
                em.menu_button.setVisible(!nmenu);
                em.esc_panel.setBounds(0, 0, r.width, r.height);
                SwingUtilities.updateComponentTreeUI(window);
                System.out.println("open");
            }
        }
    }


}
