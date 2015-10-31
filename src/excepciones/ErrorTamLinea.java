package excepciones;

@SuppressWarnings("serial")
public class ErrorTamLinea extends Exception {
	public ErrorTamLinea() {
		super("Formato de línea del archivo de entrada incorrecto para el apartado.");
	}
}