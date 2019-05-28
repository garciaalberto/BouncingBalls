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
        if(ball.getPosition().getX() <= 0){ // Cuando la bola llega a la pared izquierda
            ball.setSpeed(-2*ball.getSpeed().getX(),0);
        } else if (ball.getPosition().getX() + ball.getDiameter() >= WIDTH){ // Cuando la bola llega a la pared derecha
            ball.setSpeed(-2*ball.getSpeed().getX(),0);
        } 
        if(ball.getPosition().getY() <= 0){ // Cuando la bola llega al techo
            ball.setSpeed(0, -2*ball.getSpeed().getY());
        } else if (ball.getPosition().getY() + ball.getDiameter() >= HEIGHT){ // Cuando la bola llega abajo
            ball.setSpeed(0, -10);
        }
        if(ball.getSpeed().magnitude() < 30 || ball.getSpeed().getY() < 0 ){
            ball.accelerate();
        }
        ball.getPosition().add(ball.getSpeed());
    }
    
    private void moveNotWallsNotFollow(Ball ball) {
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
        if(ball.getSpeed().magnitude() < 30 || ball.getSpeed().getY() < 0 ){
            ball.accelerate();
        }
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
        
        if(ball.getPosition().getX() > mouseX){
            ball.setAcceleration(-0.5, ball.getAcceleration().getY());
        } else {
            ball.setAcceleration(0.5, ball.getAcceleration().getY());
        }
        if(ball.getPosition().getY() > mouseY){
            ball.setAcceleration(ball.getAcceleration().getX(), -0.5);
        } else {
            ball.setAcceleration(ball.getAcceleration().getX(), 0.5);
        }
        
        if(ball.getAcceleration().magnitude() > 20){
            ball.accelerate();
        }
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
        
        if(ball.getPosition().getX() > mouseX){
            ball.setAcceleration(-0.5, ball.getAcceleration().getY());
        } else {
            ball.setAcceleration(0.5, ball.getAcceleration().getY());
        }
        if(ball.getPosition().getY() > mouseY){
            ball.setAcceleration(ball.getAcceleration().getX(), -0.5);
        } else {
            ball.setAcceleration(ball.getAcceleration().getX(), 0.5);
        }
        ball.accelerate();
        ball.getPosition().add(ball.getSpeed());
    }

//        if(ball.getPosition().getX() > mouseX && ball.getPosition().getY() > mouseY){
//            ball.setAcceleration(-0.5,-0.5);
//        }
//        else if(ball.getPosition().getX() < mouseX && ball.getPosition().getY() < mouseY){
//            ball.setAcceleration(0.5,0.5);
//        }
//        else if(ball.getPosition().getX() < mouseX && ball.getPosition().getY() > mouseY){
//            ball.setAcceleration(0.5,-0.5);
//        }
//        else if(ball.getPosition().getX() > mouseX && ball.getPosition().getY() < mouseY){
//            ball.setAcceleration(-0.5,0.5);
//        }
//        if(ball.getSpeed().magnitude() < 20){
//        }

