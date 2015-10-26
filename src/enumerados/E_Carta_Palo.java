/**
 * Enum (implementado como clase)
 * para identificar el palo de cada carta,
 * con métodos similares a E_Carta_Valor.java
 */

package enumerados;

public class E_Carta_Palo {

    private final String nombre;

    private E_Carta_Palo (String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    public static final E_Carta_Palo CLUBS       = new E_Carta_Palo("c");
    public static final E_Carta_Palo HEARTS      = new E_Carta_Palo("h");
    public static final E_Carta_Palo DIAMONDS    = new E_Carta_Palo("d");
    public static final E_Carta_Palo SPADES      = new E_Carta_Palo("s");

}
