package bouncingballs;

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
            if(ball.getPosition().getY() == 500-ball.getDiameter()){
                ball.setSpeed(-ball.getSpeed());
            }
            if(ball.getPosition().getY() == 200+ball.getDiameter() && ball.getSpeed() < 0){
                ball.setSpeed(-ball.getSpeed());
            }
            ball.getPosition().add(0, ball.getSpeed());
            
        }
    }
    
    public void addBall(Ball ball) {
        balls.add(ball);
    }
}
