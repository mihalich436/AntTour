import java.util.Comparator;
import java.util.List;

public class Simulation {
    private final Environment environment;
    private final List<Ant> ants;

    public Simulation(Environment environment, List<Ant> ants) {
        this.environment = environment;
        this.ants = ants;
    }

    public void run(int iterNum){
        System.out.println("Run simulation for " + iterNum + " iterations.");
        List<Road> roads = environment.getRoads();

        ants.forEach(Ant::chooseRoad);
        ants.forEach(Ant::markRoad);

        for (int i=0; i<iterNum; i++){
            Ant ant = findNextAnt();
            double distance = ant.distanceToGo();
            roads.forEach(r -> r.decreasePheromone(distance));
            ants.forEach(a -> a.addRoadProgress(distance));
            ant.finishRoad();
            ant.chooseRoad();
            ant.markRoad();
        }
    }

    private Ant findNextAnt(){
        return ants.stream()
                .min(Comparator.comparingDouble(Ant::distanceToGo))
                .orElse(null);
    }
}
