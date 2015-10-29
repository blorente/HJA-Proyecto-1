package componentes;

import excepciones.EManoLlena;

import java.util.List;

/**
 * Created by Kerith on 27/10/2015.
 */
public interface I_Jugador {
    void anadirCarta(Carta carta) throws EManoLlena;

    void llenarMano(List<Carta> cartas) throws EManoLlena;

    void anadirJugada(I_Jugada jugada);

    void anadirJugadas(List<I_Jugada> jugada);

    List<Carta> getCartas();

    List<JugadaValor> getJugadas();

    String getId();

    void setId(String id);

    @Override
    String toString();
}
