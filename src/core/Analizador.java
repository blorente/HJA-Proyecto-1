/**
 * Clase est�tica (no puede ser instanciada) que contiene m�todos
 * para comparar y analizar jugadas, manos, o listas de cartas.
 *
 * Para llamarla, Analizador.analizarMano();
 *
 * Las funciones deber�an mantenerse puras (que no cambien el input)
 * por si luuego queremos meter threads.
 */

package core;

import java.util.List;

public final class Analizador {

    //-----------------------
    // CAMPOS Y CONSTRUCTORES
    //-----------------------

    /**
     * Constructor privado que permite que esta sea una clase est�tica.
     */
    private Analizador() {

    }


    //-----------------------
    // M�TODOS PROPIOS
    //-----------------------

    /**
     * M�todo que analiza la mano que le pasas, y devuelve una lusta de jugadas.
     * @param m Mano a analizar.
     * @return lista con todas la s jugadas que tiene esa mano (OEASD, Parejas, tr�os...)
     *
     * Precondici�n: m tiene como m�ximo 5 cartas.
     * Postcondici�n: La lista de retorno tiene la mejor mano como primer elemento
     */
    public static List<Jugada> analizaMano(Mano m) {
        return null;
    }

    /**
     * M�todo que compara dos manos, y devuelve un float diciendo cu�l es la mejor
     * @param m1
     * @param m2
     * @return Por ahora, devuelve [>0 si m1 > m2], [<0 si m1<m2], y [0 si m1=m2]
     */
    public static float comparaManos(Mano m1, Mano m2) {
        return 0.0f;
    }

    /**
     * Mismo m�tiodo que el anterior, pero en este caso compara dos listas de cartas.
     *
     * IMPORTANTE: Creo que para esto lo mejor es coger las cartas de 5 en 5,
     *              como dijo en clase, y compararlas usando comparaManos();
     *
     * @param l1
     * @param l2
     * @return el mismo que comparaManos()
     */
    public static float comparaListas (List<Carta> l1, List<Carta> l2) {
        return 0.0f;
    }

}
