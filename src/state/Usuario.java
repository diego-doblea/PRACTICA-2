package state;

public class Usuario {
    private EstadoUsuario estado;
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
        // Inicialmente el usuario se encuentra en estado activo.
        this.estado = new Activo();
    }

    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public String getNombre() {
        return nombre;
    }

    // Método para solicitar préstamo que delega en el estado actual.
    public void solicitarPrestamo() {
        estado.solicitarPrestamo(this);
    }

    // Método para devolver préstamo que delega en el estado actual.
    public void devolverPrestamo() {
        estado.devolverPrestamo(this);
    }
}

