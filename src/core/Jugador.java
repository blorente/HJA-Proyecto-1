/**
 * Clase que se encargar� de:
 *  - Guardar las hole cards de un jugador
 *  - Guardar informaci�n sobre las jugadas del jugador con la mesa
 *         (se har�n con la uni�n de todas las jugadas de todas las posibles manos)
 *
 *  Por ahora se parece mucho a la clase Mano, pero cuando necesitemos puede extenderse m�s,
 *  para incluir omaha por ejemplo. Tamb�en, la diferencia es que este jugador
 *  NO tiene informaci�n sobre las cartas en la mesa, eso hay que pasarselo al analizador por separado.
 */

package core;

import core.exceptions.EManoLlena;

import java.util.List;

public class Jugador {

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
    public Jugador() {

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
    public void anadirCarta(Carta carta) throws EManoLlena {}

    /**
     * M�todo para poder asignar listas enteras de cartas a la mano.
     *
     * @param cartas
     * @throws EManoLlena
     */
    public void llenarMano(List<Carta> cartas) throws EManoLlena {}

    /**
     * M�todo que a�ade una jugada al jugador, con cuidado de que no est�n repetidas.
     * @param jugada
     */
    public void a�adirJugada(I_Jugada jugada) {}

    /**
     * M�todo que a�ade una lista de jugadas al jugador.
     * @param jugada
     */
    public void a�adirJugadas(List<I_Jugada> jugada) {}

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
