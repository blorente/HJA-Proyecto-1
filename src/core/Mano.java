/**
 * Clase que se encargar� de:
 *  - Guardar una mano de (m�ximo) 5 cartas
 *  - Guardar informaci�n sobre las jugadas de mano (si tiene parejas, draws, tr�os...)
 */

package core;

import core.exceptions.EManoLlena;

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
    private List<Jugada> jugadas;

    /**
     * Constructor vac�o que inicializa las listas
     */
    public Mano() {

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
     * M�todo que asigna una jugada a la mano, por ejemplo un FlushDraw
     * @param jugada
     */
    public void asignarJugada(Jugada jugada) {}

    //-----------------------
    // GETTERS Y SETTERS
    //-----------------------

    public List<Carta> getCartas() {
        return cartas;
    }

    public List<Jugada> getJugadas() {
        return jugadas;
    }

}
