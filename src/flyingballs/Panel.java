package flyingballs;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @authors Korn, Andreas Manuel & García Socias, Alberto 
 */
public class Panel extends JPanel{
    
    ArrayList<Ball> balls = new ArrayList<Ball>();
    
    @Override
    public void paint(Graphics graph){
        super.paint(graph);
        setSize(1000,500);
        for (Ball ball : balls){
            graph.drawOval(ball.getPosition().getX(), ball.getPosition().getY(), ball.getDiameter(), ball.getDiameter());
            graph.setColor(Color.RED);
            graph.fillOval(ball.getPosition().getX(), ball.getPosition().getY(), ball.getDiameter(), ball.getDiameter());
        }
    }
    
    public void move(){
        for (Ball ball : balls){
            if(ball.getPosition().getX() + ball.getAngle().getX() < 0){
                ball.getAngle().setX(1);
            } else if (ball.getPosition().getX() + ball.getAngle().getX() > 1000 - ball.getDiameter()){
                ball.getAngle().setX(-1);
            } else if(ball.getPosition().getY() + ball.getAngle().getY() < 0){
                ball.getAngle().setY(1);
            } else if (ball.getPosition().getY() + ball.getAngle().getY() > 500 - ball.getDiameter()){
                ball.getAngle().setY(-1);
            }
            ball.getPosition().add(ball.getAngle().getX(), ball.getAngle().getY() );   
        }
    }
    
    public void addBall(Ball ball) {
        balls.add(ball);
    }
}
