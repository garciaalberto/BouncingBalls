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
     */
    public static void main(String[] args) throws InterruptedException {
        
        JFrame MainFrame = new JFrame("Flying Balls");
        MainFrame.setSize(1200, 550);
        
        Panel panel = new Panel();
        
        Ball ball = new Ball();
        
        panel.addBall(ball);
        
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
