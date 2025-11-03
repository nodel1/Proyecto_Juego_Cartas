public class Practica1 {
    public static void main(String[] args) {
        Partida p = new Partida();

        while(!p.partidaFinalizada()){
            System.out.println("==========================================");
            p.realizarJugada();
        }
        p.mostrarResultados();
    }
}
