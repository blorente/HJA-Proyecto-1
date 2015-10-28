package entrada_salida_datos;

import componentes.Carta;

import enumerados.E_Carta_Palo;
import enumerados.E_Carta_Valor;
import excepciones.ErrorParseoCarta;

public final class Parser {
	
	//-----------------------
    // CAMPOS Y CONSTRUCTORES
    //-----------------------

    /**
     * Constructor privado que permite que esta sea una clase estática.
     */
    private Parser() {

    }

    //-----------------------
    // MÉTODOS PROPIOS
    //-----------------------
    
    public static Carta parseaCarta(String cadCarta) throws ErrorParseoCarta {
    	E_Carta_Valor valor = parseaValor(cadCarta.charAt(0));
    	E_Carta_Palo palo = parseaPalo(cadCarta.charAt(1));
    	return new Carta(valor, palo);
    }
    
    private static E_Carta_Valor parseaValor(char valor) throws ErrorParseoCarta {
    	switch (valor) {
    	case 'A':
    		return E_Carta_Valor.A;
    	case 'K':
    		return E_Carta_Valor.K;
    	case 'Q':
    		return E_Carta_Valor.Q;
    	case 'J':
    		return E_Carta_Valor.J;
    	case 'T':
    		return E_Carta_Valor.T;
    	case '9':
    		return E_Carta_Valor.NUEVE;
    	case '8':
    		return E_Carta_Valor.OCHO;
    	case '7':
    		return E_Carta_Valor.SIETE;
    	case '6':
    		return E_Carta_Valor.SEIS;
    	case '5':
    		return E_Carta_Valor.CINCO;
    	case '4':
    		return E_Carta_Valor.CUATRO;
    	case '3':
    		return E_Carta_Valor.TRES;
    	case '2':
    		return E_Carta_Valor.DOS;
    	default:
    		throw new ErrorParseoCarta("Valor incorrecto.");
    	}
    }
    
    private static E_Carta_Palo parseaPalo(char palo) throws ErrorParseoCarta {
		switch (palo) {
		case 'c':
			return E_Carta_Palo.CLUBS;
		case 'h':
			return E_Carta_Palo.HEARTS;
		case 'd':
			return E_Carta_Palo.DIAMONDS;
		case 's':
			return E_Carta_Palo.SPADES;
		default:
			throw new ErrorParseoCarta("Palo incorrecto.");
		}
    }
}
