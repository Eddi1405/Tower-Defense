import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.EventListener;

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

    public UI_start(){

        //window eigenschaften
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Tower Defense");
        window.setLocationRelativeTo(null);
        window.setFocusable(true);
        window.requestFocusInWindow();
        window.setSize(768, 576);
        start_panel.setPreferredSize(new Dimension(768, 576));
        window.setContentPane(start_panel);
        window.setVisible(true);



        //Aktiviert den KeyListener Für das den JFrame Window (ab da fängt er die Signale der Tastatur auf)
        window.addKeyListener(this);
/*
        //Buttons
        mm.Map1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map = 1;
                System.out.println(map);
                mapAuswahl();
            }
        });
        mm.Map2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map = 2;
                mapAuswahl();
            }
        });
        mm.Map3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map = 3;
                mapAuswahl();
            }
        });
        start_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start");
                start_panel.setVisible(false);
                window.setContentPane(mm.panel2);
            }
        });
        exit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exit");
                System.exit(0);
            }
        });*/
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
                    window.setContentPane(mm.panel2);

                }else if (o == exit_button){
                    System.out.println("Exit");
                    System.exit(0);
                //ESC Menü Buttons
                }else if (o == em.continueButton){
                    //TODO Continue machen momentan nicht Funktionabel
                    System.out.println("Continue");
                }
                else if (o == em.exitButton){
                    System.out.println("exitovermenu");
                    System.exit(0);
                }
                else if (o == em.mainMenuButton){
                    System.out.println("Main menu");
                    window.dispose();//Schließt das window
                    UI_start UInew = new UI_start();
                }


            }
        };

        mm.Map1.addActionListener(buttonListener);
        mm.Map2.addActionListener(buttonListener);
        mm.Map3.addActionListener(buttonListener);
        exit_button.addActionListener(buttonListener);
        start_button.addActionListener(buttonListener);
        em.continueButton.addActionListener(buttonListener);
        em.exitButton.addActionListener(buttonListener);
        em.mainMenuButton.addActionListener(buttonListener);
    }


    public static void main(String[] args) {
        UI_start Frame = new UI_start();
        System.out.println("-----Beginn-----");
        Frame.addKeyListener(Frame);
    }


    public void mapAuswahl(){
        UI_gamepanel gamePanel = new UI_gamepanel();
        //BaloonsBewegen bb = new BaloonsBewegen();
        mm.panel2.setVisible(false);
        window.setContentPane(gamePanel);
        gamePanel.startGameThread();
        gamePanel.repaint();

    }
    public void setMap(int map){
        this.map = map;
    }

    public int getMap(){
        return map;
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

            em.panel1.setVisible(true);
            em.panel1.setFocusable(true);
            em.panel1.requestFocusInWindow();
            window.setContentPane(em.panel1);
            SwingUtilities.updateComponentTreeUI(window);


        }
    }


}
