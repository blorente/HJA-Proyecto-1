/**
 * Clase para representar una carta individual.
 * Contiene información sobre el palo y el valor de la carta.
 * E.G: La carta Ah se inicializaría con
 *      new Carta(E_Carta_Palo.HEARTS, E_Carta_Valor.A)
 *              ´o bien
 *      CartaFactory.crearCarta(String palo, int valor);
 */

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
