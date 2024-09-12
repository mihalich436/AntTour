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
    }
}
