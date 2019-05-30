/**
 *
 * @authors Korn, Andreas Manuel & García Socias, Alberto 
 */
package flyingballs;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    private final int DIAMETER = 35; // Indita el diametro de la bola
    private final String SHAPE = "Circle"; // String específicado por el enunciado, no es usado en ningún momento
    private final int[] COLOR = new int[3]; // Indica el color de la bola con tres valores Integer (R, G y B)
    private Vector speed, position, acceleration; // Indica los vectores de velocidad, posición y aceleración

    public Vector getAcceleration() { // Método que devuelve la aceleración
        return acceleration;
    }
    
    public void setAcceleration(Vector acceleration){ // Método que establece la aceleración
        this.acceleration = acceleration;
    }
    
    public void setAcceleration(double X, double Y){ // Overload del método para establecer la aceleración que funciona con dos double
        this.acceleration.setX(X);
        this.acceleration.setY(Y);
    }
    
    public int getDiameter() { // Método que devuelve el diametro de la bola
        return DIAMETER;
    }

    public int[] getColor() { // Método que devuelve los valores RGB de la bola
        return COLOR;
    }

    public Vector getSpeed() { // Método que devuelve la velocidad de la bola
        return speed;
    }
    
    public void setSpeed(double X, double Y){ // Método para establecer la velocidad de la bola
        this.speed.add(X, Y);
    }

    public Vector getPosition() { // Método que devuelve la posición de la bola
        return position;
    }
    
    public void setPosition(Vector position){ // Método para establecer la posición de la bola
        this.position = position;
    }
    
    public void setPosition(double X, double Y){ // Overload del método para establecer la posición de la bola con dos double
        this.position.setX(X);
        this.position.setY(Y);
    }
    
    public void accelerate(){ // Método para acelerar la bola, añade a la velocidad la acceleración
        this.speed.add(acceleration);
    }
    
    public void paintBall(Graphics graph){ // Método que pinta la bola
        graph.setColor(Color.BLACK); // Elige el color negro
        graph.drawOval((int) this.getPosition().getX(), (int) this.getPosition().getY(), this.getDiameter(), this.getDiameter()); // Genera el círculo contenedor del color
        graph.setColor(new Color(this.getColor()[0], this.getColor()[1], this.getColor()[2])); // Elige el color de la bola
        graph.fillOval((int) this.getPosition().getX(), (int) this.getPosition().getY(), this.getDiameter(), this.getDiameter()); // Rellena el círculo con dicho color
    }
    
    public Ball(){ // Constructor para el objeto bola
        this.position = new Vector((int)Math.floor(Math.random() * (1000-this.DIAMETER)), (int)Math.floor(Math.random() * (500-this.DIAMETER))); // Randomización de la posición
        double xSpd = Math.random()*2-1;
        if(xSpd<0){ // Randomización de la dirección
            xSpd = -0.5;
        } else {
            xSpd = 0.5;
        }
        this.speed = new Vector(xSpd, 0.5); // Ajuste de la velocidad de la bola
        this.acceleration = new Vector(0, 1); // Ajuste de la acceleración de la bola
        COLOR[0] = (int)Math.floor(Math.random() * 256); // Randomización de sus valores RGB
        COLOR[1] = (int)Math.floor(Math.random() * 256);
        COLOR[2] = (int)Math.floor(Math.random() * 256);
    }
    
    private void moveWallsNotFollow() {
        this.setAcceleration(0,1);
        
        if(this.getSpeed().getX() < 0.5 && this.getSpeed().getX() > -0.5){
            this.getSpeed().setX(0.5); // A veces la velocidad en el eje X es tan pequeña, que la bola parece que no se mueve. Esto se asegura de que sí lo haga.
        }
        
        if(this.getPosition().getX() <= 0){ // Cuando la bola llega a la pared izquierda
            this.setSpeed(5,0);
        } else if (this.getPosition().getX() + this.getDiameter() >= Panel.getWIDTH()){ // Cuando la bola llega a la pared derecha
            this.setSpeed(-5,0);
        } 
        if(this.getPosition().getY() <= 0){ // Cuando la bola llega al techo
            this.setSpeed(0, 5);
        } else if (this.getPosition().getY() + this.getDiameter() >= Panel.getHEIGHT()){ // Cuando la bola llega abajo
            this.setSpeed(0, -5);
        }
        this.accelerate();
        this.getSpeed().limitate(20);
        this.getPosition().add(this.getSpeed());
    }
    
    private void moveNotWallsNotFollow() {
        this.setAcceleration(0,1);

        if(this.getPosition().getX() <= 0){ // Cuando la bola llega a la pared izquierda
            this.setPosition(Panel.getWIDTH() - this.getDiameter(), this.getPosition().getY());
        } else if (this.getPosition().getX() + this.getDiameter() >= Panel.getWIDTH()){ // Cuando la bola llega a la pared derecha
            this.setPosition(0, this.getPosition().getY());
        } 
        if(this.getPosition().getY() < 0){ // Cuando la bola llega al techo
            this.setPosition(this.getPosition().getX(), Panel.getHEIGHT() - this.getDiameter());
        } else if (this.getPosition().getY() + this.getDiameter() >= Panel.getHEIGHT()){ // Cuando la bola llega abajo
            this.setPosition(this.getPosition().getX(), 0);
        }
        this.accelerate();
        this.getSpeed().limitate(10);
        this.getPosition().add(this.getSpeed());
    }
    
    private void moveNotWallsFollow(Vector mousePosition){
        if(this.getPosition().getX() <= 0){ // Cuando la bola llega a la pared izquierda
            this.setPosition(Panel.getWIDTH() - this.getDiameter(), this.getPosition().getY());
        } else if (this.getPosition().getX() + this.getDiameter() >= Panel.getWIDTH()){ // Cuando la bola llega a la pared derecha
            this.setPosition(0, this.getPosition().getY());
        } 
        if(this.getPosition().getY() < 0){ // Cuando la bola llega al techo
            this.setPosition(this.getPosition().getX(), Panel.getHEIGHT() - this.getDiameter());
        } else if (this.getPosition().getY() + this.getDiameter() >= Panel.getHEIGHT()){ // Cuando la bola llega abajo
            this.setPosition(this.getPosition().getX(), 0);
        }
        
        this.setAcceleration(((int)mousePosition.getX()-this.getPosition().getX())/(2*Panel.getWIDTH()),((int)mousePosition.getY()-this.getPosition().getY())/(2*Panel.getHEIGHT()));
        
        this.getSpeed().limitate(5);
        this.accelerate();
        this.getPosition().add(this.getSpeed());
    }
    
    private void moveWallsFollow(Vector mousePosition){
        if(this.getPosition().getX() <= 0){ // Cuando la bola llega a la pared izquierda
            this.setSpeed(1,0);
        } else if (this.getPosition().getX() + this.getDiameter() >= Panel.getWIDTH()){ // Cuando la bola llega a la pared derecha
            this.setSpeed(-1,0);
        } else if(this.getPosition().getY() <= 0){ // Cuando la bola llega al techo
            this.setSpeed(0, 1);
        } else if (this.getPosition().getY() + this.getDiameter() >= Panel.getHEIGHT()){ // Cuando la bola llega abajo
            this.setSpeed(0, -1);
        }
        
        this.setAcceleration(((int)mousePosition.getX()-this.getPosition().getX())/(2*Panel.getWIDTH()),((int)mousePosition.getY()-this.getPosition().getY())/(2*Panel.getHEIGHT()));

        this.accelerate();
        this.getSpeed().limitate(5);
        this.getPosition().add(this.getSpeed());
    }
    
    public void move(boolean walls, boolean followMouse, Vector mousePosition){ // Se encarga del movimiento de las bolas en función de los flags walls y followMouse
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
