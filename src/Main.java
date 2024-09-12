import java.util.List;

public class Main {
    private static final int simIterNum = 10,
            envRadius = 10,
            envTownNum = 6;

    public static void main(String[] args) {
        Environment environment = Environment.createRound(envRadius, envTownNum);
        List<Ant> ants = Ant.spawn(environment.getTowns());
        Simulation simulation = new Simulation(environment, ants);
        simulation.run(simIterNum);
    }
}
