import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class UI_start  extends JFrame implements KeyListener{
    //Main wie aus C, der Start eines Programms der die erste Klasse aufruft
    JFrame window = new JFrame();
    UI_menu mm = new UI_menu();
    UI_EscMenu em = new UI_EscMenu();
    mapGen mg1;


    public JPanel start_panel;
    private JButton start_button;
    private JButton exit_button;
    int map = 0;
    UI_gamepanel gamePanel = new UI_gamepanel();

    public UI_start(){

        //window eigenschaften
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setResizable(false);

        window.setTitle("Tower Defense");
        window.setLocationRelativeTo(null);

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

                }else if (o == mm.Map2) {
                    setMap(2);
                    System.out.println("Sie haben die Map "+map + " gewählt" );
                    mapAuswahl();

                }else if (o == mm.Map3){
                    setMap(3);
                    System.out.println("Sie haben die Map "+map + " gewählt" );
                    mapAuswahl();

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
                    //TODO Continue machen momentan nicht Funktionabel
                    System.out.println("Continue");
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
            window.setContentPane(em.esc_panel);
            SwingUtilities.updateComponentTreeUI(window);

            //get actual panal und dan später das alte auftrufen bei weiter


        }
    }


}
