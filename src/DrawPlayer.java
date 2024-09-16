import javax.swing.*;
import java.awt.*;

class DrawPlayer extends JComponent {
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D player = (Graphics2D) g;
        player.setColor(Color.gray);
        player.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

}
