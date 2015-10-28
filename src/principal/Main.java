package principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import analizador.Analizador;
import componentes.Carta;
import componentes.I_Jugada;
import componentes.JugadaValor;
import componentes.JugadorHoldem;
import componentes.Mano;
import entrada_salida_datos.Parser;
import excepciones.ErrorNumApartado;
import excepciones.ErrorNumJugadores;
import excepciones.ErrorNumParametros;
import excepciones.ErrorParseoCarta;
import excepciones.ErrorTamLinea;

public class Main {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		long time = System.currentTimeMillis(), manos = 0;
    	try {
    		if (args.length != 3) {
    			throw new ErrorNumParametros(); // �O CAMBIAR POR ErrorParseo("mensaje")?
    		}
    		int apartado = Integer.parseInt(args[0]);
			File archivoEntrada = new File(args[1]);
			File archivoSalida = new File(args[2]);
			FileReader fr = new FileReader(archivoEntrada);
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw = new FileWriter(archivoSalida);
			BufferedWriter bw = new BufferedWriter(fw);			
			
			String line, lineaSalida;
			String[] datosLinea;
			
			switch (apartado) {
				case 1:
					while ((line = br.readLine())!= null) {						
						
						manos++;
//						if (line.length() != 10)
//							throw new ErrorTamLinea(); // �O CAMBIAR POR ErrorParseo("mensaje")?
//						
                        //Instanciar una sola Mano.
                        Mano mano = new Mano();
                        	
                        //Parsear la string de entrada, y llenar la mano con esas cartas.
                        for (int i = 0; i <= 4; i++) {
                        	Carta carta = Parser.parseaCarta(line.substring(2*i, 2*i+2));
                        	mano.anadirCarta(carta);
                        }
                        
                       	//Analizar la mano con el Analizador.
                       	List<JugadaValor> jugadas = Analizador.analizaMano(mano);
                       	
                       	//Imprimir en el archivo de salida las jugadas de la mano.
                       	bw.write(line);
                       	bw.newLine();
						for (JugadaValor jugada : jugadas) {
							bw.write(" - ");
							bw.write(jugada.toString());
	                       	bw.newLine();
						}							
						bw.newLine();
						
					}
					break;
					
				case 2:
					while ((line = br.readLine())!= null) {
                        datosLinea = line.split(";");
						int numCartasComunes = Integer.parseInt(datosLinea[1]);
						if (line.length() != (numCartasComunes*2 + 7))
							throw new ErrorTamLinea();
						
						//Crear una instancia de Jugador y llenar con sus hole cards
						JugadorHoldem jugadorHoldem = new JugadorHoldem();
						for (int i = 0; i < 2; i++) {
                        	Carta carta = Parser.parseaCarta(datosLinea[0].substring(2*i, 2*i+2));
                        	jugadorHoldem.anadirCarta(carta);
                        }
						
						//Crear una instancia de Mesa y llenar con las cartas comunitarias
						ArrayList<Carta> mesa = new ArrayList<Carta>();
						for (int i = 0; i < numCartasComunes; i++) {
                        	Carta carta = Parser.parseaCarta(datosLinea[2].substring(2*i, 2*i+2));
                        	mesa.add(carta);
						}
						
						//Analizar lo que tiene el jugador.
						List<I_Jugada> jugadas = Analizador.analizaJugador(jugadorHoldem, mesa);
						
						//Imprimir en el archivo de salida la salida.
						bw.write(line);
						bw.newLine();
						for (I_Jugada jugada : jugadas) {
							lineaSalida = " - " + jugada.toString();
							bw.write(lineaSalida);
					           bw.newLine();
						}
						bw.newLine();
					}
					break;
					
				case 3:
					while ((line = br.readLine())!= null) {
                        datosLinea = line.split(";");
						int numJugadores = Integer.parseInt(datosLinea[0]);
						if (numJugadores < 2 || numJugadores > 9)
							throw new ErrorNumJugadores(); // �O CAMBIAR POR ErrorParseo("mensaje")?
						
						if (line.length() != (numJugadores*7 + 12))
							throw new ErrorTamLinea(); // �O CAMBIAR POR ErrorParseo("mensaje")?
						
                        //Crear instancias de Jugador y llenar con sus hole cards
						ArrayList<JugadorHoldem> listaJugadores = new ArrayList<JugadorHoldem>();
						for (int j = 1; j <= numJugadores; j++) {
							JugadorHoldem jugadorHoldem = new JugadorHoldem();
							for (int i = 0; i < 2; i++) {
								jugadorHoldem.setId(datosLinea[j].substring(0, 2));
	                       		Carta carta = Parser.parseaCarta(datosLinea[j].substring(2+(2*i),2+(2*i+2)));
	                       		jugadorHoldem.anadirCarta(carta);
	                       	}
						}
						
                        //Crear una instancia de Mesa y llenar con las cartas comunitarias
						ArrayList<Carta> mesa = new ArrayList<Carta>();
						for (int i = 0; i < 5; i++) {
                        	Carta carta = Parser.parseaCarta(datosLinea[2].substring(2*i, 2*i+2));
                        	mesa.add(carta);
						}
						
						//Analizar y comparar los jugadores
						List<JugadorHoldem> jugadoresOrdenados = Analizador.comparaJugadores(listaJugadores, mesa);
						
                        //Imprimir en el archivo de salida la salida.
						bw.write(line);
						bw.newLine();
						for (JugadorHoldem jugadorHoldem : jugadoresOrdenados) {
							List<I_Jugada> jugadas = jugadorHoldem.getJugadas();
							lineaSalida = jugadorHoldem.getId() + ": " + jugadas.get(0).toString();
							bw.write(lineaSalida);
					          bw.newLine();
						}
                        bw.newLine();
                    }
					break;
					
				default:
					throw new ErrorNumApartado(); // �O CAMBIAR POR ErrorParseo("mensaje")?
					
			}
			br.close();
			fr.close();
			bw.close();
			fw.close();
		
    	} catch (ErrorTamLinea e) {
    		System.out.println(e.getMessage());
		} catch (ErrorNumParametros e) {
    		System.out.println(e.getMessage());
    	} catch (ErrorParseoCarta e) {
			System.out.println(e.getMessage());
		} catch (ErrorNumJugadores e) {
			System.out.println(e.getMessage());
		} catch (ErrorNumApartado e) { // �O CAMBIAR TODOS ESTOS POR UN ErrorParseo GENERAL?
        	System.out.println(e.getMessage());
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo de entrada no existe.");
        } catch (NumberFormatException nfe) {
        	nfe.printStackTrace();
        } catch (IOException ioe) {
        	ioe.printStackTrace();
        } catch (Exception e) {
			e.printStackTrace();
		}
		long newtime = System.currentTimeMillis() - time;
    	System.out.println(newtime);
    	System.out.println(manos);
	}	
}


