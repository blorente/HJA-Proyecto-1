package excepciones;

@SuppressWarnings("serial")
public class ErrorNumApartado extends Exception {
	public ErrorNumApartado() {
		super("Número de apartado incorrecto.");
	}
}