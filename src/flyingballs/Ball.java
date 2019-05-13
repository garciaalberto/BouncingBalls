package flyingballs;

/**
 *
 * @authors Korn, Andreas Manuel & García Socias, Alberto 
 */
public class Ball {
    private final int diameter = 35;
    private final String shape = "Circle";
    private final int[] color = new int[3];
    private int speed;
    private int acceleration;
    private final Vector position, angle;

    public Vector getAngle() {
        return angle;
    }
    
    public int getDiameter() {
        return diameter;
    }

    public String getShape() {
        return shape;
    }

    public int[] getColor() {
        return color;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public Vector getPosition() {
        return position;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }
    
    public Ball(){
        this.position = new Vector((int)Math.floor(Math.random() * 1000-this.diameter), (int)Math.floor(Math.random() * 500-this.diameter));
        this.speed = 5;
        this.acceleration = 1;
        this.angle = new Vector(1, 1);
        color[0] = (int)Math.floor(Math.random() * 256);
        color[1] = (int)Math.floor(Math.random() * 256);
        color[2] = (int)Math.floor(Math.random() * 256);
    }
}
