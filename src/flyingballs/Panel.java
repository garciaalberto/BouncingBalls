/**
 *
 * @authors Korn, Andreas Manuel & García Socias, Alberto
 */
package flyingballs;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Panel extends JPanel implements MouseMotionListener { // Panel hereda de la clase JPanel e implementa la interfaz de MouseMotionListener

    ArrayList<Ball> balls; // Creación de un ArrayList de bolas para guardar los objetos bola que vayamos creando
    private static final int WIDTH = 1000; // Indica la anchura del panel de bolas
    private static final int HEIGHT = 500; // Indica la altura del panel de bolas
    private Vector mousePosition = new Vector(0,0); // Indica la posición del cursero
    private boolean walls; // Indica si los límites están activados en las opciones
    private boolean followMouse; // Indica si el seguir ratón está activado en las opciones

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public void setWalls(boolean walls) { // Cambia el valor de walls
        this.walls = walls;
    }

    public void setFollowMouse(boolean followMouse) { // Cambia el valor de followMouse
        this.followMouse = followMouse;
    }

    public Panel(int balls) { // Constructor de la clase panel de bolas, requiere de un integer que serán en número de bolas con el que se empieza
        this.followMouse = false; // Setea a la configuración básica (No seguir el ratón y rebotar contra las paredes)
        this.walls = true;
        this.balls = new ArrayList<>(); // Inicializa el Arraylist de bolas
        addMouseMotionListener(this); // Añade el mouselistener
        Ball ball; // Crea un objeto bola sin inicializar
        for (int i = 0; i < balls; i++) { // Inizializa y añade en el array tantas bolas como tenga el int balls
            ball = new Ball();
            addBall(ball);
        }
    }

    public void reset(int balls) { // Resetea el panel de bolas, requiere de un integer que serán el nuevo número de bolas
        this.balls.clear(); // Vacía el ArrayList
        Ball ball; // Crea un objeto bola sin inicializar
        for (int i = 0; i < balls; i++) { // Inizializa y aññade en el array tantas bolas como tenga el int balls
            ball = new Ball();
            addBall(ball);
        }
    }

    @Override
    public void paint(Graphics graph) { // Método sobreescrito de su clase padre JPanel, este método se encarga de pintar las bolas
        super.paint(graph); // Usamos el super para que haga un repaint como haría la clase paint de JPanel
        Graphics2D g2 = (Graphics2D) graph; // Y le añadimos nuestras funcionalidades extra
        g2.setStroke(new BasicStroke(5)); // Ajustamos el tamaño de trazo
        setSize(WIDTH, HEIGHT); // Ajustamos las dimensiones del panel
        try { // Por cada bola en el ArrayList de bolas, llamamos al método paintBall de ese objeto bola
            for (Ball ball : balls) {
                ball.paintBall(graph);
                ball.move(walls, followMouse, mousePosition); // Aprovecha el bucle para actualizar las posiciones de las bolas
            }
        } catch (Exception exception) {
            System.err.println("Be careful playing with so many balls"); // Al haber muchas bolas empezarán a fallar, esto avisa por consola que deberíamos considerar dejar de poner tantas bolas
        }

    }

    public void addBall(Ball ball) { // Añade un objeto bola al ArrayList de bolas
        balls.add(ball);
    }
    
    @Override
    public void mouseDragged(MouseEvent e) { // Implementación del método mouseDragged de la interfaz MouseMovementListener
        // No tiene utilidad pero necesita ser creado para implementar correctamente la interfaz MouseMovementListener
    }

    @Override
    public void mouseMoved(MouseEvent e) { // Implementación del método mouseMoved de la interfaz MouseMovementListener
        mousePosition.setX(e.getX()); // Cuando se mueve el ratón cambia las posiciones del vector mousePosition
        mousePosition.setY(e.getY());
    }
}
