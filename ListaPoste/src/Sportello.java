public class Sportello implements Runnable {
    private final Clienti clienti;
    private final int tMax = 12000;
    private final int tMin = 6000;
    private final int nSportello;
    
    public Sportello(Clienti clienti, int nSportello) {
        this.clienti = clienti;
        this.nSportello = nSportello;
    }
    
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Integer cServito = clienti.rimuoviC();
                System.out.println("Chiamato cliente n." + cServito + " dallo sportello n." + nSportello);
                int tServizio = (int) (Math.random() * (tMax - tMin + 1) + tMin);
                Thread.sleep(tServizio);
                System.out.println("Servito cliente n." + cServito + " dallo sportello n." + nSportello);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrotto durante la fase sleep.");
        } finally {
            System.out.println("Sportello chiuso.");
        }
    }
}
