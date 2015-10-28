package componentes;

import java.util.List;

import enumerados.E_Jugada_Tipo;

public class JugadaValor implements I_Jugada {

	private E_Jugada_Tipo tipo;
	private List<Carta> cartasImportantes = null;
	private Mano mano = null;
	
	public JugadaValor(E_Jugada_Tipo tipo, List<Carta> cartasImportantes, Mano mano) {
		this.tipo = tipo;
		this.cartasImportantes = cartasImportantes;
		this.mano = mano;
	}

	public JugadaValor(E_Jugada_Tipo tipo) {
		this.tipo = tipo;
	}

	public String toString() {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(tipo.toString());
		
		if (cartasImportantes != null) {
			strBuild.append(" (");
			for (int i = 0; i < cartasImportantes.size(); i++) {
				strBuild.append(cartasImportantes.get(i).getValor().toString());				
				
			}
			strBuild.append(")");			
		}
		if (mano != null) {
			strBuild.append("with ");
			strBuild.append(mano.toString());
		}
		return strBuild.toString();
	}
	   
}
