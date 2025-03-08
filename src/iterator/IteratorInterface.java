package iterator;

/**
 * Interfaz para implementar el patrón de iterador.
 * <p>
 * Define los métodos esenciales para iterar sobre una colección de elementos.
 * </p>
 */
public interface IteratorInterface {
    /**
     * Determina si existen más elementos en la colección.
     *
     * @return {@code true} si hay más elementos, de lo contrario {@code false}.
     */
    boolean hasNext();
    
    /**
     * Retorna el siguiente elemento de la colección.
     *
     * @return el siguiente elemento como {@code Object}.
     */
    Object next();
}

