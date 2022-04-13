import javax.swing.*;

public class IngameShop extends JFrame {

    DragPanel dp =new DragPanel();

    public IngameShop(){

        this.add(dp);
        this.setTitle("Shop Demo");
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
