package template;

import java.util.Calendar;

public class PrestamoNormal extends Prestamo {
    private int numeroRenovaciones = 0;
    private final int maxRenovaciones = 3;

    public PrestamoNormal() {
        this.tipo = "Normal";
        this.diasPrestamo = 15;
    }

    @Override
    protected void calcularFechaVencimiento() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaInicio);
        cal.add(Calendar.DAY_OF_YEAR, diasPrestamo);
        fechaVencimiento = cal.getTime();
    }

    @Override
    protected boolean permitirRenovacion() {
        // Se permite la renovación mientras no se haya alcanzado el máximo permitido.
        return numeroRenovaciones < maxRenovaciones;
    }

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

