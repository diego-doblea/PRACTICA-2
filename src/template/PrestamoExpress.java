package template;

import java.util.Calendar;

/**
 * Implementa un préstamo Express.
 * <p>
 * Un préstamo Express se caracteriza por tener un plazo de 7 días y no permitir renovaciones.
 * </p>
 */
public class PrestamoExpress extends Prestamo {

    /**
     * Construye un préstamo express estableciendo el tipo y los días base del préstamo.
     */
    public PrestamoExpress() {
        this.tipo = "Express";
        this.diasPrestamo = 7;
    }

    /**
     * Calcula la fecha de vencimiento para el préstamo express.
     * <p>
     * Se utiliza un objeto {@link Calendar} para sumar el número de días base del préstamo a la fecha de inicio.
     * </p>
     */
    @Override
    protected void calcularFechaVencimiento() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaInicio);
        cal.add(Calendar.DAY_OF_YEAR, diasPrestamo);
        fechaVencimiento = cal.getTime();
    }

    /**
     * Indica si se permite la renovación del préstamo express.
     * <p>
     * Para un préstamo express, siempre se retorna {@code false} ya que no se permite la renovación.
     * </p>
     *
     * @return {@code false} indicando que la renovación no es permitida.
     */
    @Override
    protected boolean permitirRenovacion() {
        // No se permite renovar un préstamo express.
        return false;
    }

    /**
     * Realiza la renovación del préstamo express.
     * <p>
     * Dado que la renovación no está permitida, este método informa que no es posible renovar.
     * </p>
     */
    @Override
    protected void realizarRenovacion() {
        // Como no se permite la renovación, este método informa que no es posible.
        System.out.println("Renovación no permitida para préstamo express.");
    }
}

