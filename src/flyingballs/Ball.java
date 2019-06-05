/**
 *
 * @authors Korn, Andreas Manuel & García Socias, Alberto 
 */
package flyingballs;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    private final int DIAMETER = 35; // Indica el diámetro de la bola
    private final String SHAPE = "Circle"; // String específicado por el enunciado, no es usado en ningún momento
    private final int[] COLOR = new int[3]; // Indica el color de la bola con tres valores Integer (R, G y B)
    private Vector speed, position, acceleration; // Indica los vectores de velocidad, posición y aceleración
    
    /**
     * Overload del método para establecer la aceleración que funciona con dos double
     * @param X
     * @param Y 
     */
    private void setAcceleration(double X, double Y){
        this.acceleration.setX(X);
        this.acceleration.setY(Y);
    }
    
    /**
     * Método para establecer la velocidad de la bola
     * @param X
     * @param Y 
     */
    private void setSpeed(double X, double Y){  
        this.speed.add(X, Y);
    }
    
    /**
     * Overload del método para establecer la posición de la bola con dos double
     * @param X
     * @param Y 
     */
    private void setPosition(double X, double Y){ 
        this.position.setX(X);
        this.position.setY(Y);
    }
    
    /**
     * Método para acelerar la bola, añade la aceleración a la velocidad.
     */
    private void accelerate(){ 
        this.speed.add(acceleration);
    }
    
    /**
     * Método para pintar la bola
     * @param graph 
     */
    public void paintBall(Graphics graph){
        graph.setColor(Color.BLACK); // Elige el color negro
        graph.drawOval((int) this.position.getX(), (int) this.position.getY(), this.DIAMETER, this.DIAMETER); // Genera el perímetro de la bola
        graph.setColor(new Color(this.COLOR[0], this.COLOR[1], this.COLOR[2])); // Elige el color de la bola
        graph.fillOval((int) this.position.getX(), (int) this.position.getY(), this.DIAMETER, this.DIAMETER); // Rellena el círculo con dicho color
    }
    
    /**
     * Constructor del objeto bola.
     */
    public Ball(){
        this.position = new Vector((int)Math.floor(Math.random() * (1000-this.DIAMETER)), (int)Math.floor(Math.random() * (500-this.DIAMETER))); // Randomización de la posición
        double xSpeed = Math.random()*2-1; // Randomización de la dirección. Será un número entre -1 y 1. 
        if(xSpeed<0){ // Redondeamos la velocidad a 0.5 en valor absoluto pero con la dirección conseguida aleatoriamente.
            xSpeed = -0.5;
        } else {
            xSpeed = 0.5;
        }
        this.speed = new Vector(xSpeed, 0.5); // Ajuste de la velocidad de la bola
        this.acceleration = new Vector(0, 1); // Ajuste de la aceleración de la bola. Por defecto será hacia abajo, simulando la gravedad.
        COLOR[0] = (int)Math.floor(Math.random() * 256); // Randomización de sus valores RGB
        COLOR[1] = (int)Math.floor(Math.random() * 256);
        COLOR[2] = (int)Math.floor(Math.random() * 256);
    }
    
    /**
     * Lógica de movimiento cuando hay paredes y no se sigue al ratón.
     */
    private void moveWallsNotFollow() {
        this.setAcceleration(0,1); // Restablecemos la gravedad por si fue modificada.
        
        if(this.speed.getX() < 0.5 && this.speed.getX() > -0.5){
            this.speed.setX(0.5); // A veces la velocidad en el eje X es tan pequeña, que la bola parece que no se mueve. Esto se asegura de que sí lo haga.
        }
        
        if(this.position.getX() <= 0){ // Cuando la bola llega a la pared izquierda
            this.setSpeed(5,0);
        } else if (this.position.getX() + this.DIAMETER >= Panel.getWIDTH()){ // Cuando la bola llega a la pared derecha
            this.setSpeed(-5,0);
        } 
        if(this.position.getY() <= 0){ // Cuando la bola llega al techo
            this.setSpeed(0, 5);
        } else if (this.position.getY() + this.DIAMETER >= Panel.getHEIGHT()){ // Cuando la bola llega abajo
            this.setSpeed(0, -5);
        }
        this.accelerate();
        this.speed.limitate(20);
        this.position.add(this.speed);
    }
    
    /**
     * Lógica de movimiento cuando no hay paredes ni se sigue al ratón.
     */
    private void moveNotWallsNotFollow() {
        this.setAcceleration(0,1); // Restablecemos la gravedad por si fue modificada.

        if(this.position.getX() <= 0){ // Cuando la bola llega a la pared izquierda
            this.setPosition(Panel.getWIDTH() - this.DIAMETER, this.position.getY());
        } else if (this.position.getX() + this.DIAMETER >= Panel.getWIDTH()){ // Cuando la bola llega a la pared derecha
            this.setPosition(0, this.position.getY());
        } 
        if(this.position.getY() < 0){ // Cuando la bola llega al techo
            this.setPosition(this.position.getX(), Panel.getHEIGHT() - this.DIAMETER);
        } else if (this.position.getY() + this.DIAMETER >= Panel.getHEIGHT()){ // Cuando la bola llega abajo
            this.setPosition(this.position.getX(), 0);
        }
        this.accelerate();
        this.speed.limitate(10);
        this.position.add(this.speed);
    }
    
    /**
     * Lógica de movimiento cuando no hay paredes y se sigue al ratón.
     * @param mousePosition 
     */
    private void moveNotWallsFollow(Vector mousePosition){
        if(this.position.getX() <= 0){ // Cuando la bola llega a la pared izquierda
            this.setPosition(Panel.getWIDTH() - this.DIAMETER, this.position.getY());
        } else if (this.position.getX() + this.DIAMETER >= Panel.getWIDTH()){ // Cuando la bola llega a la pared derecha
            this.setPosition(0, this.position.getY());
        } 
        if(this.position.getY() < 0){ // Cuando la bola llega al techo
            this.setPosition(this.position.getX(), Panel.getHEIGHT() - this.DIAMETER);
        } else if (this.position.getY() + this.DIAMETER >= Panel.getHEIGHT()){ // Cuando la bola llega abajo
            this.setPosition(this.position.getX(), 0);
        }
        
        this.setAcceleration(((int)mousePosition.getX()-this.position.getX())/(Panel.getWIDTH()),((int)mousePosition.getY()-this.position.getY())/(Panel.getHEIGHT()));
        // Resta la posición del ratón y la de la bola para conseguir la y los divide por las dimensiones del panel para que sea relativo a éste.
        
        this.speed.limitate(5);
        this.accelerate();
        this.position.add(this.speed);
    }
    
    /**
     * Lógica de movimiento cuando hay paredes y las bolas siguen al ratón.
     * @param mousePosition 
     */
    private void moveWallsFollow(Vector mousePosition){
        if(this.position.getX() <= 0){ // Cuando la bola llega a la pared izquierda
            this.setSpeed(1,0);
        } else if (this.position.getX() + this.DIAMETER >= Panel.getWIDTH()){ // Cuando la bola llega a la pared derecha
            this.setSpeed(-1,0);
        } else if(this.position.getY() <= 0){ // Cuando la bola llega al techo
            this.setSpeed(0, 1);
        } else if (this.position.getY() + this.DIAMETER >= Panel.getHEIGHT()){ // Cuando la bola llega abajo
            this.setSpeed(0, -1);
        }
        
        this.setAcceleration(((int)mousePosition.getX()-this.position.getX())/(Panel.getWIDTH()),((int)mousePosition.getY()-this.position.getY())/(Panel.getHEIGHT()));
        // Resta la posición del ratón y la de la bola para conseguir la y los divide por las dimensiones del panel para que sea relativo a éste.
        
        this.accelerate();
        this.speed.limitate(5);
        this.position.add(this.speed);
    }
    
    /**
     * Se encarga del movimiento de las bolas en función de los flags walls y followMouse
     * @param walls
     * @param followMouse
     * @param mousePosition 
     */
    public void move(boolean walls, boolean followMouse, Vector mousePosition){
        if (walls && !followMouse) {
            moveWallsNotFollow();
        }
        if (!walls && !followMouse){
            moveNotWallsNotFollow();
        }
        if(!walls && followMouse){
            moveNotWallsFollow(mousePosition);
        }
        if(walls && followMouse){
            moveWallsFollow(mousePosition);
        }
    }
}
