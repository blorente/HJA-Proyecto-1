/**
 * Clase que se encargará de:
 *  - Guardar una mano de (máximo) 5 cartas
 *  - Guardar información sobre las jugadas de mano (si tiene parejas, draws, tríos...)
 */

package componentes;

import excepciones.EManoLlena;
import excepciones.ErrorTamLinea;

import java.util.ArrayList;
import java.util.List;

import constantes.Constantes;

public class Mano {

    //-----------------------
    // CAMPOS Y CONSTRUCTORES
    //-----------------------

    /**
     * Lista de (máximo) 5 cartas
     */
    private List<Carta> cartas;

    /**
     * Lista de jugadas que tiene la mano, ordenadas de mejor a peor.
     */
    private List<I_Jugada> jugadas;

    /**
     * Constructor vacío que inicializa las listas
     */
    public Mano() {
    	cartas = new ArrayList<Carta>();    	
    }

    //-----------------------
    // MÉTODOS PROPIOS
    //-----------------------

    /**
     * Método para añadir una carta a la mano.
     * Si la mano contiene ya 5 cartas, deberá lanzar una excepción de mano llena.
     *
     * @param carta: Carta para añadir.
     * @throws EManoLlena
     */
    public void anadirCarta(Carta carta) throws EManoLlena {
    	
    	int valorCarta, valorCartaDeLaLista, i = 0;
    	boolean encontrado = false;
    	
    	try {
    		if (cartas.size() == 5) {
        		throw new EManoLlena();
        	}
        	else { // Vamos añadir las cartas de manera ordenada, por lo tanto.
        		if(cartas.size() == 0) {
        			// La lista está vacía añadimos la carta directamente.
        			cartas.add(carta);
        		}
        		else {
	        		while((i < Constantes.MAX_CARTAS_MANO) && (!encontrado)) {
	        			valorCarta = carta.getValor().getValor(); // Nos da el entero de la carta.
	        			valorCartaDeLaLista = cartas.get(i).getValor().getValor(); // nos da el valor de la pos i.
	        			
	        			// Si el valor de la carta es menor igual al valor del array
	        			if(valorCarta <= valorCartaDeLaLista) {
	        				encontrado = true;
	        				
	        				// Copiamos el valor de i a i+1 y el nuevo valor donde i
	        				Carta cartaAuxiliar = cartas.get(i);
	        				//Borramos el que estaba en esa posicion.
	        				cartas.remove(i);
	        				// Añadimos el nuestro y luego el auxiliar.
	        				cartas.add(carta);
	        				cartas.add(cartaAuxiliar);
	        				
	        			}
	        			else
	        			{
	        				// Lo añades porque va el último, los anteriores han resultado ser menores que el nuevo valor.3
	        				cartas.add(carta);
	        			}
	        			
	        			i++;
	        		}
        		}
        	}
    	}
    	catch (EManoLlena e) {
    		System.out.println(e.getMessage());
    	}
    }

    /**
     * Método para poder asignar listas enteras de cartas a la mano.
     *
     * @param cartas
     * @throws EManoLlena
     */
    public void llenarMano(List<Carta> cartas) throws EManoLlena {
    	if (cartas.size() + this.cartas.size() > 5) {
    		throw new EManoLlena();
    	}
    	for (Carta carta : cartas) {
    		this.cartas.add(carta);
    	}
    }

    /**
     * Método que asigna una jugada a la mano, por ejemplo un FlushDraw
     * @param jugada
     */
    public void asignarJugada(I_Jugada jugada) {}

    //-----------------------
    // GETTERS Y SETTERS
    //-----------------------

    public List<Carta> getCartas() {
        return cartas;
    }

    public List<I_Jugada> getJugadas() {
        return jugadas;
    }

    /**
     * @return las cartas de la mano, en orden, en una string
     */
    @Override
    public String toString() {
        return "";
    }
}
