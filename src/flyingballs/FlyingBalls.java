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
        optionsMenu.setBackground(Color.GRAY);
        optionsMenu.add(new JLabel("# Balls"));
        JTextField numberOfBalls = new JTextField("10", 1);
        optionsMenu.add(numberOfBalls);
        
        optionsMenu.add(new JCheckBox("With walls"));
        optionsMenu.add(new JCheckBox("Follow mouse"));
        
        new FlyingBalls(panel, optionsMenu).setVisible(true);
        
        while(true){
            panel.move();
            panel.repaint();
            Thread.sleep(10);
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
