package flyingballs;

/**
 *
 * @authors Korn, Andreas Manuel & García Socias, Alberto 
 */
public class Vector {
    private double X, Y;

    public void setX(double X) {
        this.X = X;
    }

    public void setY(double Y) {
        this.Y = Y;
    }
    
    public Vector(double X, double Y){
        this.X = X;
        this.Y = Y;
    }
    
    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }
    
    public void add(double X, double Y){
        this.X += X;
        this.Y += Y;
    }
    
    public void add(Vector vec){
        this.X += vec.X;
        this.Y += vec.Y;
    }
    
    public void substract(double X, double Y){
        this.X -= X;
        this.Y -= Y;
    }
    
    public void multiplyByScalar(double scalar){
        this.X = X * scalar;
        this.Y = Y * scalar;
    }
    
    public void divisionByScalar(double scalar){
        this.X = X/scalar;
        this.Y = Y/scalar;
    }
    
    public double module(){
        return Math.sqrt(X*X + Y*Y);
    }
    
    public void unitaryVector(){
        this.divisionByScalar(this.module());
    }
    
    public void limitate(double maximum){
        if(this.module() >= maximum){
            this.unitaryVector();
            this.multiplyByScalar(maximum);
        }
    }
}
