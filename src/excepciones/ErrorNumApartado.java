package excepciones;

@SuppressWarnings("serial")
public class ErrorNumApartado extends Exception {
	public ErrorNumApartado() {
		super("N�mero de apartado incorrecto.");
	}
}