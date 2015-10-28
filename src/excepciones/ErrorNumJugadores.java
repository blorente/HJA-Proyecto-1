package excepciones;

@SuppressWarnings("serial")
public class ErrorNumJugadores extends Exception {
	public ErrorNumJugadores() {
		super("Número de jugadors incorrecto.");
	}
}