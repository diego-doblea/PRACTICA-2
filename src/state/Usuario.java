package state;

/**
 * Representa a un usuario que utiliza el patrón de diseño State para gestionar su estado.
 * <p>
 * Un usuario posee un estado actual (por ejemplo, Activo, Inactivo, etc.) y un nombre. Las acciones
 * de solicitar y devolver préstamo se delegan en el estado actual del usuario.
 * </p>
 */
public class Usuario {
    /**
     * Estado actual del usuario, que determina el comportamiento de sus operaciones.
     */
    private EstadoUsuario estado;
    
    /**
     * Nombre del usuario.
     */
    private String nombre;

    /**
     * Crea un nuevo usuario con el nombre especificado e inicializa su estado como Activo.
     *
     * @param nombre el nombre del usuario.
     */
    public Usuario(String nombre) {
        this.nombre = nombre;
        // Inicialmente el usuario se encuentra en estado activo.
        this.estado = new Activo();
    }

    /**
     * Establece el estado actual del usuario.
     *
     * @param estado el nuevo estado del usuario.
     */
    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }

    /**
     * Retorna el estado actual del usuario.
     *
     * @return el estado actual.
     */
    public EstadoUsuario getEstado() {
        return estado;
    }

    /**
     * Retorna el nombre del usuario.
     *
     * @return el nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Solicita un préstamo, delegando la operación en el estado actual del usuario.
     */
    public void solicitarPrestamo() {
        estado.solicitarPrestamo(this);
    }

    /**
     * Devuelve un préstamo, delegando la operación en el estado actual del usuario.
     */
    public void devolverPrestamo() {
        estado.devolverPrestamo(this);
    }
}


