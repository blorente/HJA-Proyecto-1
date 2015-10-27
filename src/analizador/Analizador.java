/**
 * Clase estática (no puede ser instanciada) que contiene métodos
 * para comparar y analizar jugadas, manos, o listas de cartas.
 *
 * Para llamarla, Analizador.analizarMano();
 *
 * Las funciones deberían mantenerse puras (que no cambien el input)
 * por si luuego queremos meter threads.
 */

package analizador;

import java.util.ArrayList;
import java.util.List;

import componentes.Carta;
import componentes.I_Jugada;
import componentes.JugadaValor;
import componentes.JugadorHoldem;
import componentes.Mano;
import enumerados.E_Carta_Palo;
import enumerados.E_Carta_Valor;
import enumerados.E_Jugada_Tipo;

public final class Analizador {

    //-----------------------
    // CAMPOS Y CONSTRUCTORES
    //-----------------------

    /**
     * Constructor privado que permite que esta sea una clase estática.
     */
    private Analizador() {

    }


    //-----------------------
    // MÉTODOS PROPIOS
    //-----------------------
    
    
    

    /**
     * Método que analiza la mano que le pasas, y devuelve una lusta de jugadas.
     * @param m Mano a analizar.
     * @return lista con todas la s jugadas que tiene esa mano (OEASD, Parejas, tríos...)
     *
     * Precondición: m tiene como máximo 5 cartas.
     * Postcondición: La lista de retorno tiene la mejor mano como primer elemento
     */
    public static List<JugadaValor> analizaMano(Mano m) {
    	
    	//añadir boolean para proyectos escalera y color
    	
    	List<JugadaValor> jugadas = new ArrayList<JugadaValor>() ;
    	List<Carta> cartas = m.getCartas();
    	List<Carta> cartaSolucion = new ArrayList<Carta>(); //necesitamos List para el caso de Full y Dobles
    	
    	
    	if (hayEscalera(cartas) != null && hayColor(cartas) != null){
    		cartaSolucion.add(hayEscalera(cartas));
    		jugadas.add(new JugadaValor(E_Jugada_Tipo.STRAIGHT_FLUSH, cartaSolucion, m));
    	}
    	else if (hayPoker(cartas) != null){
    		cartaSolucion.add(hayPoker(cartas));
    		jugadas.add(new JugadaValor(E_Jugada_Tipo.FOUR_OF_A_KIND, cartaSolucion, m));
    	}
    	else if (hayTrio(cartas) != null && hayPareja(cartas) != null){  //hay full
    		cartaSolucion.add(hayTrio(cartas));
    		cartaSolucion.add(hayPareja(cartas));
    		jugadas.add(new JugadaValor(E_Jugada_Tipo.FULL_HOUSE, cartaSolucion, m));
    	}
    	else if (hayColor(cartas) != null){
    		cartaSolucion.add(hayColor(cartas));
    		jugadas.add(new JugadaValor(E_Jugada_Tipo.FLUSH, cartaSolucion, m));
    	}
    	else if (hayEscalera(cartas) != null){
    		cartaSolucion.add(hayEscalera(cartas));
    		jugadas.add(new JugadaValor(E_Jugada_Tipo.STRAIGHT, cartaSolucion, m));
    	}
    	else if (hayTrio(cartas) != null){
    		cartaSolucion.add(hayTrio(cartas));
    		jugadas.add(new JugadaValor(E_Jugada_Tipo.THREE_OF_A_KIND, cartaSolucion, m));
    	}
    	else if (hayDobles(cartas) != null){
    		cartaSolucion = hayDobles(cartas);    		
    		jugadas.add(new JugadaValor(E_Jugada_Tipo.TWO_PAIR, cartaSolucion, m));
    	}
    	else if (hayPareja(cartas) != null){
    		cartaSolucion.add(hayPareja(cartas));
    		jugadas.add(new JugadaValor(E_Jugada_Tipo.PAIR, cartaSolucion, m));
    	}
    	else {
    		if(cartas.get(cartas.size()-1).getValor() == E_Carta_Valor.A){
    			cartaSolucion.add(cartas.get(cartas.size()-1));
    			jugadas.add(new JugadaValor(E_Jugada_Tipo.HIGH_CARD, cartaSolucion, m));
    		}
    		else{
    			cartaSolucion.add(cartas.get(0));
    			jugadas.add(new JugadaValor(E_Jugada_Tipo.HIGH_CARD, cartaSolucion, m));
    		}
    	}
    	 
    	//Comprobamos si hay proyectos y, en caso de haberlos los añadimos a List jugadas..
    	if (hayFlushDraw(cartas))
    		jugadas.add(new JugadaValor(E_Jugada_Tipo.FLUSH_DRAW));
    	if (hayGutShot(cartas))
    		jugadas.add(new JugadaValor(E_Jugada_Tipo.GUTSHOT));
    	if (hayOESD(cartas))
    		jugadas.add(new JugadaValor(E_Jugada_Tipo.OESD));
    	
    	return jugadas;
    }

