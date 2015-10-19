package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            // notificar error y salir del programa
        }
        else  {
            try {
                int apartado = Integer.parseInt(args[0]);
                File archivoEntrada = new File(args[1]);
                File archivoSalida = new File(args[2]);
                FileReader fr = new FileReader(archivoEntrada);
                BufferedReader br = new BufferedReader(fr);
                FileWriter fw = new FileWriter(archivoSalida);
                BufferedWriter bw = new BufferedWriter(fw);

                String line, lineaSalida, idYCartasJugadores = "";
                String[] salida, datosLinea;
                int numCartasComunes, numJugadores;

                switch (apartado) {
                    case 1:
                        while ((line = br.readLine())!= null) {

                            //Instanciar una sola Mano.

                            //Parsear la string de entrada, y llenar la mano con esas cartas.

                            //Analizar la mano con el Analizador.

                            //Imprimir en el archivo de salida las jugadas de la mano.
                        }

                        break;
                    case 2:
                        while ((line = br.readLine())!= null) {
                            //Crear una instancia de Jugador y llenar con sus hole cards

                            //Crear una instancia de Mesa y llenar con las cartas comunitarias

                            //Analizar lo que tiene el jugador.

                            //Imprimir en el archivo de salida la salida.

                        }
                        break;
                    case 3:
                        while ((line = br.readLine())!= null) {
                            //Crear instancias de Jugador y llenar con sus hole cards

                            //Crear una instancia de Mesa y llenar con las cartas comunitarias

                            //Analizar y comparar los jugadores

                            //Imprimir en el archivo de salida la salida.

                        }
                        break;
                    default:
                        // notificar error y salir
                }
                br.close();
                fr.close();
                bw.close();
                fw.close();
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
