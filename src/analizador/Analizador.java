/**
 * Clase est�tica (no puede ser instanciada) que contiene m�todos
 * para comparar y analizar jugadas, manos, o listas de cartas.
 *
 * Para llamarla, Analizador.analizarMano();
 *
 * Las funciones deber�an mantenerse puras (que no cambien el input)
 * por si luuego queremos meter threads.
 */

package analizador;

import java.util.*;

import componentes.Carta;
import componentes.JugadaValor;
import componentes.JugadorHoldem;
import componentes.JugadorOmaha;
import componentes.Mano;
import enumerados.E_Carta_Palo;
import enumerados.E_Carta_Valor;
import enumerados.E_Jugada_Tipo;

public final class Analizador {

    //-----------------------
    // CAMPOS Y CONSTRUCTORES
    //-----------------------

    /**
     * Constructor privado que permite que esta sea una clase est�tica.
     */
    private Analizador() {

    }

    //-----------------------
    // M�TODOS PROPIOS
    //-----------------------

	/**
	 * M�todo que analiza la mano que le pasas, y devuelve una lusta de jugadas.
	 * @param m Mano a analizar.
	 * @return lista con todas la s jugadas que tiene esa mano (OEASD, Parejas, tr�os...)
	 *
	 * Precondici�n: m tiene como m�ximo 5 cartas.
	 * Postcondici�n: La lista de retorno tiene la mejor mano como primer elemento
	 */
	public static JugadaValor analizaMano(Mano m) {

        List<Carta> cartas = m.getCartas();

        // Ordenar las cartas de mayor a menor por su valor
        class ComparadorCarta implements Comparator<Carta> {
            public int compare(Carta c1, Carta c2) {
                if (c1.getValor().getValor() > c2.getValor().getValor())
                    return -1;
                else if (c1.getValor().getValor() < c2.getValor().getValor())
                    return 1;
                else
                    return 0;
            }
        }
        cartas.sort(new ComparadorCarta());

        //Paso 1: Analizar mano
		int[] cartasDePalo = {0, 0, 0, 0}; // = {#clubs, #hearts, #diamonds, #spades}

        Carta anterior = cartas.get(0);

        //Analiza la primera carta de la mano
        cartasDePalo[anterior.getPalo().getIdnum()]++;

        //Variables para analizar jugadas con parejas (Pair+)
        E_Jugada_Tipo jugadaParejas = E_Jugada_Tipo.HIGH_CARD;
        E_Carta_Valor[] valoresDeJugadasParejas = new E_Carta_Valor[2];
        valoresDeJugadasParejas[0] = anterior.getValor();
        int cartasIgualesSeguidas = 1;
        int indexDeParejas = 0; //Index para saber cuantas parejas llevamos

        //Variables para analizar jugadas con straight
        boolean puedeHaberProyectoEscalera = true;
        boolean hayUnHueco = false;
        int cartasEnElProyecto = 1;
        boolean puedeHaberRueda = false;

        for (int i = 1; i < cartas.size(); i++) {
            //Cogemos la siguiente carta a analizar
            Carta current = cartas.get(i);

            //Gran bloque if para analizar manos con parejas (Pair+)
            if (current.getValor() == anterior.getValor()) {

                if (jugadaParejas == E_Jugada_Tipo.HIGH_CARD) {
                    jugadaParejas = E_Jugada_Tipo.PAIR;
                    valoresDeJugadasParejas[indexDeParejas] = current.getValor();
                } else if (jugadaParejas == E_Jugada_Tipo.PAIR) {
                    if (indexDeParejas > 0) { //Ya teníamos una pareja de antes, distinta a esta

                        jugadaParejas = E_Jugada_Tipo.TWO_PAIR;
                        valoresDeJugadasParejas[indexDeParejas] = current.getValor();

                    } else { //Tenemos un trío

                        jugadaParejas = E_Jugada_Tipo.THREE_OF_A_KIND;

                    }
                } else if (jugadaParejas == E_Jugada_Tipo.THREE_OF_A_KIND) {

                    if (indexDeParejas > 0) { //Teníamos un trío de antes, difernte a este. Ahora tenemos un FH
                        jugadaParejas = E_Jugada_Tipo.FULL_HOUSE;
                        valoresDeJugadasParejas[indexDeParejas] = current.getValor();
                    } else { //El trío es del mismo valor que current -> Tenemos un POKER
                        jugadaParejas = E_Jugada_Tipo.FOUR_OF_A_KIND;
                    }
                } else if (jugadaParejas == E_Jugada_Tipo.TWO_PAIR) {

                    jugadaParejas = E_Jugada_Tipo.FULL_HOUSE;

                }
                cartasIgualesSeguidas++;
            } else {

                if (cartasIgualesSeguidas >= 2) {
                    indexDeParejas++;
                }

                cartasIgualesSeguidas = 0;
            }

            //Gran bloque if para comprobar escaleras y proyectos de escalera

            int distanciaEntreValores = anterior.getValor().getValor() - current.getValor().getValor(); //Siempre >= 1

            if (cartas.get(0).getValor() == E_Carta_Valor.A && !puedeHaberRueda) { //Para permitir wheel draws
                if (current.getValor() == E_Carta_Valor.CINCO) {
                    distanciaEntreValores = 1;
                    puedeHaberRueda = true;
                } else if (current.getValor() == E_Carta_Valor.CUATRO) {
                    distanciaEntreValores = 2;
                    puedeHaberRueda = true;
                }

            }


            if (distanciaEntreValores > 2 && cartasEnElProyecto > 1 && cartasEnElProyecto < 4) { //Ya no puede haber proyectos de escalera
                puedeHaberProyectoEscalera = false;
            } else if (puedeHaberProyectoEscalera) {

                if (distanciaEntreValores == 1) { //Son cartas contiguas
                    cartasEnElProyecto++;
                } else if (distanciaEntreValores == 2) {
                    if (hayUnHueco) { //Si ya habia un hueco antes, no se puede hacer proyecto de escalera
                        if (cartasEnElProyecto < 4) {
                            puedeHaberProyectoEscalera = false;
                        }
                    } else {
                        hayUnHueco = true;
                        cartasEnElProyecto++;
                    }
                } //Si la distancia es 0, se ignora

            }

            //Aumentamos la cuenta de color
            cartasDePalo[current.getPalo().getIdnum()]++;

            //Ponemos la carta que acabamos de analizar como "anterior"
            anterior = current;
		}

        //Paso 2: Parsear resultados y rellenar jugada
        JugadaValor jugada = new JugadaValor(jugadaParejas);

        //Marcar escaleras
        if (puedeHaberProyectoEscalera) {
            if (cartasEnElProyecto == 5) {
                jugada = new JugadaValor(E_Jugada_Tipo.STRAIGHT, cartas, m);
            } else if (cartasEnElProyecto == 4) {
                //Casos especiales: AKQJX y AX432
                if (puedeHaberRueda ||
                        (cartas.get(0).getValor() == E_Carta_Valor.A && cartas.get(3).getValor() == E_Carta_Valor.J)) {
                    jugada.setStraightDraw(true);
                } else if (hayUnHueco) { //Resto de casos
                    jugada.setGutShot(true);
                } else {
                    jugada.setOESD(true);
                }
            }
        }

        //Marcar color
        for (int i = 0; i < cartasDePalo.length; i++) {
            if (cartasDePalo[i] == 4) {
                jugada.setFlushDraw(true);
            } else if (cartasDePalo[i] == 5) {
                if (jugada.getTipo() == E_Jugada_Tipo.STRAIGHT) {
                    jugada = new JugadaValor(E_Jugada_Tipo.STRAIGHT_FLUSH, cartas, m);
                } else {
                    jugada = new JugadaValor(E_Jugada_Tipo.FLUSH, cartas, m);
                }
            }
        }

		return jugada;
	}


