package excepciones;

@SuppressWarnings("serial")
public class ErrorTamLinea extends Exception {
	public ErrorTamLinea() {
		super("Formato de l�nea del archivo de entrada incorrecto para el apartado.");
	}
}