/**
 * Implementacion simple de I_Jugada,
 * que solo debe contener una String con el nombre completo de la jugada.
 *
 * Ejemplos de llamar al constructor:
 *  - listaJugadas.add(new JugadaSimple("Best hand: Pair of Aces (AhAc)");
 *  - I_Jugada jugada = new JugadaSimple("Draw: Flush");
 */

package componentes;

public class JugadaSimple implements I_Jugada {

   private String nombre;

    public JugadaSimple(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}