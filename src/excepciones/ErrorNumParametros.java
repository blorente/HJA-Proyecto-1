package excepciones;

@SuppressWarnings("serial")
public class ErrorNumParametros extends Exception {
	public ErrorNumParametros() {
		super("N�mero de par�metros incorrecto.");
	}
}
