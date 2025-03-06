package template;

import java.util.Calendar;

public class PrestamoExpress extends Prestamo {

    public PrestamoExpress() {
        this.tipo = "Express";
        this.diasPrestamo = 7;
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
        // No se permite renovar un préstamo express.
        return false;
    }

    @Override
    protected void realizarRenovacion() {
        // Como no se permite la renovación, este método puede dejarse vacío o informar que no es posible.
        System.out.println("Renovación no permitida para préstamo express.");
    }
}

