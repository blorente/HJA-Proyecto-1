package core;

public class JugadaSimple implements I_Jugada {

    private E_Jugada_Tipo tipo;

    public JugadaSimple(E_Jugada_Tipo tipo) {
        this.tipo = tipo;
    }

    public E_Jugada_Tipo getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return tipo.toString();
    }
}
