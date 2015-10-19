/**
 * Clase que se encargará de:
 *  - Guardar una mano de (máximo) 5 cartas
 *  - Guardar información sobre las jugadas de mano (si tiene parejas, draws, tríos...)
 */

package core;

import core.exceptions.EManoLlena;

import java.util.List;

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
    private List<Jugada> jugadas;

    /**
     * Constructor vacío que inicializa las listas
     */
    public Mano() {

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
    public void anadirCarta(Carta carta) throws EManoLlena {}

    /**
     * Método para poder asignar listas enteras de cartas a la mano.
     *
     * @param cartas
     * @throws EManoLlena
     */
    public void llenarMano(List<Carta> cartas) throws EManoLlena {}

    /**
     * Método que asigna una jugada a la mano, por ejemplo un FlushDraw
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
