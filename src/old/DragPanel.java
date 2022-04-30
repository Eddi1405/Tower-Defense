package old;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DragPanel extends JPanel {
    ImageIcon tower1 = new ImageIcon(this.getClass().getResource("/pictures_map/fire.png"));
    Image tower = tower1.getImage();
    final int width = tower1.getIconWidth();
    final int height = tower1.getIconHeight();

    Point imageCorner;
    Point prevPt;

    //Mausklick wird hier geprueft
    DragPanel() {
        //is.overlay.setVisible(true);
        imageCorner = new Point(0, 0);
        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
        this.addMouseListener(clickListener);
        this.addMouseMotionListener(dragListener);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(tower, (int) imageCorner.getX(), (int) imageCorner.getY(),100,100,null);
    }

    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            prevPt = e.getPoint();
        }
    }

    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            Point currentPt = e.getPoint();
            imageCorner.translate(
                    (int) (currentPt.getX() - prevPt.getX()),
                    (int) (currentPt.getY() - prevPt.getY())
            );
            prevPt = currentPt;
            repaint();

        }
    }
}
