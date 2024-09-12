import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ant{
    private static final double pheromoneMarkPower = 1.0,
            pheromoneWeight = 1.0,
            defaultWeight = 1.0;

    private Road road;
    private Town fromTown;
    private double roadProgress;

    public Ant(Town town) {
        this.fromTown = town;
    }

    public static List<Ant> spawn(List<Town> towns){
        List<Ant> ants = new ArrayList<>();
        for (Town town : towns){
            ants.add(new Ant(town));
        }
        return ants;
    }

    public void chooseRoad(){
        List<Road> roads = Objects.requireNonNull(fromTown).getRoads();
        road = getRandomRoad(roads);
        road.addPheromone(pheromoneMarkPower);
    }

    private Road getRandomRoad(List<Road> roads){
        double weightSum = roads.stream()
                .map(r -> getRoadWeight(r.getPheromone()))
                .reduce(Double::sum)
                .orElse(0.0);
        double randomWeight = Math.random() * weightSum;
        for (Road r : roads){
            randomWeight -= getRoadWeight(r.getPheromone());
            if (randomWeight <= 0.0) return r;
        }
        return roads.get(roads.size() - 1);
    }

    private double getRoadWeight(double pheromone){
        return pheromone * pheromoneWeight + defaultWeight;
    }
}
