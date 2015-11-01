/**
 * Enum (implementado como clase)
 * para identificar el palo de cada carta,
 * con métodos similares a E_Carta_Valor.java
 */

package enumerados;

public class E_Carta_Palo {

    private final String nombre;
    private final int idnum;

    private E_Carta_Palo(String nombre, int idnum) {
        this.nombre = nombre;
        this.idnum = idnum;
    }

    public int getIdnum() {
        return idnum;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    public static final E_Carta_Palo CLUBS       = new E_Carta_Palo("c", 0);
    public static final E_Carta_Palo HEARTS      = new E_Carta_Palo("h", 1);
    public static final E_Carta_Palo DIAMONDS    = new E_Carta_Palo("d", 2);
    public static final E_Carta_Palo SPADES      = new E_Carta_Palo("s", 3);

}
