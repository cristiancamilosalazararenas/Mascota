// Este es el paquete donde se ubica la clase PetPanel.
package autonoma.mascota.gui;
// Importamos la clase Snack para poder usarla
import autonoma.mascota.elements.Snack;
// Importamos las clases necesarias de Swing y AWT
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase encargada de dibujar la mascota y los snacks en pantalla. También
 * maneja la lógica para que la mascota siga al cursor o los snacks.
 *
 * @author      Cristian Camilo Salazar Arenas
 * @version     1.0
 * @since       2025.05.04
 */
public class PetPanel extends JPanel implements MouseMotionListener {
    /**
     * Guarda la posición actual del cursor del mouse.
     */
    private Point mousePos = new Point(0, 0);
    /**
     * Guarda la posición actual de la mascota.
     */
    private final Point petPos = new Point(100, 100);
    /**
     * Tamaño en píxeles de la mascota.
     */
    private final int petSize = 30;
    /**
     * Lista que contiene todos los snacks disponibles.
     */
    private final List<Snack> snacks = new ArrayList<>();
    /**
     * Constructor que inicializa el panel, crea los snacks y comienza la
     * animación.
     *
     * @since 2025.05.04
     */
    public PetPanel() {
        // Creamos 5 snacks y los distribuimos horizontalmente
        for (int i = 0; i < 5; i++) {
            snacks.add(new Snack(50 + i * 80, 200, 20));
        }
        // Configuramos el listener para detectar movimiento del mouse
        addMouseMotionListener(this);
        // Creamos e iniciamos el hilo de animación
        Thread animator = new Thread(() -> {
            while (true) {
                movePet();      // Actualiza posición de la mascota
                repaint();     // Vuelve a dibujar el panel
                try {
                    Thread.sleep(30); // Espera 30ms (~33 FPS)
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        animator.setDaemon(true); // El hilo termina cuando se cierra la aplicación
        animator.start();
    }
    /**
     * Método que actualiza la posición de la mascota. La hace seguir al cursor
     * o al snack más cercano.
     *
     * @since 2025.05.04
     */
    private void movePet() {
        Snack target = findNearestSnack();
        Point dest;
        // Si hay un snack cerca (150px), va hacia él
        if (target != null && petPos.distance(target.getX(), target.getY()) < 150) {
            dest = new Point(target.getX(), target.getY());
            // Si alcanza el snack, lo marca como comido
            if (petPos.distance(dest) < (petSize + target.getSize()) / 2.0) {
                target.eat();
            }
        } else {
            // Si no hay snacks cerca, sigue el cursor
            dest = mousePos;
        }
        // Calcula la dirección del movimiento
        double dx = dest.x - petPos.x;
        double dy = dest.y - petPos.y;
        double dist = Math.hypot(dx, dy);
        // Mueve la mascota si está lejos del objetivo
        if (dist > 1) {
            petPos.x += (int) (dx / dist * 2);
            petPos.y += (int) (dy / dist * 2);
        }
    }
    /**
     * Busca el snack más cercano que no haya sido comido.
     *
     * @return El snack más cercano o null si no hay disponibles
     * @since 2025.05.04
     */
    private Snack findNearestSnack() {
        Snack nearest = null;
        double minDist = Double.MAX_VALUE;
        for (Snack s : snacks) {
            if (s.isEaten()) {
                continue; // Ignora los snacks ya comidos
            }
            double d = petPos.distance(s.getX(), s.getY());
            if (d < minDist) {
                minDist = d;
                nearest = s;
            }
        }
        return nearest;
    }
    /**
     * Dibuja todos los elementos en el panel (snacks y mascota).
     *
     * @param g El objeto Graphics para dibujar
     * @since 2025.05.04
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibuja los snacks (círculos naranjas)
        for (Snack s : snacks) {
            if (!s.isEaten()) {
                g.setColor(Color.ORANGE);
                g.fillOval(s.getX() - s.getSize() / 2, s.getY() - s.getSize() / 2, s.getSize(), s.getSize());
            }
        }
        // Dibuja la mascota (círculo azul)
        g.setColor(Color.BLUE);
        g.fillOval(petPos.x - petSize / 2, petPos.y - petSize / 2, petSize, petSize);
    }
    /**
     * Actualiza la posición del cursor cuando se mueve el mouse.
     *
     * @param e El evento de movimiento del mouse
     * @since 2025.05.04
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        mousePos = e.getPoint();
    }
    /**
     * Método requerido por la interfaz MouseMotionListener (no usado).
     *
     * @param e El evento de arrastre del mouse
     * @since 2025.05.04
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        // No implementado
    }
}
