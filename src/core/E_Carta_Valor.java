package core;

public enum E_Carta_Valor {
    A(1, "A"),
    K(13, "K"),
    Q(12, "Q"),
    J(11, "J"),
    T(10, "T"),
    NUEVE(9, "9"),
    OCHO(8, "8"),
    SIETE(7, "7"),
    SEIS(6, "6"),
    CINCO(5, "5"),
    CUATRO(4, "4"),
    TRES(3, "3"),
    DOS(2, "2");

    private final int valor;
    private final String nombre;

    E_Carta_Valor(int valor, String nombre) {
        this.valor = valor;
        this.nombre = nombre;
    }

    public int getValor() {
        return this.valor;
    }

    public String getNombre() {
        return this.nombre;
    }

}
