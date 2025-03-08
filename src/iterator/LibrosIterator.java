package iterator;

import java.util.List;

/**
 * Iterador para recorrer una colección de libros.
 * <p>
 * Esta clase implementa la interfaz {@link IteratorInterface} y permite iterar sobre
 * una lista de libros representados como {@code String}.
 * </p>
 */
public class LibrosIterator implements IteratorInterface {
    /**
     * Lista de libros sobre la cual se realizará la iteración.
     */
    private List<String> libros;
    
    /**
     * Posición actual en la iteración.
     */
    private int posicion = 0;

    /**
     * Construye un iterador para una lista de libros.
     *
     * @param libros la lista de libros a iterar.
     */
    public LibrosIterator(List<String> libros) {
        this.libros = libros;
    }

    /**
     * Verifica si existen más libros en la lista para iterar.
     *
     * @return {@code true} si aún quedan libros por iterar, {@code false} en caso contrario.
     */
    @Override
    public boolean hasNext() {
        return posicion < libros.size();
    }

    /**
     * Retorna el siguiente libro en la iteración.
     * <p>
     * Si no existen más libros, se retorna {@code null}.
     * </p>
     *
     * @return el siguiente libro como {@code Object} (normalmente un {@code String}), o {@code null}
     *         si no hay más elementos.
     */
    @Override
    public Object next() {
        if(hasNext()){
            return libros.get(posicion++);
        }
        return null;
    }
}

