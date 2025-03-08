package iterator;

/**
 * Iterador para recorrer una colección de revistas.
 * <p>
 * Esta clase implementa la interfaz {@link IteratorInterface} y permite iterar sobre
 * un arreglo de revistas representadas como {@code String}.
 * </p>
 */
public class RevistasIterator implements IteratorInterface {
    /**
     * Arreglo de revistas sobre el que se realiza la iteración.
     */
    private String[] revistas;
    
    /**
     * Posición actual en la iteración.
     */
    private int posicion = 0;

    /**
     * Construye un iterador para un arreglo de revistas.
     *
     * @param revistas arreglo de revistas a iterar.
     */
    public RevistasIterator(String[] revistas) {
        this.revistas = revistas;
    }

    /**
     * Verifica si existen más revistas en el arreglo para iterar.
     *
     * @return {@code true} si aún quedan revistas por iterar, {@code false} en caso contrario.
     */
    @Override
    public boolean hasNext() {
        return posicion < revistas.length;
    }

    /**
     * Retorna la siguiente revista en la iteración.
     * <p>
     * Si no existen más revistas, se retorna {@code null}.
     * </p>
     *
     * @return la siguiente revista como {@code Object} (normalmente un {@code String}),
     *         o {@code null} si no hay más elementos.
     */
    @Override
    public Object next() {
        if(hasNext()){
            return revistas[posicion++];
        }
        return null;
    }
}

