package excepciones;

@SuppressWarnings("serial")
public class EManoLlena extends RuntimeException {
	public EManoLlena() {
		
	}
	
	public EManoLlena(String msg) {
		super(msg);
	}
}
