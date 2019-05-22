package flyingballs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Korn, Andreas Manuel & García Socias, Alberto 
 */
public class FlyingBalls extends JFrame {
    
    private static int balls = 10;
    
    public FlyingBalls(Panel panel, JPanel optionsMenu){
        setPreferredSize(new Dimension(1300,550));
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(optionsMenu, BorderLayout.EAST);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) throws InterruptedException, BadLocationException {
        
        Panel panel = new Panel(10);
        
        JPanel optionsMenu = new JPanel();
        optionsMenu.setLayout(new BoxLayout(optionsMenu, 1));
        optionsMenu.setBackground(Color.LIGHT_GRAY);
        JLabel label = new JLabel("<html><br><br><br><br><br># Balls</html>");
        label.setFont(new Font("Arial", 0, 30));
        optionsMenu.add(label);
        JTextField numberOfBalls = new JTextField("10", 17);
        numberOfBalls.setFont(new Font("Arial", 0, 20));
        numberOfBalls.setMaximumSize(new Dimension(550, 40));
        optionsMenu.add(numberOfBalls);
        
        JCheckBox walls = new JCheckBox("With walls");
        walls.setFont(new Font("Arial", 0, 20));
        walls.setSelected(true);
        walls.setBackground(Color.LIGHT_GRAY);
        optionsMenu.add(walls);
        JCheckBox followMouse = new JCheckBox("Follow mouse");
        followMouse.setFont(new Font("Arial", 0, 20));
        followMouse.setBackground(Color.LIGHT_GRAY);
        optionsMenu.add(followMouse);
        
        new FlyingBalls(panel, optionsMenu).setVisible(true);
        
        while(true){
            panel.move();
            panel.repaint();
            Thread.sleep(10);
            panel.setWalls(walls.isSelected());
            panel.setFollowMouse(followMouse.isSelected());
            try{
                if(Integer.parseInt(numberOfBalls.getText())!= balls){
                    balls = Integer.parseInt(numberOfBalls.getText());
                    panel.reset(balls);
                }
            }
            catch(NumberFormatException exception){
                balls = 0;
                panel.reset(balls);
            }
        }
    }
}
