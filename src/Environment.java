import java.util.ArrayList;
import java.util.List;

public class Environment {
    private final List<Town> towns;
    private final List<Road> roads;

    private Environment(List<Town> towns, List<Road> roads) {
        this.towns = towns;
        this.roads = roads;
    }

    public static Environment createRound(double radius, int townNum){
        double angle = Math.PI / townNum;
        List<Town> townList = new ArrayList<>();
        List<Road> roadList = new ArrayList<>();
        for (int i=0; i<townNum; i++){
            Town town = new Town(
                    Math.cos(angle * i) * radius,
                    Math.sin(angle * i) * radius,
                    "(" + (i+1) + ")"
            );
            townList.add(town);
        }
        for (int i = 0; i < townList.size() - 1; i++){
            for (int j = i + 1; j < townList.size(); j++){
                roadList.add(new Road(townList.get(i), townList.get(j)));
            }
        }
        System.out.println("Created " + townList.size() + " towns and " + roadList.size() + " roads.");
        return new Environment(townList, roadList);
    }

    public List<Town> getTowns() {
        return towns;
    }

    public List<Road> getRoads() {
        return roads;
    }
}
