package template;

import java.util.Date;

public abstract class Prestamo {
    protected int diasPrestamo;       // Días base del préstamo.
    protected String tipo;            // Tipo de préstamo (Normal o Express).
    protected Date fechaInicio;       // Fecha de inicio del préstamo.
    protected Date fechaVencimiento;  // Fecha en la que vence el préstamo.

    // Método plantilla que define el proceso general del préstamo.
    public final void procesarPrestamo(String usuarioNombre, String material) {
        asignarFechaInicio();
        calcularFechaVencimiento();
        notificarPrestamo(usuarioNombre, material);
        if (permitirRenovacion()) {
            realizarRenovacion();
        }
    }

    // Asigna la fecha de inicio del préstamo (actual).
    protected void asignarFechaInicio() {
        fechaInicio = new Date();
        System.out.println("Fecha de inicio: " + fechaInicio);
    }

    // Método abstracto para calcular la fecha de vencimiento, según el tipo de préstamo.
    protected abstract void calcularFechaVencimiento();

    // Determina si se permite la renovación del préstamo.
    protected abstract boolean permitirRenovacion();

    // Método para realizar la renovación del préstamo.
    protected abstract void realizarRenovacion();

    // Notifica al usuario sobre los detalles del préstamo.
    protected void notificarPrestamo(String usuarioNombre, String material) {
        System.out.println("Notificación: " + usuarioNombre + ", has solicitado " + material + " en modalidad " + tipo);
        System.out.println("Fecha de vencimiento: " + fechaVencimiento);
    }
}

