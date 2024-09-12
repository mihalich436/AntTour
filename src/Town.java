import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Town extends PlaneObject {
    List<Road> roads = new ArrayList<>();

    public Town(double x, double y) {
        super(x, y);
    }

    public void addRoad(Road road){
        roads.add(Objects.requireNonNull(road));
    }

    public List<Road> getRoads() {
        return roads;
    }
}
