/**
 *
 * @authors Korn, Andreas Manuel & García Socias, Alberto 
 */
package flyingballs;

public class Vector { // Clase Vector, se encargará de la representación bidimensional
    private double X, Y; // Indican las dos dimensiones del vector, X para la anchura e Y para la altura

    public void setX(double X) { // Ajusta el valor de X
        this.X = X;
    }

    public void setY(double Y) { // Ajusta el valor de Y
        this.Y = Y;
    }
    
    public Vector(double X, double Y){ // Constructor del objeto vector, requiere dos doubles, uno para el valor X y otro para el valor Y
        this.X = X;
        this.Y = Y;
    }
    
    public double getX() { // Devuelve el valor de X
        return X;
    }

    public double getY() { // Devuelve el valor de Y
        return Y;
    }
    
    public void add(double X, double Y){ // Suma vectorial con dos doubles representando la X y la Y a sumar
        this.X += X;
        this.Y += Y;
    }
    
    public void add(Vector vec){ // Suma vectorial
        this.X += vec.X;
        this.Y += vec.Y;
    }
    
    public void substract(double X, double Y){ // Resta vectorial con dos doubles representando la X y la Y a restar
        this.X -= X;
        this.Y -= Y;
    }
    
    public void multiplyByScalar(double scalar){ // Multiplicación por un escaclar con un double representando el escalar
        this.X = X * scalar;
        this.Y = Y * scalar;
    }
    
    public void divisionByScalar(double scalar){ // División por un escaclar con un double representando el escalar
        this.X = X/scalar;
        this.Y = Y/scalar;
    }
    
    public double module(){ // Devuelve el valor de la modulo del vector
        return Math.sqrt(X*X + Y*Y);
    }
    
    public void unitaryVector(){ // Hace el vector unitario de el Vector
        this.divisionByScalar(this.module());
    }
    
    public void limitate(double maximum){ // Limita el vector en función del módulo del vector
        if(this.module() >= maximum){
            this.unitaryVector();
            this.multiplyByScalar(maximum);
        }
    }
}
