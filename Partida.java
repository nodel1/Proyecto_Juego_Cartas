import java.util.Random;
import java.util.Scanner;

public class Partida {
    private Baraja baraja;
    private Mesa mesa;
    private Jugador[] jugadores;
    private int turno;

    public Partida (){

        baraja = new Baraja();
        mesa = new Mesa();


        Scanner entrada = new Scanner(System.in);
        System.out.println("Elige el numero de jugadores de la partida: \n");
        int i = Integer.parseInt(entrada.nextLine());

        jugadores = new Jugador[i];

        if(i > 4){
            while (i > 4 ){
                System.out.println("ERROR: El maximo de jugadores de la partida es 4\n");
                System.out.println("Elige el numero de jugadores de la partida: \n");
                i = Integer.parseInt(entrada.nextLine());
            }
        }
        int j = 0;
        int k = 1;
        while(j < i){
            System.out.println("Introduce 1 para añadir humano\nIntroduce 0 para añadir CPU");
            int select = Integer.parseInt(entrada.nextLine());
            if(select==1){
                System.out.println("Introduce nombre:");
                this.jugadores[j] = new JugadorHumano(entrada.nextLine());
            }
            if(select==0){
                this.jugadores[j] = new JugadorCPU("CPU"+k);
                k++;
            }
            j++;
        }
        baraja.barajar();
        j = 0;
        while(j<this.jugadores.length){
            for(int x = 0; x < 10; x++){
                this.jugadores[j].mano.add(baraja.sacaCarta());//metodo jugador

            }
            j++;
        }

        this.turno = this.primerJugador();
        /*if(this.jugadores[turno].tieneCincoOros()!=null){
            this.mesa.colocar(this.jugadores[turno].tieneCincoOros());
            this.jugadores[turno].mano.remove(this.jugadores[turno].tieneCincoOros());
            System.out.println(this.jugadores[turno].getNombre()+" tiene el 5 de oros, empieza con esa carta");
            this.turno = (this.turno +1) % this.jugadores.length;
        }
        else{
            System.out.println("Nadie tiene 5 de oros, el primer turno sera aleatorio");
        }*/
    }

    public void realizarJugada(){
        Jugador jugadorActual = this.jugadores[this.turno];
        System.out.println("Turno de "+jugadorActual.getNombre()+"\nEstado actual de la mesa: ");
        this.mesa.mostrarMesa();
        if (jugadorActual.puedeJugar(this.mesa)){
            Carta cartaJugar = jugadorActual.elegirCarta(this.mesa);
            mesa.colocar(cartaJugar);
            System.out.println(jugadorActual.getNombre()+" ha sacado la carta "+cartaJugar.toString());
        }
        else {
            if(this.baraja.cartasRestantes()!=0){ //baraja.numCartas>0
                jugadorActual.mano.add(this.baraja.sacaCarta()); //robarCarta(baraja)
                System.out.println(jugadorActual.getNombre() + " no puede jugar, robara y pasará turno.\n");
            }
            else {
                System.out.println(jugadorActual.getNombre() + " no puede jugar ni robar, pasará turno.\n");
            }
        }
        System.out.println("==========================================");
        this.turno = (this.turno +1) % this.jugadores.length;
    }

    public boolean partidaFinalizada() {
        for(Jugador jugador : jugadores){
            if(jugador.haAcabado()){
                return true;
            }
        }
        return false;
    }

    public String Ganador () {
        for(Jugador jugador : jugadores){
            if(jugador.haAcabado()){
                return jugador.getNombre();
            }
        }
        return "La partida no ha finalizado";
    }

    public void mostrarResultados(){

        System.out.println("Ganador: " + this.Ganador() + "\n");
        for(Jugador jugador : jugadores){
            System.out.println(jugador.getNombre() + " le quedan " + jugador.numeroCartas() + " cartas");
        }
    }
    public int primerJugador(){
        Random r = new Random();
        for(int i = 0; i<this.jugadores.length; i++){
            if(this.jugadores[i].tieneCarta(new Carta("oros",5))){
                return i;
            }
        }
        return r.nextInt(this.jugadores.length);
    }
}