   /* *//**
     * Antigua implementacion de analizaMano
     *//*
    public static JugadaValor analizaMano(Mano m) {
    	    	
    	JugadaValor jugada;
    	List<Carta> cartaSolucion = new ArrayList<Carta>(); //necesitamos List para el caso de Full y Dobles
    	List<Carta> cartas = m.getCartas();

    	// Ordenar las cartas de mayor a menor por su valor
    	class ComparadorCarta implements Comparator<Carta> {
			public int compare(Carta c1, Carta c2) {
				if (c1.getValor().getValor() > c2.getValor().getValor())
					return -1;
				else if (c1.getValor().getValor() < c2.getValor().getValor())
					return 1;
				else
					return 0;
			}
		}
    	cartas.sort(new ComparadorCarta());
    	
    	if (hayEscalera(cartas) != null && hayColor(cartas) != null){
    		cartaSolucion.add(hayEscalera(cartas));
    		jugada = new JugadaValor(E_Jugada_Tipo.STRAIGHT_FLUSH, cartaSolucion, m);
    	}
    	else if (hayPoker(cartas) != null){
    		cartaSolucion.add(hayPoker(cartas));
    		jugada = new JugadaValor(E_Jugada_Tipo.FOUR_OF_A_KIND, cartaSolucion, m);
    	}
    	else if (hayTrio(cartas) != null && hayPareja(cartas) != null){  //hay full
    		cartaSolucion.add(hayTrio(cartas));
    		cartaSolucion.add(hayPareja(cartas));
    		jugada = new JugadaValor(E_Jugada_Tipo.FULL_HOUSE, cartaSolucion, m);
    	}
    	else if (hayColor(cartas) != null){
    		cartaSolucion.add(hayColor(cartas));
    		jugada = new JugadaValor(E_Jugada_Tipo.FLUSH, cartaSolucion, m);
    	}
    	else if (hayEscalera(cartas) != null){
    		cartaSolucion.add(hayEscalera(cartas));
    		jugada = new JugadaValor(E_Jugada_Tipo.STRAIGHT, cartaSolucion, m);
    	}
    	else if (hayTrio(cartas) != null){
    		cartaSolucion.add(hayTrio(cartas));
    		jugada = new JugadaValor(E_Jugada_Tipo.THREE_OF_A_KIND, cartaSolucion, m);
    	}
    	else if (hayDobles(cartas) != null){
    		cartaSolucion = hayDobles(cartas);    		
    		jugada = new JugadaValor(E_Jugada_Tipo.TWO_PAIR, cartaSolucion, m);
    	}
    	else if (hayPareja(cartas) != null){
    		cartaSolucion.add(hayPareja(cartas));
    		jugada = new JugadaValor(E_Jugada_Tipo.PAIR, cartaSolucion, m);
    	}
    	else {
    		if(cartas.get(cartas.size()-1).getValor() == E_Carta_Valor.A){
    			cartaSolucion.add(cartas.get(cartas.size()-1));
    			jugada = new JugadaValor(E_Jugada_Tipo.HIGH_CARD, cartaSolucion, m);
    		}
    		else{
    			cartaSolucion.add(cartas.get(0));
    			jugada = new JugadaValor(E_Jugada_Tipo.HIGH_CARD, cartaSolucion, m);
    		}
    	}
    	 
    	//Comprobamos si hay proyectos y, en caso de haberlos los marcamos en la jugada..
    	jugada.setFlushDraw(hayFlushDraw(cartas));
    	jugada.setGutShot(hayGutShot(cartas));
    	jugada.setOESD(hayOESD(cartas));
    	jugada.setStraightDraw(hayStraightDraw(cartas));
    	
    	return jugada;
    }*/

