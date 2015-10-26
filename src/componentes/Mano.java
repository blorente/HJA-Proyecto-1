/**
 * Clase que se encargar� de:
 *  - Guardar una mano de (m�ximo) 5 cartas
 *  - Guardar informaci�n sobre las jugadas de mano (si tiene parejas, draws, tr�os...)
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
     * Lista de (m�ximo) 5 cartas
     */
    private List<Carta> cartas;

    /**
     * Lista de jugadas que tiene la mano, ordenadas de mejor a peor.
     */
    private List<I_Jugada> jugadas;

    /**
     * Constructor vac�o que inicializa las listas
     */
    public Mano() {
    	cartas = new ArrayList<Carta>();    	
    }

    //-----------------------
    // M�TODOS PROPIOS
    //-----------------------

    /**
     * M�todo para a�adir una carta a la mano.
     * Si la mano contiene ya 5 cartas, deber� lanzar una excepci�n de mano llena.
     *
     * @param carta: Carta para a�adir.
     * @throws EManoLlena
     */
    public void anadirCarta(Carta carta) throws EManoLlena {
    	
    	int valorCarta, valorCartaDeLaLista, i = 0;
    	boolean encontrado = false;
    	
    	try {
    		if (cartas.size() == 5) {
        		throw new EManoLlena();
        	}
        	else { // Vamos a�adir las cartas de manera ordenada, por lo tanto.
        		if(cartas.size() == 0) {
        			// La lista est� vac�a a�adimos la carta directamente.
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
	        				// A�adimos el nuestro y luego el auxiliar.
	        				cartas.add(carta);
	        				cartas.add(cartaAuxiliar);
	        				
	        			}
	        			else
	        			{
	        				// Lo a�ades porque va el �ltimo, los anteriores han resultado ser menores que el nuevo valor.3
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
     * M�todo para poder asignar listas enteras de cartas a la mano.
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
     * M�todo que asigna una jugada a la mano, por ejemplo un FlushDraw
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
