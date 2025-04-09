import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    int frameWidth = 360;
    int frameHeight = 640;

    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    int playerStartPosX = frameWidth / 8;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;
    Player player;

    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    ArrayList<Pipe> pipes;

    Timer gameLoop;
    Timer pipesCooldown;

    int gravity = 1;

    JLabel scoreLabel;
    int score = 0;

    boolean isGameOver = false;

    public FlappyBird() {
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setBackground(Color.blue);

        setFocusable(true);
        addKeyListener(this);
        requestFocusInWindow();

        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<>();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();

        pipesCooldown = new Timer(1500, e -> placePipes());
        pipesCooldown.start();

        setLayout(null);
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setBounds(20, 20, 150, 30);
        add(scoreLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);
        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for (Pipe pipe : pipes) {
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }
    }

    public void move() {
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for (Pipe pipe : pipes) {
            pipe.setPosX(pipe.getPosX() - 3);
        }
    }

    public void placePipes() {
        int randomPipePosY = (int) (pipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPipePosY, pipeWidth, pipeHeight, upperPipeImage);
        Pipe lowerPipe = new Pipe(pipeStartPosX, randomPipePosY + pipeHeight + openingSpace, pipeWidth, pipeHeight, lowerPipeImage);

        pipes.add(upperPipe);
        pipes.add(lowerPipe);
    }

    public boolean isColliding(Pipe pipe) {
        Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
        Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());
        return playerRect.intersects(pipeRect);
    }

    public boolean isPlayerOutOfBounds() {
        return player.getPosY() + player.getHeight() >= frameHeight;
    }

    public void restartGame() {
        score = 0;
        scoreLabel.setText("Score: " + score);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);
        pipes.clear();
        isGameOver = false;
        pipesCooldown.restart();
        gameLoop.restart();
        requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGameOver) return;

        move();
        repaint();

        for (Pipe pipe : pipes) {
            if (isColliding(pipe) || isPlayerOutOfBounds()) {
                isGameOver = true;
                gameLoop.stop();
                pipesCooldown.stop();
                JOptionPane.showMessageDialog(this, "Game Over!\nSkor: " + score, "Game Over", JOptionPane.INFORMATION_MESSAGE);
                requestFocusInWindow(); // ambil fokus biar bisa baca key R atau Enter
                return; //
            }
        }

        for (int i = 0; i < pipes.size(); i += 2) {
            Pipe upperPipe = pipes.get(i);
            if (!upperPipe.isPassed() && upperPipe.getPosX() + upperPipe.getWidth() < player.getPosX()) {
                upperPipe.setPassed(true);
                score++;
                scoreLabel.setText("Score: " + score);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (!isGameOver) {
            if (key == KeyEvent.VK_SPACE) {
                player.setVelocityY(-10);
            }
        } else {
            if (key == KeyEvent.VK_R || key == KeyEvent.VK_ENTER) {
                isGameOver = false;
                restartGame();
            }
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Flappy Bird");
            FlappyBird flappyBird = new FlappyBird();
            frame.add(flappyBird);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(360, 640);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }
}