/**
 * Clase estática (no puede ser instanciada) que contiene métodos
 * para comparar y analizar jugadas, manos, o listas de cartas.
 *
 * Para llamarla, Analizador.analizarMano();
 *
 * Las funciones deberían mantenerse puras (que no cambien el input)
 * por si luuego queremos meter threads.
 */

package core;

import java.util.List;

public final class Analizador {

    //-----------------------
    // CAMPOS Y CONSTRUCTORES
    //-----------------------

    /**
     * Constructor privado que permite que esta sea una clase estática.
     */
    private Analizador() {

    }


    //-----------------------
    // MÉTODOS PROPIOS
    //-----------------------

    /**
     * Método que analiza la mano que le pasas, y devuelve una lusta de jugadas.
     * @param m Mano a analizar.
     * @return lista con todas la s jugadas que tiene esa mano (OEASD, Parejas, tríos...)
     *
     * Precondición: m tiene como máximo 5 cartas.
     * Postcondición: La lista de retorno tiene la mejor mano como primer elemento
     */
    public static List<Jugada> analizaMano(Mano m) {
        return null;
    }

    /**
     * Método que compara dos manos, y devuelve un float diciendo cuál es la mejor
     * @param m1
     * @param m2
     * @return Por ahora, devuelve [>0 si m1 > m2], [<0 si m1<m2], y [0 si m1=m2]
     */
    public static float comparaManos(Mano m1, Mano m2) {
        return 0.0f;
    }

    /**
     * Mismo métiodo que el anterior, pero en este caso compara dos listas de cartas.
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
