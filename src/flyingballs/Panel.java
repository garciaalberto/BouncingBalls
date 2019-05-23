package flyingballs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @authors Korn, Andreas Manuel & García Socias, Alberto 
 */
public class Panel extends JPanel implements MouseMotionListener{
    
    ArrayList<Ball> balls;
    private final int WIDTH = 1000;
    private final int HEIGHT = 500;
    private int mouseX, mouseY;
    private boolean walls;
    private boolean followMouse;
    
    public void setWalls(boolean walls) {
        this.walls = walls;
    }

    public void setFollowMouse(boolean followMouse) {
        this.followMouse = followMouse;
    }

    public Panel(int balls) {
        this.followMouse = false;
        this.walls = true;
        this.balls = new ArrayList<>();
        addMouseMotionListener(this);
        Ball ball;
        for(int i = 0; i < balls; i++){
            ball = new Ball();
            addBall(ball);
        }
    }
    
    public void reset(int balls){
        this.balls.clear();
        Ball ball;
        for(int i = 0; i < balls; i++){
            ball = new Ball();
            addBall(ball);
        }
    }
    
    @Override
    public void paint(Graphics graph){
        super.paint(graph);
        Graphics2D g2 = (Graphics2D) graph;
        g2.setStroke(new BasicStroke(5.0f));
        setSize(WIDTH,HEIGHT);
        try{
            for (Ball ball : balls){
                graph.setColor(Color.BLACK);
                graph.drawOval((int)ball.getPosition().getX(), (int)ball.getPosition().getY(), ball.getDiameter(), ball.getDiameter());
                graph.setColor(new Color(ball.getColor()[0],ball.getColor()[1],ball.getColor()[2]));
                graph.fillOval((int)ball.getPosition().getX(), (int)ball.getPosition().getY(), ball.getDiameter(), ball.getDiameter());
            }
        }
        catch(Exception e){
            
        }
        
    }
    
    public void move(){
        for (Ball ball : balls){
            
            if(walls && !followMouse){
                if(ball.getPosition().getX() + ball.getAngle().getX() < 0){
                    ball.getAngle().setX(1);
                } else if (ball.getPosition().getX() + ball.getAngle().getX() > WIDTH - ball.getDiameter()){
                    ball.getAngle().setX(-1);
                } else if(ball.getPosition().getY() + ball.getAngle().getY() < 0){
                    ball.getAngle().setY(1);
                } else if (ball.getPosition().getY() + ball.getAngle().getY() > HEIGHT - ball.getDiameter()){
                    ball.getAngle().setY(-1);
                }
                ball.getPosition().add(ball.getAngle().getX()*ball.getSpeed(), ball.getAngle().getY()*ball.getSpeed());
            }
            if (!walls && !followMouse){
                if(ball.getPosition().getX() + ball.getAngle().getX() < 0){
                    ball.getPosition().setX(WIDTH-(int)ball.getPosition().getX());
                } else if (ball.getPosition().getX() + ball.getAngle().getX() > WIDTH - ball.getDiameter()){
                    ball.getPosition().setX(0);
                } else if(ball.getPosition().getY() + ball.getAngle().getY() < 0){
                    ball.getPosition().setY(HEIGHT-(int)ball.getPosition().getY());
                } else if (ball.getPosition().getY() + ball.getAngle().getY() > HEIGHT - ball.getDiameter()){
                    ball.getPosition().setY(0);
                }
                ball.getPosition().add(ball.getAngle().getX()*ball.getSpeed(), ball.getAngle().getY()*ball.getSpeed());
            }
            if(!walls && followMouse){
                if(ball.getPosition().getX() + ball.getAngle().getX() < 0){
                    ball.getPosition().setX(WIDTH-(int)ball.getPosition().getX());
                } else if (ball.getPosition().getX() + ball.getAngle().getX() > WIDTH - ball.getDiameter()){
                    ball.getPosition().setX(0);
                } else if(ball.getPosition().getY() + ball.getAngle().getY() < 0){
                    ball.getPosition().setY(HEIGHT-(int)ball.getPosition().getY());
                } else if (ball.getPosition().getY() + ball.getAngle().getY() > HEIGHT - ball.getDiameter()){
                    ball.getPosition().setY(0);
                }
                if(ball.getPosition().getX() > mouseX && ball.getPosition().getY() > mouseY){
                    ball.getPosition().substract(ball.getAngle().getX()*ball.getSpeed(), ball.getAngle().getY()*ball.getSpeed());
                }
                else if(ball.getPosition().getX() < mouseX && ball.getPosition().getY() < mouseY){
                    ball.getPosition().add(ball.getAngle().getX()*ball.getSpeed(), ball.getAngle().getY()*ball.getSpeed());
                }
                else if(ball.getPosition().getX() < mouseX && ball.getPosition().getY() > mouseY){
                    ball.getPosition().add(ball.getAngle().getX()*ball.getSpeed(), -ball.getAngle().getY()*ball.getSpeed());
                }
                else if(ball.getPosition().getX() > mouseX && ball.getPosition().getY() < mouseY){
                    ball.getPosition().add(ball.getSpeed()*-ball.getAngle().getX(), ball.getAngle().getY()*ball.getSpeed());
                }
            }
            if(walls && followMouse){
                if(ball.getPosition().getX() + ball.getAngle().getX() < 0){
                    ball.getAngle().setX(1);
                } else if (ball.getPosition().getX() + ball.getAngle().getX() > WIDTH - ball.getDiameter()){
                    ball.getAngle().setX(-1);
                } else if(ball.getPosition().getY() + ball.getAngle().getY() < 0){
                    ball.getAngle().setY(1);
                } else if (ball.getPosition().getY() + ball.getAngle().getY() > HEIGHT - ball.getDiameter()){
                    ball.getAngle().setY(-1);
                }
                if(ball.getPosition().getX() > mouseX && ball.getPosition().getY() > mouseY){
                    ball.getPosition().substract(ball.getAngle().getX()*ball.getSpeed(), ball.getAngle().getY()*ball.getSpeed());
                    ball.getAngle().setX(1);
                    ball.getAngle().setY(1);
                }
                else if(ball.getPosition().getX() < mouseX && ball.getPosition().getY() < mouseY){
                    ball.getPosition().add(ball.getAngle().getX()*ball.getSpeed(), ball.getAngle().getY()*ball.getSpeed());
                    ball.getAngle().setX(1);
                    ball.getAngle().setY(1);
                }
                else if(ball.getPosition().getX() < mouseX && ball.getPosition().getY() > mouseY){
                    ball.getPosition().add(ball.getAngle().getX()*ball.getSpeed(), -ball.getAngle().getY()*ball.getSpeed());
                    ball.getAngle().setX(1);
                    ball.getAngle().setY(1);
                }
                else if(ball.getPosition().getX() > mouseX && ball.getPosition().getY() < mouseY){
                    ball.getPosition().add(ball.getSpeed()*-ball.getAngle().getX(), ball.getAngle().getY()*ball.getSpeed());
                    ball.getAngle().setX(1);
                    ball.getAngle().setY(1);
                }
            }
        }
    }
    
    public void addBall(Ball ball) {
        balls.add(ball);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
