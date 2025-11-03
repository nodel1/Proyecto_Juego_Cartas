public class Carta {

    private String palo;

    private int valor;

    public Carta (String palo, int valor){

        this.palo = palo;
        this.valor = valor;

    }

    public int getValor() {
        return valor;
    }

    public String getPalo() {
        return palo;
    }

    public String toString() {
        return valor +  " de " + palo ;
    }
}
