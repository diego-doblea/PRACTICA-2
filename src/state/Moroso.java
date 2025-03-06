package state;

public class Moroso implements EstadoUsuario {

    @Override
    public void solicitarPrestamo(Usuario usuario) {
        System.out.println("[" + usuario.getNombre() + "] No se puede solicitar préstamo. Usuario en estado MOROSO.");
    }

    @Override
    public void devolverPrestamo(Usuario usuario) {
        System.out.println("[" + usuario.getNombre() + "] Préstamo devuelto. El usuario vuelve a estar ACTIVO.");
        // Cambiamos el estado del usuario a Activo al devolver el préstamo vencido.
        usuario.setEstado(new Activo());
    }
}

