/**
 * Enum (implementado como clase) que tiene los diferentes tipos de jugadas.
 */

package enumerados;

public class E_Jugada_Tipo {

    private final String nombre;

    private E_Jugada_Tipo (String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    public static final E_Jugada_Tipo PAREJA       = new E_Jugada_Tipo("Pareja");
    //TODO: Llenar con el resto de jugadas

}