    /**
     * M�todo que compara dos manos, y devuelve un float diciendo cu�l es la mejor
     * @param m1
     * @param m2
     * @return Por ahora, devuelve [>0 si m1 > m2], [<0 si m1<m2], y [0 si m1=m2]
     */
    public static float comparaManos(Mano m1, Mano m2) {
    	
        return 0.0f;
    }

    /**
     * Mismo m�tiodo que el anterior, pero en este caso compara dos listas de cartas.
     *
     * IMPORTANTE: Creo que para esto lo mejor es coger las cartas de 5 en 5,
     *              como dijo en clase, y compararlas usando comparaManos();
     *
     * @param l1
     * @param l2
     * @return el mismo que comparaManos()
     */
    public static float comparaListas(List<Carta> l1, List<Carta> l2) {
        return 0.0f;
    }
    
    /**
     * 
     * @param jugada1
     * @param jugada2
     * @return devuelve [1 si jugada1 > jugada2], [-1 si jugada1 < jugada2], y [0 son jugada1 = jugada2]
     */
    private static int comparaJugadas(JugadaValor jugada1, JugadaValor jugada2) {
    	//System.out.println(jugada1.toString());
    	int rankJug1 = jugada1.getTipo().getRanking();
    	int rankJug2 = jugada2.getTipo().getRanking();
    	if (rankJug1 > rankJug2)
    		return 1;
    	
    	else if (rankJug1 < rankJug2)
    		return -1;
    	
    	else { // mismo tipo de jugada
    		List<Carta> cartasImp1 = jugada1.getCartasImportantes();
    		List<Carta> cartasImp2 = jugada2.getCartasImportantes();
    		for (int i = 0; i < cartasImp1.size(); i++) {
    			if (cartasImp1.get(i).getValor().getValor() > cartasImp2.get(i).getValor().getValor())
    				return 1;
    			if (cartasImp1.get(i).getValor().getValor() < cartasImp2.get(i).getValor().getValor())
    				return -1;
    		}
    		
    		// llegados a este punto tenemos que mirar los kickers
    		List<Carta> cartasKicker1 = jugada1.getMano().getCartas();
    		List<Carta> cartasKicker2 = jugada2.getMano().getCartas();
    		for (int i = 0; i < cartasKicker1.size(); i++) {
    			if (cartasKicker1.get(i).getValor().getValor() > cartasKicker2.get(i).getValor().getValor())
    				return 1;
    			if (cartasKicker1.get(i).getValor().getValor() < cartasKicker2.get(i).getValor().getValor())
    				return -1;
    		}
    		
    		// las jugadas son igual de buenas
    		return 0;
    	}
    }
    
