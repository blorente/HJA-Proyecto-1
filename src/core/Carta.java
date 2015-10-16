package core;

public class Carta {

    private E_Carta_Palo palo;
    private E_Carta_Valor valor;

    Carta(E_Carta_Palo palo, E_Carta_Valor valor) {
        this.palo = palo;
        this.valor = valor;
    }

    public E_Carta_Palo getPalo() {
        return palo;
    }

    public E_Carta_Valor getValor() {
        return valor;
    }
}
