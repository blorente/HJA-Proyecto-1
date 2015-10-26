package excepciones;

@SuppressWarnings("serial")
public class ErrorTamLinea extends Exception {
	public ErrorTamLinea() {
		super("Tamaño de línea incorrecto.");
	}
}