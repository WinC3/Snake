import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class Snake {
    public static final int SEGMENT_SIZE = 20;

    private List<Point> snakeSegments;
    private Direction direction;

    public Snake() {
        direction = Direction.RIGHT;
        snakeSegments = new ArrayList<>();
        snakeSegments.add(new Point(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2));
    }
    
    public void move() {
        Point head = snakeSegments.get(0);
        Point newHead = new Point(head.x + direction.x * SEGMENT_SIZE, head.y + direction.y * SEGMENT_SIZE);
        snakeSegments.add(0, newHead);
        snakeSegments.remove(snakeSegments.size() - 1);
    }

    public void grow() {
        Point tail = snakeSegments.get(snakeSegments.size() - 1);
        snakeSegments.add(tail);
    }

    public List<Point> getSnakeSegments() {
        return snakeSegments;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
