/**
 * Clase que se encargar� de:
 *  - Guardar una mano de (m�ximo) 5 cartas
 *  - Guardar informaci�n sobre las jugadas de mano (si tiene parejas, draws, tr�os...)
 */

package componentes;

import excepciones.EManoLlena;

import java.util.ArrayList;
import java.util.List;

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
    	if (cartas.size() == 5)
    		throw new EManoLlena();
    	
    	cartas.add(carta);
    	
    	/*
    	ArrayList<Carta> listaAux = new ArrayList<Carta>(cartas);
    	int valorCarta = carta.getValor().getValor(), valorCartaDeLaLista;
    	boolean insertado = false;
    	try {
    		if (cartas.size() == 5)
        		throw new EManoLlena();
    		
        	// Vamos a�adir las cartas de manera ordenada, por lo tanto.
        	if(cartas.size() == 0) {
        		// La lista est� vac�a a�adimos la carta directamente.
        		cartas.add(carta);
        	}
        	else {
        		cartas.clear();
	        	while(!listaAux.isEmpty()) {
	        		valorCartaDeLaLista = listaAux.get(0).getValor().getValor(); // para el valor del primer elemento en la lista
	        		// Si el valor de la carta a a�adir es mayor que el de la carta en la primera pos de la lista (que esta ordenada)
	        		if(valorCarta > valorCartaDeLaLista && !insertado) {
	        			insertado = true;
	        			cartas.add(carta);
	        		}
	        		cartas.add(listaAux.get(0));
	        		listaAux.remove(0);
	        	}
	        	if (!insertado) // si la carta es <= que todos los elementos de la lista no se habr� insertado
	        		cartas.add(carta);
        	}
        } catch (EManoLlena e) {
    		System.out.println(e.getMessage());
    	}
    	*/
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
    	StringBuilder strBuild = new StringBuilder();
    	for (int i = 0; i < cartas.size(); i++)
    		strBuild.append(cartas.get(i).toString());
        return strBuild.toString();
    }
}
