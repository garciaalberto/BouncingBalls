package flyingballs;

import javax.swing.JCheckBox;
import javax.swing.JFrame;

/**
 *
 * @author Korn, Andreas Manuel & García Socias, Alberto 
 */
public class FlyingBalls {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        
        JFrame MainFrame = new JFrame("Flying Balls");
        MainFrame.setSize(1400, 550);
        
        Panel panel = new Panel();
        
        Ball ball1 = new Ball();
        Ball ball2 = new Ball();
        Ball ball3 = new Ball();
        Ball ball4 = new Ball();
        Ball ball5 = new Ball();
        
        panel.addBall(ball1);
        panel.addBall(ball2);
        panel.addBall(ball3);
        panel.addBall(ball4);
        panel.addBall(ball5);
        
        MainFrame.add(panel);
        MainFrame.setVisible(true);
        
        //JCheckBox CollisionActivated = new JCheckBox("Collision");
        //MainFrame.add(CollisionActivated);
        //MainFrame.setVisible(true);
        
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        while(true){
            panel.move();
            panel.repaint();
            Thread.sleep(10);
        }
    }
}
