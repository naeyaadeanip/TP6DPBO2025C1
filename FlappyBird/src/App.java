import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");

        // Perbaikan: harus pakai instance 'frame', bukan 'JFrame' atau 'Frame'
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        FlappyBird flappyBird = new FlappyBird();
        frame.add(flappyBird);
        frame.pack(); // sesuaikan ukuran dengan preferredSize panel
        frame.setVisible(true);
    }
}