    /**
     * M�todo que coge las posibles manos que se pueden formar entre las cartas del jugador
     * y las de la mesa, y devuelve una lista de jugadas que tiene en total el jugador.
     * @param j
     * @param mesa
     * @return
     */
    public static JugadaValor analizaJugadorOmaha(JugadorOmaha j, List<Carta> mesa) {
    	
    	List<Mano> combinaciones = new ArrayList<Mano>();

        combinaciones.addAll(combinarCartasDeListas(j.getCartas(), 2, mesa, 3));

    	JugadaValor jugada, mejorJugada = new JugadaValor(E_Jugada_Tipo.NADA, null, null), jugadaAux;

        for (Mano mano : combinaciones) {
            jugada = analizaMano(mano);
            if (comparaJugadas(jugada, mejorJugada) == 1) {
            	jugadaAux = mejorJugada;
            	mejorJugada = jugada;
            	if (!mejorJugada.getFlushDraw())
            		mejorJugada.setFlushDraw(jugadaAux.getFlushDraw());
            	if (!mejorJugada.getGutShot())
            		mejorJugada.setGutShot(jugadaAux.getGutShot());
            	if (!jugada.getOESD())
            		mejorJugada.setOESD(jugadaAux.getOESD());
            }
        }
        j.setJugada(mejorJugada);
        return mejorJugada;
    }
    
