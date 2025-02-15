import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class Food {
    public static final int MAX_FOOD_COUNT = 5;
    public static final int SPAWN_INTERVAL = 3000; // milliseconds

    private List<Point> foodLocations;

    public Food() {
        foodLocations = new ArrayList<>();
    }

    public void createFood() {
        Point foodLocation = new Point((int) ((Snake.SEGMENT_SIZE) * (int) (Math.random() * ((int) (GamePanel.WIDTH / Snake.SEGMENT_SIZE + 1)))),
        (int) ((Snake.SEGMENT_SIZE) * (int) (Math.random() * ((int) (GamePanel.HEIGHT / Snake.SEGMENT_SIZE + 1)))));
        foodLocations.add(foodLocation);
        System.out.println("Food created at: " + foodLocation);
    }

    public void removeFood(Point foodLocation) {
        foodLocations.remove(foodLocation);
    }

    public List<Point> getFoodLocations() {
        return foodLocations;
    }
}
