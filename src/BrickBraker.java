import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BrickBraker extends JFrame implements KeyListener, ActionListener {
    DrawPlayer player;
    DrawShape bricks;
    DrawBall ball;
    private Timer timer;
    private JButton startButton;
    private boolean gameRunning = false;
    private int playerSpeed = 15;
    private int playerWidth = 150;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    BrickBraker() {
        this.setSize(1200, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1200, 720));
        this.setLayout(null);
        this.getContentPane().setBackground(Color.black);
        this.addKeyListener(this);
        this.setFocusable(true); // Ensure the frame is focusable for key events

        bricks = new DrawShape();
        bricks.setBounds(0, 0, 1200, 620);
        this.add(bricks);

        player = new DrawPlayer();
        player.setBounds(525, 650, playerWidth, 15);
        this.add(player);

        ball = new DrawBall();
        this.add(ball);

        startButton = new JButton("Start Game");
        startButton.setBounds(500, 300, 200, 50);
        startButton.setFocusPainted(false);
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.addActionListener(e -> startGame());
        this.add(startButton);

        this.setVisible(true);
    }

    private void startGame() {
        if (!gameRunning) {
            gameRunning = true;
            startButton.setVisible(false);
            if (timer != null) {
                timer.stop(); // Stop the existing timer if any
            }
            timer = new Timer(10, this);
            timer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameRunning) {
            // Move the ball every frame
            ball.moveBall();

            // Detect collision between ball and player
            checkPaddleCollision();

            // Detect collision between ball and bricks
            checkBrickCollision();

            // Check if ball falls out of bounds (optional game over condition)
            if (ball.getY() + ball.getHeight() >= this.getHeight()) {
                gameRunning = false;
                timer.stop();
                resetGame();
            }

            // Move player based on key states
            movePlayer();
        }
    }

    private void movePlayer() {
        if (leftPressed) {
            int newX = player.getX() - playerSpeed;
            if (newX >= 0) {
                player.setLocation(newX, player.getY());
            }
        }
        if (rightPressed) {
            int newX = player.getX() + playerSpeed;
            if (newX + player.getWidth() <= this.getWidth()) {
                player.setLocation(newX, player.getY());
            }
        }
    }

    private void checkPaddleCollision() {
        if (ball.getBounds().intersects(player.getBounds())) {
            ball.reverseYDirection();  // Ball bounces off the paddle
        }
    }

    private void checkBrickCollision() {
        Component[] components = bricks.getComponents();
        for (Component brick : components) {
            if (ball.getBounds().intersects(brick.getBounds())) {
                ball.reverseYDirection();  // Ball bounces off the brick
                bricks.remove(brick);  // Remove the brick from the game
                bricks.repaint();  // Redraw without the brick
                break;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not needed for movement
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                leftPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = false;
                break;
        }
    }

    private void resetGame() {
        // Reset game state
        gameRunning = false;
        startButton.setVisible(true);

        // Reset ball position and velocity
        ball.resetBall();

        // Reset player position
        player.setLocation(525, 620);

        // Clear and re-add bricks
        bricks.removeAll();
        bricks.repaint();

        // Re-add the bricks
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 14; col++) {
                Brick brick = new Brick(20 + col * 90, 10 + row * 30, 70, 20);
                bricks.add(brick);  // Add each brick to the panel
            }
        }
        bricks.repaint();
    }
}
