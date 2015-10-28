package excepciones;

@SuppressWarnings("serial")
public class ErrorNumParametros extends Exception {
	public ErrorNumParametros() {
		super("Número de parámetros incorrecto.");
	}
}
