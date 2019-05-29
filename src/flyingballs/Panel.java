package flyingballs;

import java.awt.BasicStroke;
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
public class Panel extends JPanel implements MouseMotionListener {

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
        for (int i = 0; i < balls; i++) {
            ball = new Ball();
            addBall(ball);
        }
    }

    public void reset(int balls) {
        this.balls.clear();
        Ball ball;
        for (int i = 0; i < balls; i++) {
            ball = new Ball();
            addBall(ball);
        }
    }

    @Override
    public void paint(Graphics graph) {
        super.paint(graph);
        Graphics2D g2 = (Graphics2D) graph;
        g2.setStroke(new BasicStroke(5.0f));
        setSize(WIDTH, HEIGHT);
        try {
            for (Ball ball : balls) {
                ball.paintBall(graph);
            }
        } catch (Exception exception) {
            System.err.println("Be careful playing with so many balls");
        }

    }

    public void move() {
        for (Ball ball : balls) {
            if (walls && !followMouse) {
                moveWallsNotFollow(ball);
            }
            if (!walls && !followMouse){
                moveNotWallsNotFollow(ball);
            }
            if(!walls && followMouse){
                moveNotWallsFollow(ball);
            }
            if(walls && followMouse){
                moveWallsFollow(ball);
            }
        }
    }

    private void moveWallsNotFollow(Ball ball) {
        ball.setAcceleration(0,1);
        
        if(ball.getPosition().getX() <= 0){ // Cuando la bola llega a la pared izquierda
            ball.setSpeed(5,0);
        } else if (ball.getPosition().getX() + ball.getDiameter() >= WIDTH){ // Cuando la bola llega a la pared derecha
            ball.setSpeed(-5,0);
        } 
        if(ball.getPosition().getY() <= 0){ // Cuando la bola llega al techo
            ball.setSpeed(0, 5);
        } else if (ball.getPosition().getY() + ball.getDiameter() >= HEIGHT){ // Cuando la bola llega abajo
            ball.setSpeed(0, -10);
        }
        ball.accelerate();
        ball.getSpeed().limitate(30);
        ball.getPosition().add(ball.getSpeed());
    }
    
    private void moveNotWallsNotFollow(Ball ball) {
        ball.setAcceleration(0,1);

        if(ball.getPosition().getX() <= 0){ // Cuando la bola llega a la pared izquierda
            ball.setPosition(WIDTH - ball.getDiameter(), ball.getPosition().getY());
        } else if (ball.getPosition().getX() + ball.getDiameter() >= WIDTH){ // Cuando la bola llega a la pared derecha
            ball.setPosition(0, ball.getPosition().getY());
        } 
        if(ball.getPosition().getY() < 0){ // Cuando la bola llega al techo
            ball.setPosition(ball.getPosition().getX(), HEIGHT - ball.getDiameter());
        } else if (ball.getPosition().getY() + ball.getDiameter() >= HEIGHT){ // Cuando la bola llega abajo
            ball.setPosition(ball.getPosition().getX(), 0);
        }
        ball.accelerate();
        ball.getSpeed().limitate(30);
        ball.getPosition().add(ball.getSpeed());
    }
    
    private void moveNotWallsFollow(Ball ball){
        if(ball.getPosition().getX() <= 0){ // Cuando la bola llega a la pared izquierda
            ball.setPosition(WIDTH - ball.getDiameter(), ball.getPosition().getY());
        } else if (ball.getPosition().getX() + ball.getDiameter() >= WIDTH){ // Cuando la bola llega a la pared derecha
            ball.setPosition(0, ball.getPosition().getY());
        } 
        if(ball.getPosition().getY() < 0){ // Cuando la bola llega al techo
            ball.setPosition(ball.getPosition().getX(), HEIGHT - ball.getDiameter());
        } else if (ball.getPosition().getY() + ball.getDiameter() >= HEIGHT){ // Cuando la bola llega abajo
            ball.setPosition(ball.getPosition().getX(), 0);
        }
        
        ball.setAcceleration((mouseX-ball.getPosition().getX())/WIDTH,(mouseY-ball.getPosition().getY())/HEIGHT);
        
        ball.getSpeed().limitate(10);
        ball.accelerate();
        ball.getPosition().add(ball.getSpeed());
    }
    
    private void moveWallsFollow(Ball ball){
        if(ball.getPosition().getX() <= 0){ // Cuando la bola llega a la pared izquierda
            ball.setSpeed(1,0);
        } else if (ball.getPosition().getX() + ball.getDiameter() >= WIDTH){ // Cuando la bola llega a la pared derecha
            ball.setSpeed(-1,0);
        } else if(ball.getPosition().getY() <= 0){ // Cuando la bola llega al techo
            ball.setSpeed(0, 1);
        } else if (ball.getPosition().getY() + ball.getDiameter() >= HEIGHT){ // Cuando la bola llega abajo
            ball.setSpeed(0, -1);
        }
        
        ball.setAcceleration((mouseX-ball.getPosition().getX())/WIDTH,(mouseY-ball.getPosition().getY())/HEIGHT);

        ball.accelerate();
        ball.getSpeed().limitate(10);
        ball.getPosition().add(ball.getSpeed());
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
