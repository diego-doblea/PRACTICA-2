package iterator;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.List;

/**
 * Iterador para recorrer una colección de audiolibros.
 * <p>
 * Esta clase implementa la interfaz {@link IteratorInterface} y permite iterar sobre
 * los valores de una tabla hash que contiene audiolibros.
 * </p>
 */
public class AudiolibrosIterator implements IteratorInterface {
    /**
     * Lista que contiene los audiolibros.
     */
    private List<String> audiolibrosList;
    
    /**
     * Posición actual en la iteración.
     */
    private int posicion = 0;

    /**
     * Construye un iterador de audiolibros a partir de una tabla hash.
     * <p>
     * Convierte los valores de la tabla hash a una lista para facilitar la iteración.
     * </p>
     *
     * @param audiolibros una {@link Hashtable} que contiene audiolibros, donde la clave es un {@code Integer}
     *                    y el valor es un {@code String}.
     */
    public AudiolibrosIterator(Hashtable<Integer, String> audiolibros) {
        // Convertir los valores de la tabla hash a una lista para facilitar la iteración
        audiolibrosList = new ArrayList<>(audiolibros.values());
    }

    /**
     * Indica si quedan audiolibros por iterar.
     *
     * @return {@code true} si existen más audiolibros en la lista, {@code false} en caso contrario.
     */
    @Override
    public boolean hasNext() {
        return posicion < audiolibrosList.size();
    }

    /**
     * Retorna el siguiente audiolibro en la iteración.
     * <p>
     * Si no existen más elementos, se retorna {@code null}.
     * </p>
     *
     * @return el siguiente audiolibro como {@code Object} (usualmente un {@code String}),
     *         o {@code null} si no hay más elementos.
     */
    @Override
    public Object next() {
        if(hasNext()){
            return audiolibrosList.get(posicion++);
        }
        return null;
    }
}

