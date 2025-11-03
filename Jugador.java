import java.util.ArrayList;
import java.util.List;

public abstract class Jugador {
    private String nombre;
    protected List<Carta> mano;

    public Jugador(String n){
        this.nombre = n;
        this.mano = new ArrayList<Carta>();
    }

    public String getNombre() {
        return this.nombre;
    }
    public boolean puedeJugar(Mesa m){
        for(Carta c: this.mano){
            if(m.puedeColocar(c)){
                return true;
            }
        }
        return false;
    }
    public boolean tieneCarta(Carta c){
        for(Carta c1: this.mano){
            if(c1.getPalo().equals(c.getPalo()) && c1.getValor()==c.getValor()){
                return true;
            }
        }
        return false;
    }

    public int numeroCartas(){
        return this.mano.size();
    }
    public boolean haAcabado(){
        return this.mano.isEmpty();
    }
    public void mostrarCartas(){
        String s = "[";
        System.out.println("Cartas de "+this.nombre+":\n");
        int i = 0;
        for(Carta c : this.mano){
            s = s+c.toString()+"("+i+") ";
            i++;
        }
        s = s+"]";
        System.out.println(s);
    }
   /* public Carta tieneCincoOros(){
        for(Carta c : this.mano){
            if(c.getPalo().equals("oros") && c.getValor()==5){
                return c;
            }
        }
        return null;
    }*/
    public abstract Carta elegirCarta(Mesa m);
}
