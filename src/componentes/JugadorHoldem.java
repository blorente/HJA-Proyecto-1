/**
 * Clase que se encargará de:
 *  - Guardar las hole cards de un jugador
 *  - Guardar información sobre las jugadas del jugador con la mesa
 *         (se harán con la unión de todas las jugadas de todas las posibles manos)
 *
 *  Por ahora se parece mucho a la clase Mano, pero cuando necesitemos puede extenderse más,
 *  para incluir omaha por ejemplo. Tambíen, la diferencia es que este jugador
 *  NO tiene información sobre las cartas en la mesa, eso hay que pasarselo al analizador por separado.
 */

package componentes;

import excepciones.EManoLlena;

import java.util.ArrayList;
import java.util.List;

public class JugadorHoldem implements I_Jugador {

    //-----------------------
    // CAMPOS Y CONSTRUCTORES
    //-----------------------

    /**
     * Lista de (máximo) 2 cartas
     */
    private List<Carta> cartas;

    /**
     * Lista de jugadas que tiene la mano, ordenadas de mejor a peor.
     */
    private List<JugadaValor> jugadas;
    
    /**
     * ID del jugador
     */
    private String id;

    /**
     * Constructor vacío que inicializa las listas
     */
    public JugadorHoldem() {
    	cartas = new ArrayList<Carta>();
    }

    //-----------------------
    // MÉTODOS PROPIOS
    //-----------------------

    /**
     * Método para añadir una carta a la mano.
     * Si la mano contiene ya 2 cartas, deberá lanzar una excepción de mano llena.
     *
     * @param carta: Carta para añadir.
     * @throws EManoLlena
     */
    @Override
    public void anadirCarta(Carta carta) throws EManoLlena {
    	if (this.cartas.size() == 2) {
            throw new EManoLlena();
        } else {
            this.cartas.add(carta);
        }
    }

    /**
     * Método para poder asignar listas enteras de cartas a la mano.
     *
     * @param cartas
     * @throws EManoLlena
     */
    @Override
    public void llenarMano(List<Carta> cartas) throws EManoLlena {}

    /**
     * Método que añade una jugada al jugador, con cuidado de que no estén repetidas.
     * @param jugada
     */
    @Override
    public void anadirJugada(I_Jugada jugada) {}

    /**
     * Método que añade una lista de jugadas al jugador.
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

    public List<JugadaValor> getJugadas() {
        return jugadas;
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
