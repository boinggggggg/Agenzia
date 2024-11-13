
import java.util.ArrayList;

public class Clienti {
    
    private final ArrayList<Integer> listaN;
    private int ultimoArrivo;
    private int ultimoServito;
    private final int nMax = 100;
    
    public Clienti() {
        listaN = new ArrayList<>();
        ultimoArrivo = 0;
        ultimoServito = 0;
    }
    
    public synchronized Integer rimuoviC() throws InterruptedException {
        while (ultimoServito >= ultimoArrivo)
            wait();
        ultimoServito++;
        return ultimoServito;
    }
    
    public synchronized Integer addC() {
        if (ultimoArrivo < nMax) {
            ultimoArrivo++;
            listaN.add(ultimoArrivo);
            notifyAll();
            return ultimoArrivo;
        }
        return null;
    }
}