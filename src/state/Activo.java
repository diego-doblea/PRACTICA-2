package state;

public class Activo implements EstadoUsuario {

    @Override
    public void solicitarPrestamo(Usuario usuario) {
        System.out.println("[" + usuario.getNombre() + "] Préstamo solicitado con éxito.");
        // Aquí se implementaría la lógica de préstamo.
    }

    @Override
    public void devolverPrestamo(Usuario usuario) {
        System.out.println("[" + usuario.getNombre() + "] Préstamo devuelto. Estado sigue siendo ACTIVO.");
        // Lógica adicional si es necesario.
    }
}

