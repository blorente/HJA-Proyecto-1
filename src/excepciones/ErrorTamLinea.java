package excepciones;

@SuppressWarnings("serial")
public class ErrorTamLinea extends Exception {
	public ErrorTamLinea() {
		super("Tama�o de l�nea incorrecto.");
	}
}