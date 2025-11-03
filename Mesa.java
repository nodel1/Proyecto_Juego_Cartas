import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Mesa {   //hacer vector de deques y monton realmente sobra
    private Deque<Carta> oros;
    private Deque<Carta> espadas;
    private Deque<Carta> bastos;
    private Deque<Carta> copas;

    public Mesa(){
        oros = new ArrayDeque<>();
        espadas = new ArrayDeque<>();
        bastos = new ArrayDeque<>();
        copas = new ArrayDeque<>();
    }
    public void colocar(Carta c){
        if(c.getValor() >= 5){
            if(c.getPalo().equals("bastos")){
                this.bastos.addLast(c);
            }
            if(c.getPalo().equals("oros")){
                this.oros.addLast(c);
            }
            if(c.getPalo().equals("espadas")){
                this.espadas.addLast(c);
            }
            if(c.getPalo().equals("copas")){
                this.copas.addLast(c);
            }
        }
        if(c.getValor() < 5){
            if(c.getPalo().equals("bastos")){
                this.bastos.addFirst(c);
            }
            if(c.getPalo().equals("oros")){
                this.oros.addFirst(c);
            }
            if(c.getPalo().equals("espadas")){
                this.espadas.addFirst(c);
            }
            if(c.getPalo().equals("copas")){
                this.copas.addFirst(c);
            }
        }
    }





    public boolean puedeColocar(Carta c){
        String[] nombresPalos = {"oros", "espadas", "bastos", "copas"};

        if(c.getValor() == 5) {
            return true;
        } else {
            for (String palo : nombresPalos) {
                if (c.getPalo().equals(palo)) {
                    Deque<Carta> dequePalo = null;

                    switch (palo) {
                        case "oros":
                            dequePalo = oros;
                            break;
                        case "espadas":
                            dequePalo = espadas;
                            break;
                        case "bastos":
                            dequePalo = bastos;
                            break;
                        case "copas":
                            dequePalo = copas;
                            break;
                    }

                    if (dequePalo.isEmpty()) {
                        return false;
                    } else {
                        if (dequePalo.peekFirst().getValor() == c.getValor() + 1 || dequePalo.peekLast().getValor() == c.getValor() - 1) {
                            return true;
                        }
                    }
                }
            }
        }

        return false; //no deberia llegar aqui nunca
    }



    public void mostrarMesa() {
        System.out.println("Mesa:");
        System.out.println("Oros: " + oros);
        System.out.println("Espadas: " + espadas);
        System.out.println("Bastos: " + bastos);
        System.out.println("Copas: " + copas);
    }

}
