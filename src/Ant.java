import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ant{
    private static double pheromoneMarkPower = 1.0,
            pheromoneWeight = 1.0,
            defaultWeight = 1.0;

    private final String name;
    private Road road;
    private Town fromTown;
    private double roadProgress;
    private final List<Town> route = new ArrayList<>();

    public Ant(Town town, String name) {
        this.fromTown = town;
        this.name = name;
        this.route.add(town);
    }

    public static List<Ant> spawn(List<Town> towns){
        List<Ant> ants = new ArrayList<>();
        int i = 1;
        for (Town town : towns){
            ants.add(new Ant(town, "[" + (i++) + "]"));
        }
        return ants;
    }

    public void markRoad(){
        road.addPheromone(pheromoneMarkPower);
    }

    public void chooseRoad(){
        List<Road> roads = Objects.requireNonNull(fromTown).getRoads();
        road = getRandomRoad(roads, road);
    }

    private Road getRandomRoad(List<Road> roads, Road prevRoad){
        double weightSum = roads.stream()
                .map(r -> getRoadWeight(r.getPheromone()))
                .reduce(Double::sum)
                .orElse(0.0);
        //exclude previous road (ant came from it)
        if (Objects.nonNull(prevRoad))
            weightSum -= getRoadWeight(prevRoad.getPheromone());
        double randomWeight = Math.random() * weightSum;
        for (Road r : roads){
            if (r.equals(prevRoad)) continue;
            randomWeight -= getRoadWeight(r.getPheromone());
            if (randomWeight <= 0.0) return r;
        }
        return roads.get(roads.size() - 1);
    }

    private double getRoadWeight(double pheromone){
        return pheromone * pheromoneWeight + defaultWeight;
    }
    public double distanceToGo(){
        return road.getLength()-roadProgress;
    }
    public void addRoadProgress(double distance){
        roadProgress += distance;
    }

    private void resetRoadProgress(){
        roadProgress = 0;
    }

    public void finishRoad(){
        Town nextTown = road.getOtherEnd(fromTown);
        //System.out.println(this.toString() + " came from " + fromTown + " to " + nextTown);
        fromTown = nextTown;
        route.add(nextTown);
        resetRoadProgress();
    }

    //puts to string last <lastNum> towns, all if lastNum = 0
    public String routeToString(int lastNum){
        int start = route.size() - lastNum;
        if (start < 0 || lastNum == 0) start = 0;
        StringBuilder builder = new StringBuilder();
        for (int i=start; i<route.size()-1; i++){
            builder.append(route.get(i).getName());
            builder.append(" -> ");
        }
        builder.append(route.get(route.size()-1).getName());
        return builder.toString();
    }

    @Override
    public String toString() {
        return "Ant " + name;
    }

    public static void setPheromoneParameters(double pheromoneMarkPower, double pheromoneWeight, double defaultWeight){
        Ant.pheromoneMarkPower = pheromoneMarkPower;
        Ant.pheromoneWeight = pheromoneWeight;
        Ant.defaultWeight = defaultWeight;
    }
}
