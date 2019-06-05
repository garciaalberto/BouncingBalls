/**
 *
 * @authors Korn, Andreas Manuel & García Socias, Alberto 
 */
package flyingballs;

public class Vector { // Clase Vector, se encargará de la representación bidimensional
    private double X, Y; // Indican las dos dimensiones del vector, X para la anchura e Y para la altura

    /**
     * Setter del valor de X
     * @param X 
     */
    public void setX(double X) {
        this.X = X;
    }

    /**
     * Setter del valor Y.
     * @param Y 
     */
    public void setY(double Y) {
        this.Y = Y;
    }
    
    /**
     * Constructor del objeto vector. Recibe por parámetros dos doubles que serán los valores X e Y del vector.
     * @param X
     * @param Y 
     */
    public Vector(double X, double Y){
        this.X = X;
        this.Y = Y;
    }
    
    /**
     * Getter del valor X
     * @return 
     */
    public double getX() {
        return X;
    }

    /**
     * Getter del valor de Y
     * @return 
     */
    public double getY() {
        return Y;
    }
    
    /**
     * Ejecuta la suma vectorial al vector recibiendo los valores por parámetro.
     * @param X
     * @param Y 
     */
    public void add(double X, double Y){
        this.X += X;
        this.Y += Y;
    }
    
    /**
     * Ejecuta la suma vectorial al vector que afecta recibiendo el vector a sumar.
     * @param vector 
     */
    public void add(Vector vector){
        this.X += vector.X;
        this.Y += vector.Y;
    }
    
    /**
     * Ejecuta la resta vectorial al vector recibiendo los valores por parámetro
     * @param X
     * @param Y 
     */
    public void substract(double X, double Y){
        this.X -= X;
        this.Y -= Y;
    }
    
    /**
     * Multiplica el vector por un double recibido por parámetro.
     * @param scalar 
     */
    public void multiplyByScalar(double scalar){
        this.X = X * scalar;
        this.Y = Y * scalar;
    }
    
    /**
     * Divide el vector por un double recibido por parámetro.
     * @param scalar 
     */
    public void divisionByScalar(double scalar){
        this.X = X/scalar;
        this.Y = Y/scalar;
    }
    
    /**
     * Devuelve el módulo del vector.
     * @return 
     */
    private double module(){
        return Math.sqrt(X*X + Y*Y);
    }
    
    /**
     * Calcula el vector unitario del vector.
     */
    private void unitaryVector(){
        this.divisionByScalar(this.module());
    }
    
    /**
     * Limita el vector si el módulo es mayor que el valor recibido por parámetro.
     * @param maximum 
     */
    public void limitate(double maximum){
        if(this.module() >= maximum){
            this.unitaryVector();
            this.multiplyByScalar(maximum);
        }
    }
}
