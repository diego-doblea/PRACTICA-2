package iterator;

import java.util.Hashtable;
import java.util.List;

public class ColeccionMaterial {
    private List<String> libros;
    private String[] revistas;
    private Hashtable<Integer, String> audiolibros;

    public ColeccionMaterial(List<String> libros, String[] revistas, Hashtable<Integer, String> audiolibros) {
        this.libros = libros;
        this.revistas = revistas;
        this.audiolibros = audiolibros;
    }

    public IteratorInterface getLibrosIterator() {
        return new LibrosIterator(libros);
    }

    public IteratorInterface getRevistasIterator() {
        return new RevistasIterator(revistas);
    }

    public IteratorInterface getAudiolibrosIterator() {
        return new AudiolibrosIterator(audiolibros);
    }
}