    /**
     * M�todo que coge las posibles manos que se pueden formar entre las cartas del jugador
     * y las de la mesa, y devuelve una lista de jugadas que tiene en total el jugador.
     * @param j
     * @param mesa
     * @return
     */
    public static JugadaValor analizaJugador(JugadorHoldem j, List<Carta> mesa) {
    	
    	List<Mano> combinaciones = new ArrayList<Mano>();

		if (mesa.size() >= 5) {
			combinaciones.addAll(combinarCartasDeListas(j.getCartas(), 0, mesa, 5));
		}
		if (mesa.size() >= 4) {
            combinaciones.addAll(combinarCartasDeListas(j.getCartas(), 1, mesa, 4));
        }
        combinaciones.addAll(combinarCartasDeListas(j.getCartas(), 2, mesa, 3));

    	JugadaValor jugada, mejorJugada = new JugadaValor(E_Jugada_Tipo.NADA, null, null), jugadaAux;

        for (Mano mano : combinaciones) {
            jugada = analizaMano(mano);
            if (comparaJugadas(jugada, mejorJugada) == 1) {
            	jugadaAux = mejorJugada;
            	mejorJugada = jugada;
            	if (!mejorJugada.getFlushDraw())
            		mejorJugada.setFlushDraw(jugadaAux.getFlushDraw());
            	if (!mejorJugada.getGutShot())
            		mejorJugada.setGutShot(jugadaAux.getGutShot());
            	if (!jugada.getOESD())
            		mejorJugada.setOESD(jugadaAux.getOESD());
            }
        }
        j.setJugada(mejorJugada);
        return mejorJugada;
    }

    private static List<Mano> combinarCartasDeListas(List<Carta> cartas1, int numCartas1,
                                                     List<Carta> cartas2, int numCartas2) {
    	
        List<Mano> manos = new ArrayList<Mano>();

		List<List<Carta>> combCartas1 = n_escoge_k(cartas1, numCartas1);
		List<List<Carta>> combCartas2 = n_escoge_k(cartas2, numCartas2);

        List<List<Carta>> todasLasCombinaciones = productoCartesiano(combCartas1, combCartas2);

        for (List<Carta> lista: todasLasCombinaciones) {
            Mano mano = new Mano();
            mano.llenarMano(lista);
            manos.add(mano);
        }

        return manos;
    }

    private static List<List<Carta>> productoCartesiano(List<List<Carta>> combCartas1, List<List<Carta>> combCartas2) {

        List<List<Carta>> prod = new ArrayList<List<Carta>>();

        for (int i = 0; i < combCartas1.size(); i++) {
            for (int j = 0; j < combCartas2.size(); j++) {
                ArrayList<Carta> lista = new ArrayList<Carta>(combCartas1.get(i));
                lista.addAll(new ArrayList<Carta>(combCartas2.get(j)));
                prod.add(lista);
            }
        }

        return prod;
    }

    /**
     * Función para sacar todas las combinaciones posibles de una lista, sin repetición.
     * @param cartas Lista de cartas.
     * @param k Longitud de las selecciones.
     * @return
     */
	private static List<List<Carta>> n_escoge_k(List<Carta> cartas, int k) {
		List<List<Carta>> devolver = new ArrayList<List<Carta>>();

		List<Carta> mano = new ArrayList<Carta>();

		n_escoge_k(cartas, mano, k, 0, 0, devolver); //Llamada a la versión recursiva de esta función

		return devolver;
    }

    /**
     * Función recursiva auxiliar, que se llama automáticamente desde n_escoge_k.
     *
     * NO TOCAR
     *
     */

	private static void n_escoge_k(List<Carta> cartas, List<Carta> mano, int k, int iteration, int curIndex, List<List<Carta>> devolver) {

		if (curIndex > cartas.size()) {
			return;
		}

		if (iteration == k) {
			devolver.add(new ArrayList<Carta>(mano));
			return;
		}

		for (int i = curIndex; i < cartas.size(); i++) {
            if (mano.size() <= iteration) {
                mano.add(cartas.get(i));
            } else {
                mano.set(iteration, cartas.get(i));
            }
			n_escoge_k(cartas, mano, k, iteration + 1, i + 1, devolver);
		}

	}

