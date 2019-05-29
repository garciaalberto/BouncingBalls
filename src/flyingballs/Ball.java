package flyingballs;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @authors Korn, Andreas Manuel & García Socias, Alberto 
 */
public class Ball {
    private final int DIAMETER = 35;
    private final String SHAPE = "Circle";
    private final int[] COLOR = new int[3];
    private Vector speed;
    private Vector position, acceleration;

    public Vector getAcceleration() {
        return acceleration;
    }
    
    public void setAcceleration(Vector acceleration){
        this.acceleration = acceleration;
    }
    
    public void setAcceleration(double X, double Y){
        this.acceleration.setX(X);
        this.acceleration.setY(Y);
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

    public Vector getPosition() {
        return position;
    }
    
    public void setPosition(Vector position){
        this.position = position;
    }
    
    public void setPosition(double X, double Y){
        this.position.setX(X);
        this.position.setY(Y);
    }
    
    public void accelerate(){
        this.speed.add(acceleration);
    }
    
    public void paintBall(Graphics graph){
        graph.setColor(Color.BLACK);
        graph.drawOval((int) this.getPosition().getX(), (int) this.getPosition().getY(), this.getDiameter(), this.getDiameter());
        graph.setColor(new Color(this.getColor()[0], this.getColor()[1], this.getColor()[2]));
        graph.fillOval((int) this.getPosition().getX(), (int) this.getPosition().getY(), this.getDiameter(), this.getDiameter());
    }
    
    public Ball(){
        this.position = new Vector((int)Math.floor(Math.random() * (1000-this.DIAMETER)), (int)Math.floor(Math.random() * (500-this.DIAMETER)));
        double xSpd = Math.random()*2-1;
        if(xSpd<0){
            xSpd = -1;
        } else {
            xSpd = 1;
        }
        this.speed = new Vector(xSpd, 1);
        this.acceleration = new Vector(0, 1);
        COLOR[0] = (int)Math.floor(Math.random() * 256);
        COLOR[1] = (int)Math.floor(Math.random() * 256);
        COLOR[2] = (int)Math.floor(Math.random() * 256);
    }
}
