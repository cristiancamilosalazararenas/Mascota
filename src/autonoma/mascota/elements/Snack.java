// Este es el paquete donde se ubica la clase Snack.
package autonoma.mascota.elements;
/**
 * Clase que representa un snack que la mascota puede comer.
 *
 * @author      Cristian Camilo Salazar Arenas
 * @version     1.0
 * @since       2025.05.04
 */
public class Snack {
    /**
     * Coordenada x donde se ubica el snack.
     */
    private final int x;
    /**
     * Coordenada y donde se ubica el snack.
     */
    private final int y;
    /**
     * Tamaño en píxeles del snack.
     */
    private final int size;
    /**
     * Indica si el snack ha sido comido (true) o no (false).
     */
    private boolean eaten;
    /**
     * Crea un nuevo snack en la posición especificada.
     *
     * @param x La coordenada horizontal del snack
     * @param y La coordenada vertical del snack
     * @param size El tamaño del snack en píxeles
     */
    public Snack(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.eaten = false;
    }
    /**
     * @return La coordenada x del snack
     */
    public int getX() {
        return x;
    }
    /**
     * @return La coordenada y del snack
     */
    public int getY() {
        return y;
    }
    /**
     * @return El tamaño del snack en píxeles
     */
    public int getSize() {
        return size;
    }
    /**
     * @return true si el snack fue comido, false si no
     */
    public boolean isEaten() {
        return eaten;
    }
    /**
     * Marca el snack como comido.
     */
    public void eat() {
        this.eaten = true;
    }
}