import javax.swing.*;
import java.awt.*;

public class DrawBall extends JComponent {
    private int x, y;
    private int diameter = 20;
    private int xSpeed = 4;
    private int ySpeed = 4;

    DrawBall() {
        this.x = 590; // Initial x position (center)
        this.y = 300; // Initial y position (middle of the window)
        this.setBounds(x, y, diameter, diameter); // Set the bounds for the ball

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D ball = (Graphics2D) g;
        ball.setColor(Color.white);
        ball.fillOval(0, 0, diameter, diameter);
    }

    public void moveBall() {
        x += xSpeed;
        y += ySpeed;

        // Check for collision with the walls
        if (x <= 0 || x + diameter >= getParent().getWidth()) {
            xSpeed = -xSpeed;
        }
        if(y <= 0) {
            ySpeed = -ySpeed;
        }

        this.setLocation(x, y);
    }

    public void reverseYDirection() {
        ySpeed = -ySpeed;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, diameter, diameter);
    }

    public void resetBall() {
        this.x = 590; // Reset to initial x position
        this.y = 300; // Reset to initial y position
        this.xSpeed = 4; // Reset to initial x speed
        this.ySpeed = 4; // Reset to initial y speed
        this.setBounds(x, y, diameter, diameter); // Update bounds for new position
    }

}
