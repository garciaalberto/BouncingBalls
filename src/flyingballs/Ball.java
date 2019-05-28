package flyingballs;

/**
 *
 * @authors Korn, Andreas Manuel & García Socias, Alberto 
 */
public class Ball {
    private final int DIAMETER = 35;
    private final String SHAPE = "Circle";
    private final int[] COLOR = new int[3];
    private Vector speed;
//    private double acceleration;
    private Vector position, acceleration;

    public Vector getAcceleration() {
        return acceleration;
    }
    
    public int getDiameter() {
        return DIAMETER;
    }

    public int[] getColor() {
        return COLOR;
    }

    public Vector getSpeed() {
        return speed;
    }
    
    public void setSpeed(double X, double Y){
        this.speed.add(X, Y);
    }

//    public double getAcceleration() {
//        return acceleration;
//    }

    public Vector getPosition() {
        return position;
    }
    
    public void setPosition(Vector position){
        this.position = position;
    }
    
    public void accelerate(){
        this.speed.add(acceleration);
    }
    
//    public void setSpeed(Vector speed) {
//        this.speed = speed;
//    }

//    public void setAcceleration(double acceleration) {
//        this.acceleration = acceleration;
//    }
    
    public Ball(){
        this.position = new Vector((int)Math.floor(Math.random() * 1000-this.DIAMETER), (int)Math.floor(Math.random() * 500-this.DIAMETER));
        this.speed = new Vector(1, 10);
//        this.acceleration = 1;
        this.acceleration = new Vector(0, 1);
        COLOR[0] = (int)Math.floor(Math.random() * 256);
        COLOR[1] = (int)Math.floor(Math.random() * 256);
        COLOR[2] = (int)Math.floor(Math.random() * 256);
    }
}
