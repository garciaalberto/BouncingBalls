package flyingballs;

/**
 *
 * @authors Korn, Andreas Manuel & García Socias, Alberto 
 */
public class Vector {
    private int X, Y;

    public void setX(int X) {
        this.X = X;
    }

    public void setY(int Y) {
        this.Y = Y;
    }
    
    public Vector(int X, int Y){
        this.X = X;
        this.Y = Y;
    }
    
    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
    
    public void add(int X, int Y){
        this.X += X;
        this.Y += Y;
    }
    
    public void substract(int X, int Y){
        this.X -= X;
        this.Y -= Y;
    }
    
    public void multiplyByScalar(int scalar){
        this.X = X * scalar;
        this.Y = Y * scalar;
    }
    
    public void divisionByScalar(int scalar){
        this.X = X/scalar;
        this.Y = Y/scalar;
    }
    
    public double magnitude(){
        return Math.sqrt(X*X + Y*Y);
    }
    
    public void unitaryVector(){
        this.divisionByScalar((int)this.magnitude());
    }
    
    public void limitate(int maximum){
        if(this.magnitude() > maximum){
            this.unitaryVector();
            this.divisionByScalar(maximum);
        }
    }
}
