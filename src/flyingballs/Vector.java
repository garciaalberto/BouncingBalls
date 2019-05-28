package flyingballs;

/**
 *
 * @authors Korn, Andreas Manuel & García Socias, Alberto 
 */
public class Vector {
    private double X, Y;

    public void setX(int X) {
        this.X = X;
    }

    public void setY(int Y) {
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
    
    public double magnitude(){
        return Math.sqrt(X*X + Y*Y);
    }
    
    public void unitaryVector(){
        this.divisionByScalar(this.magnitude());
    }
    
    public void limitate(double maximum){
        if(this.magnitude() > maximum){
            this.unitaryVector();
            this.divisionByScalar(maximum);
        }
    }
}
