package iterator;

public class RevistasIterator implements IteratorInterface {
    private String[] revistas;
    private int posicion = 0;

    public RevistasIterator(String[] revistas) {
        this.revistas = revistas;
    }

    @Override
    public boolean hasNext() {
        return posicion < revistas.length;
    }

    @Override
    public Object next() {
        if(hasNext()){
            return revistas[posicion++];
        }
        return null;
    }
}

