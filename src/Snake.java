import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class Snake {
    public static final int SEGMENT_SIZE = 20;

    private List<Point> snakeSegments;
    private Direction direction;
    private Direction queuedDirection = Direction.RIGHT;

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

    public void setDirection() {
        this.direction = queuedDirection;
    }

    public void queueDirection(Direction direction) {
        if (this.direction == Direction.UP && direction == Direction.DOWN ||
                this.direction == Direction.DOWN && direction == Direction.UP ||
                this.direction == Direction.LEFT && direction == Direction.RIGHT ||
                this.direction == Direction.RIGHT && direction == Direction.LEFT) {
        } else {
            this.queuedDirection = direction;
        } // Only change direction if it is not the opposite direction
    }
}