    /**
     * Método que compara dos manos, y devuelve un float diciendo cuál es la mejor
     * @param m1
     * @param m2
     * @return Por ahora, devuelve [>0 si m1 > m2], [<0 si m1<m2], y [0 si m1=m2]
     */
    public static float comparaManos(Mano m1, Mano m2) {
    	
    	// 1 mirar ranking
        return 0.0f;
    }

    /**
     * Mismo métiodo que el anterior, pero en este caso compara dos listas de cartas.
     *
     * IMPORTANTE: Creo que para esto lo mejor es coger las cartas de 5 en 5,
     *              como dijo en clase, y compararlas usando comparaManos();
     *
     * @param l1
     * @param l2
     * @return el mismo que comparaManos()
     */
    public static float comparaListas (List<Carta> l1, List<Carta> l2) {
        return 0.0f;
    }

    
    
    public static float comparaJugadas (I_Jugada jugada1, I_Jugada jugada2) {
    	
    	// comparar ranking
    	
    	// en caso de ranking igual comparar cartasImportantes
    	
    	// en caso de cartasImportantes iguales MAGIA
    	
        return 0.0f;
    }
    
    /**
     * Método que coge las posibles manos que se pueden formar entre las cartas del jugador
     * y las de la mesa, y devuelve una lista de jugadas que tiene en total el jugador.
     * @param j
     * @param mesa
     * @return
     */
    public static List<I_Jugada> analizaJugador(JugadorHoldem j, List<Carta> mesa) {

    	// combinaciones de las cartas del jugador con las cartas de la mesa
    	List<Mano> combinaciones = null;

        if (mesa.size() > 3) {
            combinaciones.addAll(combinarCartasDeListas(j.getCartas(), 1, mesa, 4));
        }
        combinaciones.addAll(combinarCartasDeListas(j.getCartas(), 2, mesa, 3));

    	// machacar siempre best hand (primero de la lista) e ir añadiendo draws
    	
    	// usando analizaMano (HECHO) y comparaJugadas (POR HACER)
    	
        return null;
    }

    private static List<Mano> combinarCartasDeListas(List<Carta> cartas1, int numCartas1,
                                                     List<Carta> cartas2, int numCartas2) {

        List<Mano> manos = new ArrayList<Mano>();

        int totalCombinations = binomial(cartas1.size(), numCartas1) *
                                binomial(cartas2.size(), numCartas2);

        return manos;
    }

    /**
     * @param n
     * @param k
     * @return (n choose k)
     *
     * @author http://rosettacode.org/wiki/Evaluate_binomial_coefficients#Java
     */
    private static int binomial(int n, int k) {
        if (k>n-k)
            k=n-k;

        int b=1;
        for (int i=1, m=n; i<=k; i++, m--)
            b=b*m/i;
        return b;
    }

    /**
     * Método que coge los jugadores con sus respectivas cartas y las cartas de la mesa, y
     * devuelve una lista de jugadores teniendo en cuenta su jugada, es decir, de mejor a peor.
     * @param list
     * @param mesa
     * @return
     */
    public static List<JugadorHoldem> comparaJugadores(List<JugadorHoldem> list, List<Carta> mesa) {
    	
    	
        return null;
    } 
    
    private static Carta hayEscalera(List<Carta> cartas){
    	int size = cartas.size() , index = 1, valorAnterior = cartas.get(0).getValor().getValor();
    	Carta mejorCarta = cartas.get(0);
    	
    	//posible escalera empezada en As (As no actua como "1" )
    	if(cartas.get(size-1).getValor() == E_Carta_Valor.A &&
    			cartas.get(0).getValor() == E_Carta_Valor.K){     		
    		size--; //contamos con que el As es el primero de la escalera y K el segundo
    		mejorCarta = cartas.get(size);
    	}   	    	
    
		while( index < size ){
			if(cartas.get(index).getValor().getValor() == valorAnterior - 1 )
				valorAnterior--;
			else
				return null;
			index++;
		}       
    	
		return mejorCarta;
    }
    
