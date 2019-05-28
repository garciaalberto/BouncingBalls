package flyingballs;

/**
 *
 * @authors Korn, Andreas Manuel & García Socias, Alberto 
 */
public class Ball {
    private final int DIAMETER = 35;
    private final String SHAPE = "Circle";
    private final int[] COLOR = new int[3];
    private double speed;
    private double acceleration;
    private final Vector POSITION, ANGLE;

    public Vector getAngle() {
        return ANGLE;
    }
    
    public int getDiameter() {
        return DIAMETER;
    }

    public int[] getColor() {
        return COLOR;
    }

    public double getSpeed() {
        return speed;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public Vector getPosition() {
        return POSITION;
    }
    
    public void setSpeed(double speed) {
        this.speed = this.speed*speed;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }
    
    public Ball(){
        this.POSITION = new Vector((int)Math.floor(Math.random() * 1000-this.DIAMETER), (int)Math.floor(Math.random() * 500-this.DIAMETER));
        this.speed = 10;
        this.acceleration = 1;
        this.ANGLE = new Vector(1, 1);
        COLOR[0] = (int)Math.floor(Math.random() * 256);
        COLOR[1] = (int)Math.floor(Math.random() * 256);
        COLOR[2] = (int)Math.floor(Math.random() * 256);
    }
}
