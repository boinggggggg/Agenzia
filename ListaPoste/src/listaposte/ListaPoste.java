import java.util.ArrayList; 

public class Clienti{
    
    private final ArrayList<Integer> listaN;
    private int ultimoArrivo;
    private int ultimoServito;
    private final int nMax = 100;
    
    public Clienti(){
        listaN = new ArrayList<>();
        ultimoArrivo = 0;
        ultimoServito = 0;
    }
    
    public synchronized Integer rimuoviC() throws InterruptedException {
        while(ultimoServito >= ultimoArrivo)
            wait();
        ultimoServito++;
        return ultimoServito;
    }
    
    public synchronized Integer addC(){
        if(ultimoArrivo < nMax) {
            ultimoArrivo++;
            listaN.add(ultimoArrivo);
            notifyAll();
            return ultimoArrivo;
        }
        return null;
    }
}

public class Arrivi implements Runnable{
    private final Clienti clienti;
    private final int attesaArrivi = 2000;
    
    public Arrivi(Clienti clienti) {
        this.clienti = clienti;
    }
    
    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                Thread.sleep(attesaArrivi);
                Integer clienteArrivato = clienti.addC();
                if (clienteArrivato == null) break;
                System.out.println("Cliente n." + clienteArrivato + " è arrivato.");
            }
        }catch (InterruptedException e) {
            System.out.println("Thread interrotto durante la fase sleep.");
        }finally {
            System.out.println("Posta chiusa.");
        }
    }
}

public class Sportello implements Runnable {
    private final Clienti clienti;
    private final int tMax = 12000;
    private final int tMin = 6000;
    private final int nSportello;
    
    public Sportello (Clienti clienti, int nSportello) {
        this.clienti = clienti;
        this.nSportello = nSportello;
    }
    
    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                Integer cServito = clienti.rimuoviC();
                System.out.println("Chiamato cliente n." + cServito + "dallo sportello n." + nSportello);
                int tServizio = (int) (Math.random() * (tMax - tMin + 1) + tMin);
                Thread.sleep(tServizio);
                System.out.println("Servito cliente n." + cServito + "dallo sportello n." + nSportello);
            }
        }catch (InterruptedException e) {
            System.out.println("Thread interrotto durante la fase sleep.");
        }finally {
            System.out.println("Sportello chiuso.");
        }
    }
}

public class MainPosta {
    
    public static final int nSportelli = 3;
    
    public static void main (String[] args) {
        Clienti clienti = new clienti();
        Thread arriviThread = new Thread(new Arrivi(clienti));
        ArrayList<Thread> sportelloThreadList = new ArrayList<Thread>();
        arriviThread.start();
        for(int i=0;i<nSportelli;i++) {
            Thread sportelloThread = new Thread(new Sportello(clienti, i+1));
            sportelloThreadList.add(sportelloThread);
            sportelloThread.start();
        }
    }
}


