import java.util.Scanner;

public class JugadorHumano extends Jugador{
    public JugadorHumano(String n) {
        super(n);
    }

    @Override
    public Carta elegirCarta(Mesa m){
        Scanner entrada = new Scanner(System.in);
        super.mostrarCartas();
        System.out.println("Elige carta por indice: \n");
        int i = entrada.nextInt();
        if(i<super.mano.size()) {
            Carta c = super.mano.get(i);
            if(!m.puedeColocar(c)){
                System.out.println("No se puede colocar dicha carta");
                return this.elegirCarta(m);
            }
            else{
                super.mano.remove(c);
                return c;
            }
        }
        else{
            System.out.println("Error: no tienes ninguna carta con ese indice");
            return this.elegirCarta(m);
        }
    }
}