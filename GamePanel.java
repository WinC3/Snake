import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener{
    public static final int HEIGHT = Snake.SEGMENT_SIZE * 30;
    public static final int WIDTH = Snake.SEGMENT_SIZE * 40;
    private Snake snake;
    private Food food;
    private int points = 0;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        snake = new Snake();
        food = new Food();
        requestFocusInWindow(); // Request focus in the constructor
    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow(); // Request focus when the component is added to its parent
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        for (int i = 0; i < snake.getSnakeSegments().size(); i++) {
            Point segment = snake.getSnakeSegments().get(i);
            g.fillRect(segment.x, segment.y, Snake.SEGMENT_SIZE, Snake.SEGMENT_SIZE);
        } // Paint snake
        
        g.drawString("Points: " + points, 10, HEIGHT - 10); // Paint score

        g.setColor(Color.RED);
        for (Point foodLocation : food.getFoodLocations()) {
            g.fillRect(foodLocation.x, foodLocation.y, Snake.SEGMENT_SIZE, Snake.SEGMENT_SIZE);
        } // Paint food
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

    public void incrementPoints() {
        points++;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                snake.setDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                snake.setDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                snake.setDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                snake.setDirection(Direction.RIGHT);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // do nothing
    }
}