	/**
     * @param n
     * @param k
     * @return (n choose k)
     *
     * @author http://rosettacode.org/wiki/Evaluate_binomial_coefficients#Java
     */
    @SuppressWarnings("unused")
	private static int binomial(int n, int k) {
        if (k>n-k)
            k=n-k;

        int b=1;
        for (int i=1, m=n; i<=k; i++, m--)
            b=b*m/i;
        return b;
    }

    /**
     * M�todo que coge los jugadores con sus respectivas cartas y las cartas de la mesa, y
     * devuelve una lista de jugadores teniendo en cuenta su jugada, es decir, de mejor a peor.
     * @param list
     * @param mesa
     * @return
     */
    public static List<JugadorHoldem> comparaJugadores(List<JugadorHoldem> list, List<Carta> mesa) {
    	for (JugadorHoldem jug : list)
    		Analizador.analizaJugador(jug, mesa);
    	
    	class ComparadorJugador implements Comparator<JugadorHoldem> {
			public int compare(JugadorHoldem j1, JugadorHoldem j2) {
				return (-1) * comparaJugadas(j1.getJugada(), j2.getJugada());
			}
		}
    	list.sort(new ComparadorJugador());
        return list;
    } 
    
  /*//Se encarga de comprobar si hay escalera en la lista de cartas y, en caso de haberla, devuelve
    //la carta con m�s valor de la escalera.
    private static Carta hayEscalera(List<Carta> cartas){
    	int size = cartas.size() , index = 1, valorAnterior = cartas.get(0).getValor().getValor();
    	
    	//posible escalera empezada en As (As actua como "1" )
    	if(cartas.get(0).getValor() == E_Carta_Valor.A &&
    			cartas.get(size - 1).getValor() == E_Carta_Valor.DOS)  { 
    		index++;
    		valorAnterior = cartas.get(1).getValor().getValor();
    	}
  	    	
    	//vamos comprobando que el valor actual sea igual que el valor anterior - 1
		while( index < size ){
			if(cartas.get(index).getValor().getValor() == valorAnterior - 1 )
				valorAnterior--;
			else
				return null;
			index++;
		}       
    	
		if(cartas.get(1).getValor() == E_Carta_Valor.CINCO)
			return cartas.get(1);
		
		return cartas.get(0);
    }
    
    //Se encarga de comprobar si hay color en la lista de cartas y, en caso de haberlo, devuelve
    //la carta con m�s valor de la escalera.
    private static Carta hayColor(List<Carta> cartas){
    	int index = 1;  //el 0 ya esta evaluado
    	E_Carta_Palo palo = cartas.get(0).getPalo();    	
    	
    	while(index < cartas.size())
    		if (cartas.get(index).getPalo() != palo)
    			return null;
    		else
    			index++;
    	    	
    	return cartas.get(0);
    }
    
    //Se encarga de comprobar si hay trio en la lista de cartas y, en caso de haberlo, devuelve
    //una carta perteneciente al trio.
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
    
    //Se encarga de comprobar si hay pareja en la lista de cartas y, en caso de haberlo, devuelve
    //una carta perteneciente a la pareja.
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
    
    //Se encarga de comprobar si hay doble pareja en la lista de cartas y, en caso de haberlo, 
    //devuelve una carta perteneciente a cada pareja (de mayor a menor).
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
    
    //Se encarga de comprobar si hay poker en la lista de cartas y, en caso de haberlo, devuelve
    //una carta perteneciente al poker.
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
    
    //Se encarga de comprobar si hay proyecto de color en la lista de cartas y, en caso de haberlo,
    //devuelve true
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
    
    //Se encarga de comprobar si hay gutshot en la lista de cartas y, en caso de haberlo, 
    //devuelve true
    private static boolean hayGutShot(List<Carta> cartas){
    	int size = cartas.size() , index = 1, valorAnterior = cartas.get(0).getValor().getValor();
    	boolean casoEspecialA2 = false;
    	int gutshot = 1, cartaSobrante = 1;	//numero de cartas para completar el proyecto
    	
    	
    	//posible escalera empezada en As (As actua como "1" )
    	if(cartas.get(0).getValor() == E_Carta_Valor.A &&
    			(cartas.get(1).getValor() == E_Carta_Valor.CINCO || 
    			cartas.get(2).getValor() == E_Carta_Valor.CINCO)){
    		valorAnterior = 5;
    		casoEspecialA2 = true;
    		
    		if(cartas.get(1).getValor() == E_Carta_Valor.CINCO){ //cartaSobrante al final
    			index++;
    		}
    		else{ 	//cartaSobrante al principio
    			cartaSobrante--;
    			index+=2;
    		}
    	}   
    	
    	// Caso del gutshot entre el A y el 3
    	if(cartas.get(0).getValor() == E_Carta_Valor.A && casoEspecialA2 &&
    			cartas.get(size-1).getValor() == E_Carta_Valor.TRES){
    		gutshot--;
    		size--;
    	}
    	
    	
		for(; index < size; index++ ){
			if(cartas.get(index).getValor().getValor() == valorAnterior - 1 )
				valorAnterior--;
			else if(index == 1 && cartas.get(index).getValor().getValor() == valorAnterior - 2){ // Si el gutshot esta entre la pos 0 y la 1
				gutshot--;
				valorAnterior = cartas.get(index).getValor().getValor();
			}
			else if ((index == 1 || index == 4) && cartaSobrante > 0 && !casoEspecialA2){				
					valorAnterior = cartas.get(index).getValor().getValor();
					cartaSobrante--;								
			}
			else if (gutshot > 0 && 
					cartas.get(index).getValor().getValor() == valorAnterior - 2){
				valorAnterior -= 2;
				gutshot--;				
			}
			else
				return false;
		}       
		
		if(cartaSobrante == 0 && gutshot == 0)
			return true;
		
		return false;
    }
    
    //Se encarga de comprobar si hay OESD en la lista de cartas y, en caso de haberlo, 
    //devuelve true
    private static boolean hayOESD(List<Carta> cartas){
    	int size = cartas.size() , index = 1, valorAnterior = cartas.get(0).getValor().getValor();
    	 
    	//quitar casos de escalera esquina
    	if(cartas.get(0).getValor() == E_Carta_Valor.A && 
    			(cartas.get(size-1).getValor() == E_Carta_Valor.DOS || 
    			cartas.get(1).getValor() == E_Carta_Valor.K))
    		return false;
    	   	
    	int nFallos = 1;	//numero de cartas para completar el proyecto
    	for( ;index < size; index++ ){
			if(cartas.get(index).getValor().getValor() == valorAnterior - 1 )
				valorAnterior--;
			else if ((index == 1 || index == 4) && nFallos > 0){
				valorAnterior = cartas.get(index).getValor().getValor();
				nFallos--;
			}
			else
				return false;	
		}   
    	
    	//if nFallos es 0 true, sino false (para que no coja tambien la escalera completa)
		if(nFallos != 0)
			return false;
    	return true;
    }
    
    //Se encarga de comprobar si hay proyecto de escalera en la lista de cartas y, en caso de haberlo, 
    //devuelve true
    private static boolean hayStraightDraw(List<Carta> cartas){
    	int cartaSobrante = 1, index = 1;
    	int valorAnterior = cartas.get(0).getValor().getValor();
    	
    	if(cartas.get(0).getValor() == E_Carta_Valor.A &&
    			(cartas.get(1).getValor() == E_Carta_Valor.CUATRO ||
    			cartas.get(2).getValor() == E_Carta_Valor.CUATRO)){    		
    		valorAnterior = 4;
    		if(cartas.get(1).getValor() == E_Carta_Valor.CUATRO)
    			index++;
    		else{
    			index += 2;
    			cartaSobrante--;
    		}
    		
    		for(; index <  cartas.size(); index++){
    			if(cartas.get(index).getValor().getValor() == valorAnterior-1)
    				valorAnterior--;
    			else if(index == 4 && cartaSobrante > 0)
    				cartaSobrante--;
    			else
    				return false;
    		}
    	}
    		
		return true;    	
    }*/
}
