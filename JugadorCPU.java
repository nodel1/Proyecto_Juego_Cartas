import java.util.List;

public class JugadorCPU extends Jugador{
    public JugadorCPU(String n) {
        super(n);
    }

    @Override
    public Carta elegirCarta(Mesa m){

        for (Carta c : super.mano) {
            if (esUnoOdiez(c)) {
                if (m.puedeColocar(c)) {
                    super.mano.remove(c);
                    return c;
                }
            }
        }

        for (Carta c : super.mano) {
            if(esNueveOrDos(c)){
                if (m.puedeColocar(c)) {
                    if (tieneCartaSiguiente(c)) {
                        super.mano.remove(c);
                        return c;
                    }
                }
            }
        }

        for(Carta c: super.mano){
            if(m.puedeColocar(c) && !esNueveOrDos(c)){
                super.mano.remove(c);
                return c;
            }
        }

        String paloPrioritario = BuscarPaloPrioritario();

        for(Carta c: super.mano){
            if(m.puedeColocar(c) && c.getPalo().equals(paloPrioritario)){
                super.mano.remove(c);
                return c;
            }
        }

        for(Carta c: super.mano){           //aqui nunca deberia llegar por que el for anterior siempre te deberia sacar un palo prioritario
            if(m.puedeColocar(c)){          //pero por si acaso lo dejo          REVISAR/BORRAR
                super.mano.remove(c);
                return c;
            }
        }
        return null;
    }


    private boolean esNueveOrDos(Carta carta) {
        int valor = carta.getValor();
        return valor == 2 || valor == 9;
    }

    private boolean esUnoOdiez(Carta carta) {
        int valor = carta.getValor();
        return valor == 1 || valor == 10;
    }


    private boolean tieneCartaSiguiente(Carta carta) {
        int valorBuscado = 0;

        if(carta.getValor() == 2){
            valorBuscado = 1;
        }
        else if (carta.getValor() == 9) {
            valorBuscado = 10;
        }

        for (Carta c : super.mano) {
            if ((c.getPalo().equals(carta.getPalo())) && (valorBuscado == c.getValor())) {
                return true;
            }
        }
        return false;
    }

    private String BuscarPaloPrioritario() {
        int[] contador = new int[4];
        int max = 0;
        int paloPrioritario = 0;

        for (Carta c : super.mano) {
            String palo = c.getPalo();
            if (palo.equals("oros")) {
                contador[0]++;
            } else if (palo.equals("copas")) {
                contador[1]++;
            } else if (palo.equals("espadas")) {
                contador[2]++;
            } else if (palo.equals("bastos")) {
                contador[3]++;
            }
        }

        for (int i = 0; i < contador.length; i++) {
            if (contador[i] > max) {
                max = contador[i];
                paloPrioritario = i;
            }
        }

        if (paloPrioritario == 0) {
            return "oros";
        } else if (paloPrioritario == 1) {
            return "copas";
        } else if (paloPrioritario == 2) {
            return "espadas";
        } else if (paloPrioritario == 3) {
            return "bastos";
        } else {
            return "";            //no deberia salir nunca esto
        }
    }
}
