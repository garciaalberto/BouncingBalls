/**
 *
 * @authors Korn, Andreas Manuel & García Socias, Alberto 
 */
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

public class FlyingBalls extends JFrame { // FlyingBalls hereda de la clase JFrame
    
    private static int balls = 2; // Inizializamos el programa con 2 bolas
    
    public FlyingBalls(Panel panel, JPanel optionsMenu){ // Creamos el JFrame con un constructor para la clase FlyingBalls
        setPreferredSize(new Dimension(1300,540)); // Le damos unas dimensiones de 1300 de anchura (1000 para las bolas y 300 para las opciones) por 540 de altura)
        setLayout(new BorderLayout()); // Crea un layout per alinear los dos JPanel
        add(panel, BorderLayout.CENTER); // Añade el panel princnipal que contendrá las bolas en movimiento en el centro
        add(optionsMenu, BorderLayout.EAST); // Añade un segundo panel que contendrá el menú de opciones en el este
        pack(); // Empaqueta todo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra el programa cuando se cierra la ventana
    }
    
    public static void main(String[] args) throws InterruptedException, BadLocationException {
        
        Panel panel = new Panel(2); // Crea un nuevo panel con las dos bolas
        
        JPanel optionsMenu = new JPanel(); // Creación de el panel de opciones
        optionsMenu.setLayout(new BoxLayout(optionsMenu, 1)); // Creación de un BoxLayout para las opciones
        optionsMenu.setBackground(Color.LIGHT_GRAY);
        JLabel label = new JLabel("<html><br><br><br><br><br># Balls</html>"); // Aquí hemos usado HTML tags para centrar el menú verticalmente
        label.setFont(new Font("Arial", 0, 30));
        optionsMenu.add(label);
        JTextField numberOfBalls = new JTextField("2", 17); // Field para el input de bolas
        numberOfBalls.setFont(new Font("Arial", 0, 20));
        numberOfBalls.setMaximumSize(new Dimension(550, 40));
        optionsMenu.add(numberOfBalls);
        JCheckBox walls = new JCheckBox("With walls"); // Checkbox para el input de pared
        walls.setFont(new Font("Arial", 0, 20));
        walls.setSelected(true);
        walls.setBackground(Color.LIGHT_GRAY);
        optionsMenu.add(walls);
        JCheckBox followMouse = new JCheckBox("Follow mouse"); // Checkbox para el input de seguir al ratón
        followMouse.setFont(new Font("Arial", 0, 20));
        followMouse.setBackground(Color.LIGHT_GRAY);
        optionsMenu.add(followMouse);
        
        new FlyingBalls(panel, optionsMenu).setVisible(true); // Creacion del JFrame con los dos paneles
        
        while(true){
            panel.move(); // Llamamos al movimiento de las bolas
            panel.repaint(); // Hacemos un repaint de las bolas en sus nuevas posiciones
            Thread.sleep(10); // Dejamos reposar por 10 microsegundos
            panel.setWalls(walls.isSelected()); // Damos a walls el valor que tenía anteriormente
            panel.setFollowMouse(followMouse.isSelected()); // Damos a followMouse el valor que tenía anteriormente
            try{
                if(Integer.parseInt(numberOfBalls.getText())!= balls){ // Si el número de bolas del panel de opciones no coinicde con las bolas que teníamos antes
                    balls = Integer.parseInt(numberOfBalls.getText()); // Cambiamos el nuevo número de bolas
                    panel.reset(balls); // Reseteamos el panel con el nuevo número de bolas
                }
            }
            catch(NumberFormatException exception){ // Si el input en el menú no es un número Integer
                balls = 0; // Pasa a tener 0 bolas
                panel.reset(balls); // Se resetea el panel con 0 bolas
            }
        }
    }
}
