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
        Point foodLocation = new Point((int)(Math.random() * GamePanel.WIDTH), (int)(Math.random() * GamePanel.HEIGHT));
        foodLocations.add(foodLocation);
    }

    public void removeFood(Point foodLocation) {
        foodLocations.remove(foodLocation);
    }

    public List<Point> getFoodLocations() {
        return foodLocations;
    }
}