    private static Carta hayColor(List<Carta> cartas){
    	int index = 1;  //el 0 ya esta evaluado
    	E_Carta_Palo palo = cartas.get(0).getPalo();    	
    	
    	while(index < cartas.size())
    		if (cartas.get(index).getPalo() != palo)
    			return null;
    		else
    			index++;
    	
    	//Si hay color, devolvemos la mejor carta
    	if (cartas.get(4).getValor() == E_Carta_Valor.A)
    		return cartas.get(4);
    	
    	return cartas.get(0);
    }
    
    private static Carta hayTrio(List<Carta> cartas){
    	Carta a = null, b = null, c = null;
    	int index = 0, iguales[] = {0, 0, 0};
    	
    	for (;index < cartas.size(); index++){
    		if (a != null && cartas.get(index).getValor() == a.getValor()){
    			iguales[0]++;
    		}
    		else if (b != null && cartas.get(index).getValor() == b.getValor()){
    			iguales[1]++;
    		}
    		else if (c != null && cartas.get(index).getValor() == c.getValor()){
    			iguales[2]++;
    		}
    		else{
    			if (iguales[0] == 0){
    				a = cartas.get(index);
    				iguales[0]++;
    			}
    			else if (iguales[1] == 0){
    				b = cartas.get(index);
    				iguales[1]++;
    			}
    			else if (iguales[2] == 0){
    				c = cartas.get(index);
    				iguales[2]++;
    			}
    			else
    				return null;
    		}
    	}
    	
    	if ( iguales[0] == 3 )
    		return a;
    	else if ( iguales[1] == 3 )
    		return b;
    	else if ( iguales[2] == 3 )
    		return c;
    	
    	return null;
    }
    
    private static Carta hayPareja(List<Carta> cartas){
    	Carta a = null, b = null, c = null, d = null;
    	int index = 0, iguales[] = {0, 0, 0, 0};
    	
    	for (;index < cartas.size(); index++){
    		if (a != null && cartas.get(index).getValor() == a.getValor()){
    			iguales[0]++;
    		}
    		else if (b != null && cartas.get(index).getValor() == b.getValor()){
    			iguales[1]++;
    		}
    		else if (c != null && cartas.get(index).getValor() == c.getValor()){
    			iguales[2]++;
    		}
    		else if (d != null && cartas.get(index).getValor() == d.getValor()){
    			iguales[3]++;
    		}
    		else{
    			if (iguales[0] == 0){
    				a = cartas.get(index);
    				iguales[0]++;
    			}
    			else if (iguales[1] == 0){
    				b = cartas.get(index);
    				iguales[1]++;
    			}
    			else if (iguales[2] == 0){
    				c = cartas.get(index);
    				iguales[2]++;
    			}
    			else if (iguales[3] == 0){
    				d = cartas.get(index);
    				iguales[3]++;
    			}
    			else
    				return null;
    		}
    	}
    	
    	if ( iguales[0] == 2 )
    		return a;
    	else if ( iguales[1] == 2 )
    		return b;
    	else if ( iguales[2] == 2 )
    		return c;
    	else if ( iguales[3] == 2 )
    		return d;
    	
    	return null;
    }
    
    private static List<Carta> hayDobles(List<Carta> cartas){
    	Carta a = null, b = null, c = null;
    	int index = 0, iguales[] = {0, 0, 0};
    	List<Carta> solucion = new ArrayList<Carta>();
    	
    	for (;index < cartas.size(); index++){
    		if (a != null && cartas.get(index).getValor() == a.getValor()){
    			iguales[0]++;
    		}
    		else if (b != null && cartas.get(index).getValor() == b.getValor()){
    			iguales[1]++;
    		}
    		else if (c != null && cartas.get(index).getValor() == c.getValor()){
    			iguales[2]++;
    		}
    		else{
    			if (iguales[0] == 0){
    				a = cartas.get(index);
    				iguales[0]++;
    			}
    			else if (iguales[1] == 0){
    				b = cartas.get(index);
    				iguales[1]++;
    			}
    			else if (iguales[2] == 0){
    				c = cartas.get(index);
    				iguales[2]++;
    			}
    			else
    				return null;
    		}
    	}
    	
    	if ( iguales[0] == 2 )
    		solucion.add(a);
    	if ( iguales[1] == 2 )
    		solucion.add(b);
    	if ( iguales[2] == 2 )
    		solucion.add(c);
    	
    	return solucion;
    }
    
