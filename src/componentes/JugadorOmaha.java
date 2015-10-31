package componentes;

import java.util.ArrayList;
import java.util.List;

import excepciones.EManoLlena;

public class JugadorOmaha implements I_Jugador{
	
	//-----------------------
    // CAMPOS Y CONSTRUCTORES
    //-----------------------
	
	/**
     * Lista de (m�ximo) 4 cartas
     */
    private List<Carta> cartas;

    /**
     * Jugada que tiene la mano
     */
    private JugadaValor jugada;
    
    /**
     * ID del jugador
     */
    private String id;
	
	public JugadorOmaha() {
		cartas = new ArrayList<Carta>();
	}
	
	//-----------------------
    // M�TODOS PROPIOS
    //-----------------------

	/**
     * M�todo para a�adir una carta a la mano.
     * Si la mano contiene ya 4 cartas, deber� lanzar una excepci�n de mano llena.
     *
     * @param carta: Carta para a�adir.
     * @throws EManoLlena
     */
    @Override
    public void anadirCarta(Carta carta) throws EManoLlena {
    	if (this.cartas.size() == 4) {
            throw new EManoLlena();
        } else {
            this.cartas.add(carta);
        }
    }

    /**
     * M�todo para poder asignar listas enteras de cartas a la mano.
     *
     * @param cartas
     * @throws EManoLlena
     */
    @Override
    public void llenarMano(List<Carta> cartas) throws EManoLlena {}

    /**
     * M�todo que a�ade una jugada al jugador, con cuidado de que no est�n repetidas.
     * @param jugada
     */
    @Override
    public void anadirJugada(I_Jugada jugada) {}

    /**
     * M�todo que a�ade una lista de jugadas al jugador.
     * @param jugada
     */
    @Override
    public void anadirJugadas(List<I_Jugada> jugada) {}

    //-----------------------
    // GETTERS Y SETTERS
    //-----------------------

    public List<Carta> getCartas() {
        return cartas;
    }
    
    public void setJugada(JugadaValor jugada) {
		this.jugada = jugada;
	}

	public JugadaValor getJugada() {
		return this.jugada;
	}
    
    public String getId() {
    	return id;
    }
    
    public void setId(String id) {
    	this.id = id;
    }

    /**
     * @return las cartas de la mano, en orden, en una string
     */
    @Override
    public String toString() {
        return "";
    }

	

}
