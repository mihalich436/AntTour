import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Town extends PlaneObject {
    private final String name;
    List<Road> roads = new ArrayList<>();

    public Town(double x, double y, String name) {
        super(x, y);
        this.name = name;
    }

    public void addRoad(Road road){
        roads.add(Objects.requireNonNull(road));
    }

    public List<Road> getRoads() {
        return roads;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Town " + name;
    }
}
