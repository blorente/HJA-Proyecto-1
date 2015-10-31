/**
 * Enum para representar los valores de las cartas y ayudar con el parseo.
 * Tiene dos campos:
 *  @param valor: Valor numérico que se le asigna a cada carta.
 *  @param nombre: Nombre textual de cada carta, para ayudar en el parseo.
 */

package enumerados;

public class E_Carta_Valor {

    private final int valor;
    private final String nombre;

    E_Carta_Valor(int valor, String nombre) {
        this.valor = valor;
        this.nombre = nombre;
    }

    public int getValor() {
        return this.valor;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    public static final E_Carta_Valor A     = new E_Carta_Valor(14, "A");
    public static final E_Carta_Valor K     = new E_Carta_Valor(13, "K");
    public static final E_Carta_Valor Q     = new E_Carta_Valor(12, "Q");
    public static final E_Carta_Valor J     = new E_Carta_Valor(11, "J");
    public static final E_Carta_Valor T     = new E_Carta_Valor(10, "T");
    public static final E_Carta_Valor NUEVE = new E_Carta_Valor(9, "9");
    public static final E_Carta_Valor OCHO  = new E_Carta_Valor(8, "8");
    public static final E_Carta_Valor SIETE = new E_Carta_Valor(7, "7");
    public static final E_Carta_Valor SEIS  = new E_Carta_Valor(6, "6");
    public static final E_Carta_Valor CINCO = new E_Carta_Valor(5, "5");
    public static final E_Carta_Valor CUATRO= new E_Carta_Valor(4, "4");
    public static final E_Carta_Valor TRES  = new E_Carta_Valor(3, "3");
    public static final E_Carta_Valor DOS   = new E_Carta_Valor(2, "2");

}
