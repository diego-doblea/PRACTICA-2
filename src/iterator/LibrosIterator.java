package iterator;

import java.util.List;

public class LibrosIterator implements IteratorInterface {
    private List<String> libros;
    private int posicion = 0;

    public LibrosIterator(List<String> libros) {
        this.libros = libros;
    }

    @Override
    public boolean hasNext() {
        return posicion < libros.size();
    }

    @Override
    public Object next() {
        if(hasNext()){
            return libros.get(posicion++);
        }
        return null;
    }
}

