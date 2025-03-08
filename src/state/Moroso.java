package state;

/**
 * Representa el estado "Moroso" de un usuario.
 * <p>
 * En este estado, el usuario no puede solicitar nuevos préstamos hasta que devuelva el préstamo actual.
 * </p>
 */
public class Moroso implements EstadoUsuario {

    /**
     * Intenta solicitar un préstamo para el usuario.
     * <p>
     * En el estado Moroso, la solicitud de un nuevo préstamo es denegada.
     * </p>
     *
     * @param usuario el usuario que intenta solicitar el préstamo.
     */
    @Override
    public void solicitarPrestamo(Usuario usuario) {
        System.out.println("[" + usuario.getNombre() + "] No se puede solicitar préstamo. Usuario en estado MOROSO.");
    }

    /**
     * Permite la devolución del préstamo para el usuario.
     * <p>
     * Al devolver el préstamo, el usuario cambia su estado a Activo.
     * </p>
     *
     * @param usuario el usuario que devuelve el préstamo.
     */
    @Override
    public void devolverPrestamo(Usuario usuario) {
        System.out.println("[" + usuario.getNombre() + "] Préstamo devuelto. El usuario vuelve a estar ACTIVO.");
        // Cambiamos el estado del usuario a Activo al devolver el préstamo vencido.
        usuario.setEstado(new Activo());
    }
}

