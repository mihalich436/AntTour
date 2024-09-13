import java.util.List;

public class Main {
    private static final int simIterNum = 100000,
            envRadius = 10,
            envTownNum = 6;
    private static final double pheromoneMarkPower = 10.0,
            pheromoneWeight = 1.0,
            defaultWeight = 1.0,
            pheromoneDecreaseCoef = 0.2;

    public static void main(String[] args) {
        //set global parameters
        Road.setPheromoneDecreaseCoef(pheromoneDecreaseCoef);
        Ant.setPheromoneParameters(pheromoneMarkPower, pheromoneWeight, defaultWeight);
        Environment environment = Environment.createRound(envRadius, envTownNum);
        List<Ant> ants = Ant.spawn(environment.getTowns());
        Simulation simulation = new Simulation(environment, ants);
        simulation.run(simIterNum);

        for(Ant ant : ants){
            System.out.println(ant + ": " + ant.routeToString(envTownNum * 2));
        }
    }
}
