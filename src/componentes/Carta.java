/**
 * Clase para representar una carta individual.
 * Contiene información sobre el palo y el valor de la carta.
 * E.G: La carta Ah se inicializaría con
 *      new Carta(E_Carta_Palo.HEARTS, E_Carta_Valor.A)
 *              ´o bien
 *      CartaFactory.crearCarta(String palo, int valor);
 */

package componentes;

import enumerados.E_Carta_Palo;
import enumerados.E_Carta_Valor;

public class Carta {

    private E_Carta_Valor valor;
    private E_Carta_Palo palo;

    public Carta(E_Carta_Valor valor, E_Carta_Palo palo) {
        this.valor = valor;
        this.palo = palo;
    }

    public E_Carta_Valor getValor() {
        return valor;
    }

    public E_Carta_Palo getPalo() {
        return palo;
    }
    
    public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append(this.valor);
    	builder.append(this.palo);
		return builder.toString();
    }
}
