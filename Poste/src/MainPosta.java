
import java.util.ArrayList;


public class MainPosta {
    
    public static final int nSportelli = 3;
    
    public static void main(String[] args) {
        Clienti clienti = new Clienti();
        Thread arriviThread = new Thread(new Arrivi(clienti));
        ArrayList<Thread> sportelloThreadList = new ArrayList<>();
        arriviThread.start();
        for (int i = 0; i < nSportelli; i++) {
            Thread sportelloThread = new Thread(new Sportello(clienti, i + 1));
            sportelloThreadList.add(sportelloThread);
            sportelloThread.start();
        }
    }
}
