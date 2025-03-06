package state;

public interface EstadoUsuario {
    // Método para solicitar un préstamo.
    void solicitarPrestamo(Usuario usuario);

    // Método para devolver un préstamo.
    void devolverPrestamo(Usuario usuario);
}

