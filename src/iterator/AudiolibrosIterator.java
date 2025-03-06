package iterator;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.List;

public class AudiolibrosIterator implements IteratorInterface {
    private List<String> audiolibrosList;
    private int posicion = 0;

    public AudiolibrosIterator(Hashtable<Integer, String> audiolibros) {
        // Convertir los valores de la tabla hash a una lista para facilitar la iteración
        audiolibrosList = new ArrayList<>(audiolibros.values());
    }

    @Override
    public boolean hasNext() {
        return posicion < audiolibrosList.size();
    }

    @Override
    public Object next() {
        if(hasNext()){
            return audiolibrosList.get(posicion++);
        }
        return null;
    }
}

