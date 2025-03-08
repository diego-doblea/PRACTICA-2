package state;

/**
 * Representa el estado "Activo" de un usuario.
 * <p>
 * En este estado, el usuario puede solicitar y devolver préstamos con éxito.
 * </p>
 */
public class Activo implements EstadoUsuario {

    /**
     * Procesa la solicitud de préstamo para un usuario en estado activo.
     * <p>
     * En el estado Activo, el préstamo se solicita con éxito.
     * </p>
     *
     * @param usuario el usuario que solicita el préstamo.
     */
    @Override
    public void solicitarPrestamo(Usuario usuario) {
        System.out.println("[" + usuario.getNombre() + "] Préstamo solicitado con éxito.");
    }

    /**
     * Procesa la devolución de préstamo para un usuario en estado activo.
     * <p>
     * En el estado Activo, al devolver el préstamo el usuario permanece en estado activo.
     * </p>
     *
     * @param usuario el usuario que devuelve el préstamo.
     */
    @Override
    public void devolverPrestamo(Usuario usuario) {
        System.out.println("[" + usuario.getNombre() + "] Préstamo devuelto. Estado sigue siendo ACTIVO.");
    }
}

