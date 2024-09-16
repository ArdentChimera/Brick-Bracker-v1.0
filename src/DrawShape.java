import javax.swing.*;
import java.awt.*;

class Brick extends JComponent {
    Brick(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.gray);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}

class DrawShape extends JComponent {
   DrawShape() {
       this.setLayout(null);

       for (int row = 0; row < 5; row++){
           for(int col = 0; col < 14; col++) {
               Brick brick = new Brick(20 + col * 90, 10 + row * 30, 70, 20);
               this.add(brick);
           }
       }
   }

//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        int x = 20;
//        int y = 10;
//        int width = 20;
//        int height = 20;
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setColor(Color.gray);
//        for (int j = 1; j <= 10; j++){
//            g2.drawRect(x, y, width, height);
//            g2.fillRect(x, y, width, height);
//            for (int i = 1; i <= 50; i++) {
//                g2.drawRect(x, y, width, height);
//                g2.fillRect(x, y, width, height);
//                x += 23;
//            }
//
//            x = 20;
//            y += 23;
//        }
//    }
}
