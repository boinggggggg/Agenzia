import java.util.ArrayList;

class Bus{
    private String ngruppo;
    private int tempo = 200;
    private ArrayList<Gruppo> arrivati;
    
    public Bus(int ngruppo){
        this.ngruppo = ngruppo;
        arrivati= new ArrayList<>();
    }
    
    public String Trasporta(){
        for(int i=1; i<3; i++){
            Gruppo g = new Gruppo("Gruppo" + ngruppo);
            arrivati.add(g);
            return "Gruppo" + g.getNaz() + "è arrivato al porto";
        }
        return null;
    }
}
