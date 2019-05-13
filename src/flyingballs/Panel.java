package flyingballs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @authors Korn, Andreas Manuel & García Socias, Alberto 
 */
public class Panel extends JPanel{
    
    ArrayList<Ball> balls;
    private final int width = 1000;
    private final int height = 500;

    public Panel() {
        this.balls = new ArrayList<>();
    }
    
    @Override
    public void paint(Graphics graph){
        super.paint(graph);
        Graphics2D g2 = (Graphics2D) graph;
        g2.setStroke(new BasicStroke(5.0f));
        setSize(width,height);
        for (Ball ball : balls){
            graph.setColor(Color.BLACK);
            graph.drawOval(ball.getPosition().getX(), ball.getPosition().getY(), ball.getDiameter(), ball.getDiameter());
            graph.setColor(new Color(ball.getColor()[0],ball.getColor()[1],ball.getColor()[2]));
            graph.fillOval(ball.getPosition().getX(), ball.getPosition().getY(), ball.getDiameter(), ball.getDiameter());
        }
    }
    
    public void move(){
        for (Ball ball : balls){
            boolean walls = false;
            
            if(walls){
                if(ball.getPosition().getX() + ball.getAngle().getX() < 0){
                    ball.getAngle().setX(1);
                } else if (ball.getPosition().getX() + ball.getAngle().getX() > width - ball.getDiameter()){
                    ball.getAngle().setX(-1);
                } else if(ball.getPosition().getY() + ball.getAngle().getY() < 0){
                    ball.getAngle().setY(1);
                } else if (ball.getPosition().getY() + ball.getAngle().getY() > height - ball.getDiameter()){
                    ball.getAngle().setY(-1);
                }
                ball.getPosition().add(ball.getAngle().getX()*ball.getSpeed(), ball.getAngle().getY()*ball.getSpeed());
            } else{
                if(ball.getPosition().getX() + ball.getAngle().getX() < 0){
                    ball.getPosition().setX(width-ball.getPosition().getX());
                } else if (ball.getPosition().getX() + ball.getAngle().getX() > width - ball.getDiameter()){
                    ball.getPosition().setX(0);
                } else if(ball.getPosition().getY() + ball.getAngle().getY() < 0){
                    ball.getPosition().setY(height-ball.getPosition().getY());
                } else if (ball.getPosition().getY() + ball.getAngle().getY() > height - ball.getDiameter()){
                    ball.getPosition().setY(0);
                }
                ball.getPosition().add(ball.getAngle().getX()*ball.getSpeed(), ball.getAngle().getY()*ball.getSpeed());
            }
        }
    }
    
    public void addBall(Ball ball) {
        balls.add(ball);
    }
}
