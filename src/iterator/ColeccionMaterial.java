package iterator;

import java.util.Hashtable;
import java.util.List;

/**
 * Representa una colección de materiales que incluye libros, revistas y audiolibros.
 * <p>
 * Esta clase proporciona métodos para obtener iteradores específicos para cada tipo de material.
 * </p>
 */
public class ColeccionMaterial {
    /**
     * Lista de libros.
     */
    private List<String> libros;
    
    /**
     * Arreglo de revistas.
     */
    private String[] revistas;
    
    /**
     * Tabla hash que contiene audiolibros, donde la clave es un {@code Integer} y el valor es un {@code String}.
     */
    private Hashtable<Integer, String> audiolibros;

    /**
     * Construye una colección de materiales con libros, revistas y audiolibros.
     *
     * @param libros      lista de libros representados como {@code String}.
     * @param revistas    arreglo de revistas representadas como {@code String}.
     * @param audiolibros tabla hash de audiolibros con claves {@code Integer} y valores {@code String}.
     */
    public ColeccionMaterial(List<String> libros, String[] revistas, Hashtable<Integer, String> audiolibros) {
        this.libros = libros;
        this.revistas = revistas;
        this.audiolibros = audiolibros;
    }

    /**
     * Retorna un iterador para recorrer la colección de libros.
     *
     * @return un objeto que implementa {@link IteratorInterface} para iterar sobre la lista de libros.
     */
    public IteratorInterface getLibrosIterator() {
        return new LibrosIterator(libros);
    }

    /**
     * Retorna un iterador para recorrer la colección de revistas.
     *
     * @return un objeto que implementa {@link IteratorInterface} para iterar sobre el arreglo de revistas.
     */
    public IteratorInterface getRevistasIterator() {
        return new RevistasIterator(revistas);
    }

    /**
     * Retorna un iterador para recorrer la colección de audiolibros.
     *
     * @return un objeto que implementa {@link IteratorInterface} para iterar sobre la tabla hash de audiolibros.
     */
    public IteratorInterface getAudiolibrosIterator() {
        return new AudiolibrosIterator(audiolibros);
    }
}