//    private void moveWallsNotFollow(Ball ball){
//        if(ball.getPosition().getX() + ball.getAcceleration().getX() < 0){
//            ball.accelerate();
//        } else if (ball.getPosition().getX() + ball.getAcceleration().getX() > WIDTH - ball.getDiameter()){
//            ball.getAcceleration().setX(-1);
//        } else if(ball.getPosition().getY() + ball.getAcceleration().getY() < 0){
//            ball.getAcceleration().setY(1);
//        } else if (ball.getPosition().getY() + ball.getAcceleration().getY() > HEIGHT - ball.getDiameter()){
//            ball.getAcceleration().setY(-1);
//        }
//        bal
//        ball.getPosition().add( ball.getAcceleration().getX()*ball.getSpeed() , ball.getAcceleration().getY()*ball.getSpeed());
//    }
//    private void moveNotWallsNotFollow(Ball ball){
//        if(ball.getPosition().getX() + ball.getAcceleration().getX() < 0){
//            ball.getPosition().setX(WIDTH-(int)ball.getPosition().getX());
//        } else if (ball.getPosition().getX() + ball.getAcceleration().getX() > WIDTH - ball.getDiameter()){
//            ball.getPosition().setX(0);
//        } else if(ball.getPosition().getY() + ball.getAcceleration().getY() < 0){
//            ball.getPosition().setY(HEIGHT-(int)ball.getPosition().getY());
//        } else if (ball.getPosition().getY() + ball.getAcceleration().getY() > HEIGHT - ball.getDiameter()){
//            ball.getPosition().setY(0);
//        }
//        ball.getPosition().add(ball.getAcceleration().getX()*ball.getSpeed(), ball.getAcceleration().getY()*ball.getSpeed());
//    }
//    
//    private void moveNotWallsFollow(Ball ball){
//        if(ball.getPosition().getX() + ball.getAcceleration().getX() < 0){
//            ball.getPosition().setX(WIDTH-(int)ball.getPosition().getX());
//        } else if (ball.getPosition().getX() + ball.getAcceleration().getX() > WIDTH - ball.getDiameter()){
//            ball.getPosition().setX(0);
//        } else if(ball.getPosition().getY() + ball.getAcceleration().getY() < 0){
//            ball.getPosition().setY(HEIGHT-(int)ball.getPosition().getY());
//        } else if (ball.getPosition().getY() + ball.getAcceleration().getY() > HEIGHT - ball.getDiameter()){
//            ball.getPosition().setY(0);
//        }
//        if(ball.getPosition().getX() > mouseX && ball.getPosition().getY() > mouseY){
//            ball.getPosition().substract(ball.getAcceleration().getX()*ball.getSpeed(), ball.getAcceleration().getY()*ball.getSpeed());
//        }
//        else if(ball.getPosition().getX() < mouseX && ball.getPosition().getY() < mouseY){
//            ball.getPosition().add(ball.getAcceleration().getX()*ball.getSpeed(), ball.getAcceleration().getY()*ball.getSpeed());
//        }
//        else if(ball.getPosition().getX() < mouseX && ball.getPosition().getY() > mouseY){
//            ball.getPosition().add(ball.getAcceleration().getX()*ball.getSpeed(), -ball.getAcceleration().getY()*ball.getSpeed());
//        }
//        else if(ball.getPosition().getX() > mouseX && ball.getPosition().getY() < mouseY){
//            ball.getPosition().add(ball.getSpeed()*-ball.getAcceleration().getX(), ball.getAcceleration().getY()*ball.getSpeed());
//        }
//    }
//    
//    private void moveWallsFollow(Ball ball){
//        if(ball.getPosition().getX() + ball.getAcceleration().getX() < 0){
//            ball.getAcceleration().setX(1);
//        } else if (ball.getPosition().getX() + ball.getAcceleration().getX() > WIDTH - ball.getDiameter()){
//            ball.getAcceleration().setX(-1);
//        } else if(ball.getPosition().getY() + ball.getAcceleration().getY() < 0){
//            ball.getAcceleration().setY(1);
//        } else if (ball.getPosition().getY() + ball.getAcceleration().getY() > HEIGHT - ball.getDiameter()){
//            ball.getAcceleration().setY(-1);
//        }
//        if(ball.getPosition().getX() > mouseX && ball.getPosition().getY() > mouseY){
//            ball.getPosition().substract(ball.getAcceleration().getX()*ball.getSpeed(), ball.getAcceleration().getY()*ball.getSpeed());
//            ball.getAcceleration().setX(1);
//            ball.getAcceleration().setY(1);
//        }
//        else if(ball.getPosition().getX() < mouseX && ball.getPosition().getY() < mouseY){
//            ball.getPosition().add(ball.getAcceleration().getX()*ball.getSpeed(), ball.getAcceleration().getY()*ball.getSpeed());
//            ball.getAcceleration().setX(1);
//            ball.getAcceleration().setY(1);
//        }
//        else if(ball.getPosition().getX() < mouseX && ball.getPosition().getY() > mouseY){
//            ball.getPosition().add(ball.getAcceleration().getX()*ball.getSpeed(), -ball.getAcceleration().getY()*ball.getSpeed());
//            ball.getAcceleration().setX(1);
//            ball.getAcceleration().setY(1);
//        }
//        else if(ball.getPosition().getX() > mouseX && ball.getPosition().getY() < mouseY){
//            ball.getPosition().add(ball.getSpeed()*-ball.getAcceleration().getX(), ball.getAcceleration().getY()*ball.getSpeed());
//            ball.getAcceleration().setX(1);
//            ball.getAcceleration().setY(1);
//        }
//    }
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
