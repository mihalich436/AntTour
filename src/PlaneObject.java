public abstract class PlaneObject {
    private double x;
    private double y;

    public PlaneObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void move(double dx, double dy){
        this.x += dx;
        this.y += dy;
    }

    public void set(float x, float y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