    private static Carta hayPoker(List<Carta> cartas){
    	Carta a = null, b = null;
    	int index = 0, iguales[] = {0, 0};
    	
    	for (;index < cartas.size(); index++){
    		if (a != null && cartas.get(index).getValor() == a.getValor()){
    			iguales[0]++;
    		}
    		else if (b != null && cartas.get(index).getValor() == b.getValor()){
    			iguales[1]++;
    		}
    		else{
    			if (iguales[0] == 0){
    				a = cartas.get(index);
    				iguales[0]++;
    			}
    			else if (iguales[1] == 0){
    				b = cartas.get(index);
    				iguales[1]++;
    			}
    			else
    				return null;
    		}
    	}
    	
    	//solamente devolvera una carta en caso de haber trio
    	if ( iguales[0] == 4 )
    		return a;
    	else if ( iguales[1] == 4 )
    		return b;
    	
    	return null;
    }
    
    //PROYECTOS
    
    private static boolean hayFlushDraw(List<Carta> cartas){
    	E_Carta_Palo color1, color2;	
    	int[] colores = {0, 0};
    	//Hay 2 colores distintos (por defecto ponemos el color de la primera carta
    	
    	color1 = cartas.get(0).getPalo();
    	color2 = color1;
    	
    	for (int i = 0; i < cartas.size(); i++)
    	{
    		if(cartas.get(i).getPalo() == color1)
    			colores[0]++;
    		else if(cartas.get(i).getPalo() == color2)
    			colores[1]++;
    		else{
    			if(colores[1] > 0)	//ya se ha insertado algun color
    				return false;
    			else{
    				color2 = cartas.get(i).getPalo();
    				colores[1]++;
    			}
    		}
    	}
    	
    	if (colores[0] == 4 || colores[1] == 4)
    		return true;
    	else
    		return false;    	
    }
    
    private static boolean hayGutShot(List<Carta> cartas){
    	int size = cartas.size() , index = 1, valorAnterior = cartas.get(0).getValor().getValor();
    	boolean casoEspecialAK = false;
    	int gutshot = 1, cartaSobrante = 1;	//numero de cartas para completar el proyecto
    	
    	//posible escalera empezada en As (As no actua como "1" )
    	if(cartas.get(size-1).getValor() == E_Carta_Valor.A &&
    			cartas.get(0).getValor() == E_Carta_Valor.K){     		
    		size--; //contamos con que el As es el primero de la escalera y K el seundo  
    		casoEspecialAK = true;
    	}   
    	
    	//// Caso del gutshot = K
    	if(cartas.get(size-1).getValor() == E_Carta_Valor.A &&
    			cartas.get(0).getValor() == E_Carta_Valor.Q){
    		gutshot--;
    		cartaSobrante--;
    		size-=2;
    	}
    	
    	
		while( index < size ){
			if(cartas.get(index).getValor().getValor() == valorAnterior - 1 )
				valorAnterior--;
			else if(index == 1 && cartas.get(index).getValor().getValor() == valorAnterior - 2){ // Si el gutshot esta entre la pos 0 y la 1
				gutshot--;
				valorAnterior = cartas.get(index).getValor().getValor();
			}
			else if (((index == 1 || index == 4) && cartaSobrante > 0 && !casoEspecialAK) || 
					((index == 1 || index == 3) && cartaSobrante > 0 && casoEspecialAK)){
				if (casoEspecialAK && index == 1 && cartas.get(index ).getValor().getValor() == valorAnterior - 2){
					gutshot--;
					valorAnterior -= 2;
				}
				else if(casoEspecialAK && index != 3)					
					return false;
				else{
					valorAnterior = cartas.get(index).getValor().getValor();
					cartaSobrante--;
				}				
			}
			else if (gutshot > 0 && index < size && cartas.get(index).getValor().getValor() == valorAnterior - 2){
				valorAnterior -= 2;
				gutshot--;
				
			}
			else
				return false;
			index++;
		}       
		
		if(cartaSobrante == 0 && gutshot == 0)
			return true;
		
		return false;
    }
    
    private static boolean hayOESD(List<Carta> cartas){
    	int size = cartas.size() , index = 1, valorAnterior = cartas.get(0).getValor().getValor();
    	 
    	//quitar casos de escalera esquina y añadir nuevo metodo
    	int nFallos = 1;	//numero de cartas para completar el proyecto
    	while( index < size ){
			if(cartas.get(index).getValor().getValor() == valorAnterior - 1 )
				valorAnterior--;
			else if ( cartas.get(index).getValor() == E_Carta_Valor.K && cartas.get(size-1).getValor() == E_Carta_Valor.A)
				valorAnterior--;
			else if ((index == 1 || index == 4) && nFallos > 0){
				valorAnterior = cartas.get(index).getValor().getValor();
				nFallos--;
			}
			else
				return false;
			//if nFallos es 0 true, sino false
			index++;
		}     
    	return true;
    }
    private static boolean hayDrawStraight(List<Carta> cartas){
    	return false;
    }

}
