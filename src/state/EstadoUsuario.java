package state;

/**
 * Define el contrato para los estados de un usuario en el sistema de préstamos.
 * <p>
 * Cada estado debe implementar las acciones de solicitar y devolver préstamo, definiendo
 * el comportamiento específico según el estado actual del usuario.
 * </p>
 */
public interface EstadoUsuario {
    /**
     * Maneja la acción de solicitar un préstamo para el usuario.
     * <p>
     * La implementación de este método debe definir qué ocurre cuando un usuario en el estado
     * actual intenta solicitar un préstamo.
     * </p>
     *
     * @param usuario el usuario que solicita el préstamo.
     */
    void solicitarPrestamo(Usuario usuario);

    /**
     * Maneja la acción de devolver un préstamo para el usuario.
     * <p>
     * La implementación de este método debe definir qué ocurre cuando un usuario en el estado
     * actual devuelve un préstamo.
     * </p>
     *
     * @param usuario el usuario que devuelve el préstamo.
     */
    void devolverPrestamo(Usuario usuario);
}

