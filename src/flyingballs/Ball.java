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
}
