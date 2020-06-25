package Cargadores;



import java.io.FileNotFoundException;
import java.io.IOException;

import Lugares.Planta;
import Lugares.Singleton;

/**
 * Clase creada para ser usada en la utilidad cargador
 * contiene el main del cargador. Se crea una instancia de la clase Estacion, una instancia de la clase Cargador
 * y se procesa el fichero de inicio, es decir, se leen todas las líneas y se van creando todas las instancias de la simulación
 * 
 * @version 1.1 
 * @author 
 * <b> Grupo "Los Topos v1.3.7a" </b><br>
 * Compuesto por:<br/>
 * Santiago Rangel Colon<br/>
 * Samuel Moreno Vincent<br/>
 * 
 * Asignatura Desarrollo de Programas<br/>
 * Numero de entrega: EC2
 * Curso 12/13
 */
public class CargaFichero {
	public static void main(String[] args) throws IOException  {
		/**  
		instancia asociada al fichero de entrada inicio.txt
		*/
		Cargador cargador = new Cargador();
		try {
			/**  
			método que procesa línea a línea el fichero de entrada inicio.txt
			*/
		     FicheroCarga.procesarFichero("inicio.txt", cargador);
		}
		catch (FileNotFoundException valor)  {
			System.err.println ("Excepcion capturada al procesar fichero: "+valor.getMessage());
		}
		catch (IOException valor)  {
			System.err.println ("Excepcion capturada al procesar fichero: "+valor.getMessage());
		}
		
		Planta planta= Singleton.GetPlanta();
		planta.simulacion();
		
	}
}
