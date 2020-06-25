package Objetos;

import java.util.ArrayList;

import Estructuras.Arbol;
import GenAleatorios.GenAleatorios;


enum estado {desconfigurada,abierta,cerrada};

/**
 * Implementacion de los metodos de la clase Planta.
 * 
 * @version 1.3
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
public class Puerta {
	/**Arbol, en el que se guardan la configuracion actual de la clase Puerta. */
	private Arbol <Llaves> a_Llaves= new Arbol<Llaves> ();
	/**Arbol, que guarda la configuracion de las llaves probadas hasta ahora. */
	private Arbol <Llaves> llavesProbadas=new Arbol<Llaves> ();
	/**Variable enumerada que guarda la configuracion actual de la puerta. */
	private estado configuracion;
	/**Array, que guarda la configuracion inicial del Arbol a_Llaves. */
	ArrayList <Llaves> conInicial = new ArrayList<Llaves>();
	/** altura de la puerta*/
	private int altura;
	/**
	 * Constructor de la clase por defecto. Pone un maximo de 50 llaves para
	 * la configuracion inicial.
	 */
	public Puerta(Llaves[] LL, int altura_){
		configuracion=estado.desconfigurada;
		altura = altura_;
		combinar(LL);
	}
	public void setAltura(int A){
		altura = A;
	}
	public estado getEstado(){
		return configuracion;
	}
	public int getAltura(){
		return altura;
	}
	public int getEstadoPuerta(){
		if(configuracion==estado.abierta){
			return 1;
		}
		if(configuracion==estado.cerrada){
		return -1;
		}
		return 0;
	}
	/**
	 * Inserta los datos en a_Llaves y en conInicial, y pona la configuracion de la
	 * puerta a cerrado.
	 * @param e Un array de llaves para insertar.
	 */
	private void combinar(Llaves[] llaves){
		//Pasamos el Array a ArrayList
		ArrayList <Llaves> llavesOrdenadas = new ArrayList<Llaves>();
		for (int i = 0; i < llaves.length; i++) {
			llavesOrdenadas.add(llaves[i]);
		}
		//Desordenamos el ArrayList
		while(!llavesOrdenadas.isEmpty()){
			int A= GenAleatorios.generarNumero(llavesOrdenadas.size());
//			System.out.println("Aleatorio generado (posición): "+A);
			conInicial.add(llavesOrdenadas.get(A));
			llavesOrdenadas.remove(A);
		}
		//Metemos las llaves del ArrayList en el arbol
		for (int i = 0; i < conInicial.size(); i++) {
			a_Llaves.insertar(conInicial.get(i));
		}
		//Inicializamos el arbol de llaves probadas
		llavesProbadas.destruirArbol();
		//establecemos la configuracion en cerrada
		configuracion=estado.cerrada;
		
	}
	/**
	 * Usa los datos almacenados en conInicial, para volver a insertarlos en a_Llaves.
	 * Al recombinar, las llaves probadas libera todos sus datos.
	 */
	public void recombinar(){
		System.out.println("Recombianando puerta");
		//Metemos las llaves del ArrayList en el arbol
		for (int i = 0; i < conInicial.size(); i++) {
			a_Llaves.insertar(conInicial.get(i));
		}
		//Inicializamos el arbol de llaves probadas
		llavesProbadas.destruirArbol();
		//establecemos la configuracion en cerrada
		configuracion=estado.cerrada;
	}
	/**
	 * Comprueba si una llave ya ha sido probada, en caso negativo se comprueba si esta
	 * pertenece a a_Llaves, en caso negativo se borra esta llave y se actualizan las
	 * llaves probadas. Por ultimo si a_Llaves esta vacio el enumerado configuracion
	 * pasa a abierto.
	 * @param l Llave a borrar de a_Llaves
	 * @return Verdadero si se borro correctamente, falso en caso contrario.
	 */
	public boolean probarLlave(Llaves llave){
		if(!llavesProbadas.pertenece(llave)){
			llavesProbadas.insertar(llave);
			if(a_Llaves.pertenece(llave)){
				a_Llaves.borrar(llave);
				if(a_Llaves.vacio()||a_Llaves.calcularAltura()<altura){
					configuracion=estado.abierta;
				}
				return true;
				
			}
		}
		return false;
	}
	/**
	 * Muestra las llaves que han sido probadas.
	 */
	public void mostrarLlavesProbadas(){
		llavesProbadas.inOrden();
	}
	/**
	 * Muestra las llaves con las que esta configurada la puerta.
	 */
	public void mostrarA_Llaves(){
		a_Llaves.inOrden();
	}
	/**
	 * Devuelve el estado actual de la puerta.
	 * @return 1, si configuracion esta en estado abierto, 0 si esta en desconfigurado
	 * y -1 en caso de que este cerrado.
	 */

	
}