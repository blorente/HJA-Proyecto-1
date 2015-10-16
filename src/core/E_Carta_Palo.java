package core;

public enum E_Carta_Palo {
    HEARTS("h"),
    CLUBS("c"),
    SPADES("s"),
    DIAMONDS("d");

    private final String nombre;

    E_Carta_Palo (String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

}
