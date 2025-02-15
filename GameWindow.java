import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameWindow extends JFrame implements ActionListener{
    private static final int TIMER_DELAY = 100;

    private Timer timer;
    private GamePanel gamePanel;
    private int spawnIntervalCounter = 0;
    private boolean checkCollision = true;

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

        Point head;

        while (true) {
            head = snake.getSnakeSegments().get(0);
            System.out.println("Snake at: " + head);
            if (head.x < 0) {
                head.x = GamePanel.WIDTH - Snake.SEGMENT_SIZE;
            } else if (head.x >= GamePanel.WIDTH) {
                head.x = 0;
            } else if (head.y < 0) {
                head.y = GamePanel.HEIGHT - Snake.SEGMENT_SIZE;
            } else if (head.y >= GamePanel.HEIGHT) {
                head.y = 0;
            }
            snakeSegments = new ArrayList<>(snake.getSnakeSegments());
            foodLocations = new ArrayList<>(food.getFoodLocations());;
            Iterator<Point> iterator = foodLocations.iterator();
            while (iterator.hasNext()) {
                Point foodPoint = iterator.next();
                System.out.println("Food at: " + foodPoint);
                System.out.println("Snake at: " + snakeSegments.get(0));

                if (snakeSegments.get(0).equals(foodPoint)) {
                    snake.grow();
                    gameWindow.checkCollision = false;
                    iterator.remove();
                    food.removeFood(foodPoint);
                }
            }

            if (!gameWindow.checkCollision) {
                continue;
            }
            
            for (int i = 3; i < snakeSegments.size(); i++) {
                if (snakeSegments.get(0).equals(snakeSegments.get(i))) {
                    System.out.println("Game Over");
                    System.exit(0);
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
        checkCollision = true;
    }
}