// Este es el paquete donde se ubica la clase PetFollower.
package autonoma.mascota.main;

// Importamos las clases necesarias para la interfaz gráfica
import autonoma.mascota.gui.PetPanel;
import javax.swing.*;

/**
 * Clase principal que inicia la aplicación de la mascota seguidora. Crea y
 * configura la ventana principal de la aplicación.
 *
 * @author Cristian Camilo Salazar Arenas
 * @version 1.0
 * @since 2025.05.04
 */
public class PetFollower {

    /**
     * Método principal que inicia la aplicación.
     *
     * @since 2025.05.04
     */
    public static void main(String[] args) {
        // Usamos SwingUtilities.invokeLater para asegurar que la GUI
        // se cree y modifique en el hilo de despacho de eventos (EDT)
        SwingUtilities.invokeLater(() -> {
            // Creamos la ventana principal (JFrame)
            JFrame frame = new JFrame("Mascota Seguidora");
            // Configuramos la operación por defecto al cerrar la ventana
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Añadimos el panel principal (PetPanel) al JFrame
            frame.add(new PetPanel());
            // Establecemos el tamaño de la ventana
            frame.setSize(600, 400);
            // Centramos la ventana en la pantalla
            frame.setLocationRelativeTo(null);
            // Hacemos visible la ventana
            frame.setVisible(true);
        });
    }
}