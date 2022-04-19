import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class UI_start  extends JFrame implements KeyListener,ActionListener{
    //Main wie aus C, der Start eines Programms der die erste Klasse aufruft
    JFrame window = new JFrame();
    UI_menu mm = new UI_menu();
    UI_EscMenu em = new UI_EscMenu();


    public JPanel start_panel;
    private JButton start_button;
    private JButton exit_button;
    int map = 0;

    Timer timer = new Timer(50,this);



    public UI_start(){
        //TODO dont work
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
        //window.setSize(1920,  1080);
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
                if(o == mm.Map1 ){
                    setMap(1);
                    System.out.println("Sie haben die Map "+map + " gewählt" );
                    //Versuch 0pointerExc zu umgehen (geht net)
                    //mg1.loadMap(map);
                    mapAuswahl();
                    //Aufbau zeit vor dem Spawnen der GEgner
                    timer.start();

                }else if (o == mm.Map2) {
                    setMap(2);
                    System.out.println("Sie haben die Map "+map + " gewählt" );
                    mapAuswahl();
                    //Aufbau zeit vor dem Spawnen der GEgner
                    timer.start();

                }else if (o == mm.Map3){
                    setMap(3);
                    System.out.println("Sie haben die Map "+map + " gewählt" );
                    mapAuswahl();
                    //Aufbau zeit vor dem Spawnen der GEgner
                    timer.start();

                }else if (o == start_button){
                    System.out.println("Start");
                    start_panel.setVisible(false);
                    window.setContentPane(mm.menu_panel);
                    mm.menu_panel.setVisible(true);
                }else if (o == exit_button){
                    System.out.println("Exit");
                    System.exit(0);
                //ESC Menü Buttons
                }else if (o == em.weiter_button){
                    System.out.println("Continue");
                    em.esc_panel.setVisible(false);
                    em.esc_panel.setFocusable(false);
                    SwingUtilities.updateComponentTreeUI(window);

                    // Werden Entitys gespeichert ??! //TODO TEsten
                    UI_gamepanel gamePanel = new UI_gamepanel(map);
                    window.setContentPane(gamePanel);
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

                }


            }
        };

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
        if(keyEvent.getKeyCode() == 27){
            em.esc_panel.setVisible(true);
            em.esc_panel.setFocusable(true);
            em.esc_panel.requestFocusInWindow();

            //Ist dafür da das es als overlay angezeigt wird. klappt aber noch nicht ganz
            //window.add(em.esc_panel);
            window.setContentPane(em.esc_panel);
            SwingUtilities.updateComponentTreeUI(window);

            //get actual panal und dan später das alte auftrufen bei weiter


        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Idee fürs Spawnen
        if(timer.isRunning()){
            int gegnerAnzahl = 10;
            for(int i = 0; i <=  gegnerAnzahl;i++){
                // Gegner gg = new Gegner();
                //gg.draw();
            }

        }
    }
}
