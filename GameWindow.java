import javax.swing.*;

public class GameWindow extends JFrame{

    public GameWindow() {
        setTitle("Game Window");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new GameWindow();
    }
}