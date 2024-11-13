public class Arrivi implements Runnable {
    private final Clienti clienti;
    private final int attesaArrivi = 2000;
    
    public Arrivi(Clienti clienti) {
        this.clienti = clienti;
    }
    
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Thread.sleep(attesaArrivi);
                Integer clienteArrivato = clienti.addC();
                if (clienteArrivato == null) break;
                System.out.println("Cliente n." + clienteArrivato + " è arrivato.");
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrotto durante la fase sleep.");
        } finally {
            System.out.println("Posta chiusa.");
        }
    }
}

