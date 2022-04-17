import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class UI_start  extends JFrame implements KeyListener{
    //Main wie aus C, der Start eines Programms der die erste Klasse aufruft
    JFrame window = new JFrame();
    UI_menu mm = new UI_menu();
    UI_EscMenu em = new UI_EscMenu();

    //DragPanel dp =new DragPanel();
    IngameShop is = new IngameShop();
    //MenuBar mb = new MenuBar();
    mapGen mg1;


    public boolean nstart;
    public boolean escopen;


    public JPanel start_panel;
    private JButton start_button;
    private JButton exit_button;
    int map = 0;


    public UI_start(){
        //TODO dont work ||WAT?
        ImageIcon img = new ImageIcon("pictures_map/icon.png");
        window.setIconImage(img.getImage());

        //window eigenschaften
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setResizable(false);

        window.setTitle("Tower Defense");
        window.setLocationRelativeTo(null);
        //
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
                //TODO Switch
                if(o == mm.Map1 ){
                    setMap(1);
                    System.out.println("Sie haben die Map "+map + " gewaehlt" );
                    //Versuch 0pointerExc zu umgehen (geht net)
                    //mg1.loadMap(map);
                    mapAuswahl();

                }else if (o == mm.Map2) {
                    setMap(2);
                    System.out.println("Sie haben die Map "+map + " gewaehlt" );
                    mapAuswahl();

                }else if (o == mm.Map3){
                    setMap(3);
                    System.out.println("Sie haben die Map "+map + " gewaehlt" );
                    mapAuswahl();

                }else if (o == start_button){
                    System.out.println("Start");
                    start_panel.setVisible(false);
                    window.setContentPane(mm.menu_panel);
                    mm.menu_panel.setVisible(true);
                    nstart = true;
                }else if (o == exit_button){
                    System.out.println("Exit");
                    System.exit(0);
                //ESC Menü Buttons
                }else if (o == em.weiter_button){
                    //TODO Continue machen momentan nicht Funktionabel
                    System.out.println("Continue");
                    em.esc_panel.setVisible(false);
                    em.esc_panel.setFocusable(false);
                    SwingUtilities.updateComponentTreeUI(window);

                    // Werden Entitys gespeichert ??! //TODO TEsten
                    UI_gamepanel gamePanel = new UI_gamepanel(map);
                    window.setContentPane(gamePanel);
                    em.esc_panel.setVisible(false);
                    em.esc_panel.setFocusable(false);
                    gamePanel.startGameThread();
                    gamePanel.repaint();
                }
                else if (o == em.exit_button){
                    System.out.println("exitovermenu");
                    System.exit(0);
                }
                else if (o == em.menu_button){
                    System.out.println("Main menu");
                    em.esc_panel.setVisible(false);
                    window.setContentPane(start_panel);
                    start_panel.setVisible(true);
                    nstart = false;
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

    }


    public void mapAuswahl(){

        //BaloonsBewegen bb = new BaloonsBewegen();
        mm.menu_panel.setVisible(false);
        UI_gamepanel gamePanel = new UI_gamepanel(map);
        window.setContentPane(gamePanel);
        gamePanel.startGameThread();
        gamePanel.repaint();

        // Rectangle r = window.getBounds();
        window.setLayout(null);
        window.add(is.IngameShop);
        is.IngameShop.setBounds(1631,0,300,1100);
        is.IngameShop.setVisible(true);



    }
    public void setMap(int map){
        this.map = map;
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
                System.out.println("asda");
                is.IngameShop.setVisible(true);
            }
            else {
                escopen = true;
                em.esc_panel.setVisible(true);
                //em.esc_panel.setFocusable(true);
                //em.esc_panel.requestFocusInWindow();

                //Ist dafür da das es als overlay angezeigt wird. klappt aber noch nicht ganz
                Rectangle r = window.getBounds();
                window.setLayout(null);
                //em.esc_panel.setLayout(null);
                window.add(em.esc_panel);
                em.esc_panel.setBounds(0, 0, r.width, r.height);
                is.IngameShop.setVisible(false);
                //window.setContentPane(em.esc_panel);
                SwingUtilities.updateComponentTreeUI(window);

                //get actual panal und dan später das alte auftrufen bei weiter

            }
        }
    }


}
