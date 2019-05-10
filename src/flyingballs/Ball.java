package flyingballs;

import java.util.Arrays;

/**
 *
 * @authors Korn, Andreas Manuel & García Socias, Alberto 
 */
public class Ball {
    private final int diameter = 35;
    private final String shape = "Circle";
    private float[] color;
    private int speed;
    private int acceleration;
    private final Vector position;
    
    public int getDiameter() {
        return diameter;
    }

    public String getShape() {
        return shape;
    }

    public float[] getColor() {
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
        this.position = new Vector(0,0);
        this.speed = 5;
        this.acceleration = 1;
        //float color[] = {(float) Math.random(),(float) Math.random(),(float) Math.random()};
        //this.color = Arrays.copyOf(color, 3);
        
        
    }
    
    private float randomizeRGBNumber(){
        return (float) Math.random();
    }
}
