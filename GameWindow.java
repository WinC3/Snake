import java.awt.BorderLayout;
import java.awt.List;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameWindow extends JFrame implements ActionListener{
    private static final int TIMER_DELAY = 100;

    private Timer timer;
    private GamePanel gamePanel;
    private int spawnIntervalCounter = 0;

    public GameWindow() {
        setTitle("Game Window");
        setSize(GamePanel.WIDTH, GamePanel.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);

        gamePanel = new GamePanel();
        setLayout(new BorderLayout());
        add(gamePanel, BorderLayout.CENTER);
        pack();
        timer = new Timer(TIMER_DELAY, this);
        timer.start();
    }

    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();

        Snake snake = gameWindow.gamePanel.getSnake();
        Food food = gameWindow.gamePanel.getFood();
        ArrayList<Point> snakeSegments;
        ArrayList<Point> foodLocations;
        while (true) {
            snakeSegments = (ArrayList<Point>) snake.getSnakeSegments();
            foodLocations = (ArrayList<Point>) food.getFoodLocations();
            for (Point foodPoint : foodLocations) {
                if (snakeSegments.get(0).equals(foodPoint)) {
                    snake.grow();
                    food.removeFood(foodPoint);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gamePanel.getSnake().move();
        spawnIntervalCounter += TIMER_DELAY;
        if (spawnIntervalCounter >= Food.SPAWN_INTERVAL) {
            if (gamePanel.getFood().getFoodLocations().size() < Food.MAX_FOOD_COUNT) {
                gamePanel.getFood().createFood();
            }
            spawnIntervalCounter %= Food.SPAWN_INTERVAL;
        }
        repaint();
    }
}