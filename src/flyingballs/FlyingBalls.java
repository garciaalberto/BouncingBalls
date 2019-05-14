package flyingballs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        
        JFrame mainFrame = new JFrame("Flying Balls");
        mainFrame.setSize(1400, 550);
        
        Panel panel = new Panel();
        
        Ball ball;
        for(int i = 0; i < 10; i++){
            ball = new Ball();
            panel.addBall(ball);
        }
        
        mainFrame.add(panel);
        mainFrame.setVisible(true);
        
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.setBackground(Color.GRAY);
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
        panelSuperior.add(new JLabel("# Balls"));
        panelSuperior.add(new JFormattedTextField(20));
        panelSuperior.add(new JCheckBox("With walls"));
        panelSuperior.add(new JCheckBox("Follow mouse"));
        mainFrame.add(panelSuperior);
        
        mainFrame.getContentPane().add(panelSuperior,BorderLayout.EAST);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        while(true){
            panel.move();
            panel.repaint();
            Thread.sleep(10);
        }
    }
}
