import java.util.Arrays;
import java.util.Random;

public class Baraja{
    private int nCartas;
    private Carta[] baraja;

    public Baraja(){
        this.nCartas=40;
        this.baraja=new Carta[nCartas];
        String[] palos={"oros","copas","espadas","bastos"};
        int contador = 0;
        for (String palo : palos){
            for (int i = 1; i < 11; i++){
                baraja[contador] = new Carta(palo, i);
                contador++;
            }
        }
    }
    public void barajar(){
        Carta aux;
        Random r;
        int n;
        int i=0;
        while(i<this.nCartas){
            r=new Random();
            n=r.nextInt(40);
            aux=this.baraja[i];
            this.baraja[i]=this.baraja[n];
            this.baraja[n]=aux;
            i++;
        }
    }
    public Carta sacaCarta(){
        this.nCartas--;
        return this.baraja[nCartas];
    }
    public int cartasRestantes(){
        return this.nCartas;
    }

    public String toString(){
        int i=0;
        String salida="";
        while(i<nCartas){
            salida=salida+this.baraja[i].toString()+"\n";
            i++;
        }
        return salida;
    }
}
