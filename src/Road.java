import java.util.Objects;

public class Road {
    private static double pheromoneDecreaseCoef = 1.0;
    private final Town start;
    private final Town end;
    private double pheromone;
    private final double length;

    public Road(Town start, Town end) {

        this.start = Objects.requireNonNull(start);
        this.end = Objects.requireNonNull(end);
        start.addRoad(this);
        end.addRoad(this);
        this.length = Math.sqrt(Math.pow(end.getX()-start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
    }

    public double getLength(){
        return length;
    }

    public void decreasePheromone(double time){
        pheromone -= time * pheromoneDecreaseCoef;
        if (pheromone < 0) pheromone = 0;
    }

    public double getPheromone() {
        return pheromone;
    }

    public void addPheromone(double pheromone) {
        this.pheromone += pheromone;
    }

    public Town getOtherEnd(Town town){
        return start.equals(town) ? end : start;
    }

    public static void setPheromoneDecreaseCoef(double pheromoneDecreaseCoef) {
        Road.pheromoneDecreaseCoef = pheromoneDecreaseCoef;
    }
}
