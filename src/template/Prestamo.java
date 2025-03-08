package template;

import java.util.Scanner;
import java.util.Date;

/**
 * Clase abstracta que representa un préstamo.
 * <p>
 * Esta clase define el método plantilla {@link #procesarPrestamo(String, String)} para procesar un préstamo,
 * estableciendo el flujo de operaciones que deben realizarse. Los métodos abstractos deben ser implementados
 * por las subclases para definir comportamientos específicos, como el cálculo de la fecha de vencimiento, la
 * determinación de si se permite la renovación y la realización de la renovación.
 * </p>
 */
public abstract class Prestamo {
    /**
     * Días base del préstamo.
     */
    protected int diasPrestamo;
    
    /**
     * Tipo de préstamo (por ejemplo, "Normal" o "Express").
     */
    protected String tipo;
    
    /**
     * Fecha de inicio del préstamo.
     */
    protected Date fechaInicio;
    
    /**
     * Fecha en la que vence el préstamo.
     */
    protected Date fechaVencimiento;

    /**
     * Método plantilla que define el proceso general del préstamo.
     * <p>
     * Este método realiza los siguientes pasos:
     * <ol>
     *   <li>Asignar la fecha de inicio del préstamo.</li>
     *   <li>Calcular la fecha de vencimiento del préstamo.</li>
     *   <li>Notificar al usuario los detalles del préstamo.</li>
     *   <li>Realizar la renovación del préstamo si está permitida.</li>
     * </ol>
     * </p>
     *
     * @param usuarioNombre el nombre del usuario que solicita el préstamo.
     * @param material      el material que se está solicitando en el préstamo.
     */
    public final void procesarPrestamo(String usuarioNombre, String material) {
    asignarFechaInicio();
    calcularFechaVencimiento();
    notificarPrestamo(usuarioNombre, material);
    
    // Supongamos que se solicita la renovación de forma interactiva:
    Scanner scanner = new Scanner(System.in);
    while (permitirRenovacion()) {
        System.out.print("¿Desea renovar el préstamo? (S/N): ");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("S")) {
            realizarRenovacion();
        } else {
            break;
	}
      }
    }

    /**
     * Asigna la fecha de inicio del préstamo a la fecha actual.
     */
    protected void asignarFechaInicio() {
        fechaInicio = new Date();
        System.out.println("Fecha de inicio: " + fechaInicio);
    }

    /**
     * Calcula la fecha de vencimiento del préstamo.
     * <p>
     * Este método debe ser implementado por las subclases para definir la lógica de cálculo
     * de la fecha de vencimiento según el tipo de préstamo.
     * </p>
     */
    protected abstract void calcularFechaVencimiento();

    /**
     * Determina si se permite la renovación del préstamo.
     * <p>
     * Las subclases deben implementar este método para indicar si se permite renovar el préstamo.
     * </p>
     *
     * @return {@code true} si se permite la renovación, {@code false} en caso contrario.
     */
    protected abstract boolean permitirRenovacion();

    /**
     * Realiza la renovación del préstamo.
     * <p>
     * Este método debe ser implementado por las subclases para definir la lógica de renovación del préstamo.
     * </p>
     */
    protected abstract void realizarRenovacion();

    /**
     * Notifica al usuario los detalles del préstamo.
     * <p>
     * Este método imprime información sobre el préstamo, incluyendo el nombre del usuario, el material solicitado,
     * el tipo de préstamo y la fecha de vencimiento.
     * </p>
     *
     * @param usuarioNombre el nombre del usuario que ha solicitado el préstamo.
     * @param material      el material que se ha solicitado en el préstamo.
     */
    protected void notificarPrestamo(String usuarioNombre, String material) {
        System.out.println("Notificación: " + usuarioNombre + ", has solicitado " + material + " en modalidad " + tipo);
        System.out.println("Fecha de vencimiento: " + fechaVencimiento);
    }
}

