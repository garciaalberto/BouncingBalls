package flyingballs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Korn, Andreas Manuel & García Socias, Alberto 
 */
public class FlyingBalls extends JFrame {
    
    public FlyingBalls(Panel panel, JPanel optionsMenu){
        setPreferredSize(new Dimension(1300,550));
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(optionsMenu, BorderLayout.EAST);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) throws InterruptedException {
        
        Panel panel = new Panel();
        
        Ball ball;
        for(int i = 0; i < 10; i++){
            ball = new Ball();
            panel.addBall(ball);
        }
        JPanel optionsMenu = new JPanel();
        optionsMenu.setBackground(Color.GRAY);
        optionsMenu.add(new JLabel("# Balls"));
        optionsMenu.add(new JTextField("10", 1));
        optionsMenu.add(new JCheckBox("With walls"));
        optionsMenu.add(new JCheckBox("Follow mouse"));
        
        new FlyingBalls(panel, optionsMenu).setVisible(true);
        
        while(true){
            panel.move();
            panel.repaint();
            Thread.sleep(10);
        }
    }
}
