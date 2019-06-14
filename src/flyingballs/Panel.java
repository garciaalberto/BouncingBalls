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
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel extends JPanel implements MouseMotionListener { // Panel hereda de la clase JPanel e implementa la interfaz de MouseMotionListener

    private ArrayList<Ball> balls; // Creación de un ArrayList de bolas para guardar los objetos bola que vayamos creando
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

    /**
     * Cambia el valor del booleano walls al recibido por parámetro.
     * @param walls 
     */
    public void setWalls(boolean walls) { 
        this.walls = walls;
    }

    /**
     * Cambia el valor del bolleano followMouse al recibido por parámetro.
     * @param followMouse 
     */
    public void setFollowMouse(boolean followMouse) {
        this.followMouse = followMouse;
    }

    /**
     * Constructor de la clase panel de bolas. Recibe por parámetro el número de bolas.
     * @param balls 
     */
    public Panel(int balls) { 
        this.followMouse = false; // Setea a la configuración por defecto (No seguir el ratón y rebotar contra las paredes)
        this.walls = true;
        this.balls = new ArrayList<>(); // Inicializa el Arraylist de bolas
        addMouseMotionListener(this); // Añade el mouselistener
        Ball ball; // Crea un objeto bola sin inicializar
        for (int i = 0; i < balls; i++) { // Inicializa y añade en el array tantas bolas como tenga el int balls
            ball = new Ball();
            addBall(ball);
        }
    }

    /**
     * Restablece el panel de bolas. Recibe por parámetro el número de bolas.
     * @param balls 
     */
    public void reset(int balls) {
        this.balls.clear(); // Vacía el ArrayList
        Ball ball; // Crea un objeto bola sin inicializar
        for (int i = 0; i < balls; i++) { // Inizializa y añade en el array tantas bolas como tenga el int balls
            ball = new Ball();
            addBall(ball);
        }
    }

    /**
     * Método sobreescrito de su clase padre JPanel, este método se encarga de pintar las bolas
     * @param graph 
     */
    @Override
    public void paint(Graphics graph) {
        super.paint(graph); // Usamos el super para que haga un repaint como haría la clase paint de JPanel
        Graphics2D g2 = (Graphics2D) graph; // Y le añadimos nuestras funcionalidades extra
        g2.setStroke(new BasicStroke(5)); // Ajustamos el tamaño de trazo
        setSize(WIDTH, HEIGHT); // Ajustamos las dimensiones del panel
        try { // Por cada bola en el ArrayList de bolas, llamamos al método paintBall de ese objeto bola
            for (Ball ball : balls) {
                ball.paintBall(graph);
            }
        } catch (Exception exception) {
            System.err.println("Be careful playing with so many balls"); // Al haber muchas bolas empezarán a fallar, esto avisa por consola que deberíamos considerar dejar de poner tantas bolas
        }
    }
    
    public void updateBallsPosition(){
        for (Ball ball : balls) {
            ball.move(walls, followMouse, mousePosition); // Actualiza las posiciones de las bolas
        }
    }
    
    public void infiniteLoop(JTextField numberOfBalls, int numBalls, JCheckBox wallCheck, JCheckBox mouseCheck) throws InterruptedException {
        while(true){
            repaint(); // Hacemos un repaint de las bolas en sus nuevas posiciones
            updateBallsPosition(); // Calcula la nueva posición de las bolas
            Thread.sleep(10); // Reposamos el panel por 10 microsegundos
            setWalls(wallCheck.isSelected()); // Damos a walls el valor que tenía anteriormente
            setFollowMouse(mouseCheck.isSelected()); // Damos a followMouse el valor que tenía anteriormente
            try{
                if(Integer.parseInt(numberOfBalls.getText()) != numBalls){ // Si el número de bolas del panel de opciones no coincide con las bolas que teníamos antes
                    numBalls = Integer.parseInt(numberOfBalls.getText()); // Cambiamos el nuevo número de bolas
                    reset(numBalls); // Reseteamos el panel con el nuevo número de bolas
                }
            }
            catch(NumberFormatException exception){ // Si el input en el menú no es un número Integer
                numBalls = 0; // Pasa a tener 0 bolas
                reset(numBalls); // Se resetea el panel con 0 bolas
            }
        }
    }

    /**
     * Añade un objeto bola al ArrayList de bolas
     * @param ball 
     */
    public void addBall(Ball ball) {
        balls.add(ball);
    }
    
    /**
     * Implementación del método mouseDragged de la interfaz MouseMotionListener
     * @param event 
     */
    @Override
    public void mouseDragged(MouseEvent event) {
        // No tiene utilidad pero necesita ser creado para implementar correctamente la interfaz MouseMotionListener
    }

    /**
     * Implementación del método mouseMoved de la interfaz MouseMotionListener
     * @param event 
     */
    @Override
    public void mouseMoved(MouseEvent event) { 
        mousePosition.setX(event.getX()); // Cuando se mueve el ratón cambia las posiciones del vector mousePosition
        mousePosition.setY(event.getY());
    }
}
