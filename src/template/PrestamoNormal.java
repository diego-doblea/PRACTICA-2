package template;

import java.util.Calendar;

/**
 * Representa un préstamo de tipo "Normal".
 * <p>
 * Este préstamo tiene un plazo base de 15 días y permite renovaciones, con un máximo de 3 renovaciones.
 * Cada renovación extiende la fecha de vencimiento en 5 días.
 * </p>
 */
public class PrestamoNormal extends Prestamo {
    /**
     * Número de renovaciones realizadas.
     */
    private int numeroRenovaciones = 0;
    
    /**
     * Máximo número de renovaciones permitidas.
     */
    private final int maxRenovaciones = 3;

    /**
     * Construye un préstamo normal estableciendo el tipo y los días base del préstamo.
     */
    public PrestamoNormal() {
        this.tipo = "Normal";
        this.diasPrestamo = 15;
    }

    /**
     * Calcula la fecha de vencimiento para el préstamo normal.
     * <p>
     * Se utiliza un objeto {@link Calendar} para sumar los días base del préstamo a la fecha de inicio.
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
     * Determina si se permite la renovación del préstamo normal.
     * <p>
     * Se permite la renovación mientras el número de renovaciones realizadas sea menor que el máximo permitido.
     * </p>
     *
     * @return {@code true} si se permite renovar el préstamo, {@code false} de lo contrario.
     */
    @Override
    protected boolean permitirRenovacion() {
        // Se permite la renovación mientras no se haya alcanzado el máximo permitido.
        return numeroRenovaciones < maxRenovaciones;
    }

    /**
     * Realiza la renovación del préstamo normal.
     * <p>
     * Cada renovación extiende la fecha de vencimiento en 5 días, incrementa el contador de renovaciones,
     * y notifica la nueva fecha de vencimiento.
     * </p>
     */
    @Override
    protected void realizarRenovacion() {
        // Cada renovación extiende la fecha de vencimiento en 5 días.
        numeroRenovaciones++;
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaVencimiento);
        cal.add(Calendar.DAY_OF_YEAR, 5);
        fechaVencimiento = cal.getTime();
        System.out.println("Renovación " + numeroRenovaciones + " realizada. Nueva fecha de vencimiento: " + fechaVencimiento);
    }
}

