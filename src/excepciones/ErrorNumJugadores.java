package excepciones;

@SuppressWarnings("serial")
public class ErrorNumJugadores extends Exception {
	public ErrorNumJugadores() {
		super("N�mero de jugadors incorrecto.");
	}
}